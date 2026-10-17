package com.ecommerce.userservice.security.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userService;
    }


    //OncePerRequestFilter nos exige usar este metodo
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {


        //1. Validar que sea un Header Authorization valido
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader==null || authHeader.isEmpty() || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        //2. Validar que el jwt sea autentico
        String jwt = authHeader.substring(7);
        if(!jwtUtil.isValid(jwt)){
            filterChain.doFilter(request, response);
            return;
        }

        //3. Cargar el User del UserDetailsService
        String username = jwtUtil.getUsername(jwt);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        //4. Agregar el User al Contexto de Seguridad
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),
                        null,
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
