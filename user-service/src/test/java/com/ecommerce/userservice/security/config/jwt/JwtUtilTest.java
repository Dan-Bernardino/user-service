package com.ecommerce.userservice.security.config.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtUtilTest {

    private static final String EMAIL = "isaac@example.com";

    private final JwtUtil jwtUtil = new JwtUtil();

    @Test
    void createGeneraUnTokenValidoConElEmailComoSubject() {
        String token = jwtUtil.create(EMAIL);

        assertNotNull(token);
        assertTrue(jwtUtil.isValid(token));
        assertEquals(EMAIL, jwtUtil.getUsername(token));
    }

    @Test
    void isValidDevuelveFalseParaUnTokenInvalido() {
        assertFalse(jwtUtil.isValid("token-invalido"));
    }

    @Test
    void getUsernameLanzaExcepcionParaUnTokenInvalido() {
        assertThrows(
                JWTVerificationException.class,
                () -> jwtUtil.getUsername("token-invalido")
        );
    }
}
