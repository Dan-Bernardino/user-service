package com.ecommerce.userservice.security.config.jwt;

import jakarta.servlet.FilterChain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class JwtFilterTest {

    private static final String EMAIL = "isaac@example.com";

    private JwtUtil jwtUtil;
    private UserDetailsService userDetailsService;
    private JwtFilter jwtFilter;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private FilterChain filterChain;

    @BeforeEach
    void preparar() {
        jwtUtil = mock(JwtUtil.class);
        userDetailsService = mock(UserDetailsService.class);
        jwtFilter = new JwtFilter(jwtUtil, userDetailsService);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        filterChain = mock(FilterChain.class);
        SecurityContextHolder.clearContext();
    }

    @AfterEach
    void limpiar() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void sinHeaderAuthorization_continuaLaCadena() throws Exception {
        jwtFilter.doFilter(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtUtil, userDetailsService);
    }

    @Test
    void conHeaderVacio_continuaLaCadena() throws Exception {
        request.addHeader(HttpHeaders.AUTHORIZATION, "");

        jwtFilter.doFilter(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtUtil, userDetailsService);
    }

    @Test
    void conHeaderQueNoEsBearer_continuaLaCadena() throws Exception {
        request.addHeader(HttpHeaders.AUTHORIZATION, "Basic credenciales");

        jwtFilter.doFilter(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtUtil, userDetailsService);
    }

    @Test
    void conTokenInvalido_continuaLaCadenaSinAutenticar() throws Exception {
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer token-invalido");
        when(jwtUtil.isValid("token-invalido")).thenReturn(false);

        jwtFilter.doFilter(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verify(jwtUtil).isValid("token-invalido");
        verifyNoInteractions(userDetailsService);
        assertEquals(null, SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void conTokenValido_autenticaAlUsuarioEnElContexto() throws Exception {
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer token-valido");
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn(EMAIL);
        when(userDetails.getAuthorities())
                .thenAnswer(invocacion -> List.of(new SimpleGrantedAuthority("CLIENTE")));

        when(jwtUtil.isValid("token-valido")).thenReturn(true);
        when(jwtUtil.getUsername("token-valido")).thenReturn(EMAIL);
        when(userDetailsService.loadUserByUsername(EMAIL)).thenReturn(userDetails);

        jwtFilter.doFilter(request, response, filterChain);

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        assertNotNull(authentication);
        assertEquals(EMAIL, authentication.getName());
        assertEquals(
                "CLIENTE",
                authentication.getAuthorities().iterator().next().getAuthority()
        );

        verify(userDetailsService).loadUserByUsername(EMAIL);
        verify(filterChain).doFilter(request, response);
    }
}
