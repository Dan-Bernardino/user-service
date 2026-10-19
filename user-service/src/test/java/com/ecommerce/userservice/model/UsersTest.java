package com.ecommerce.userservice.model;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsersTest {

    @Test
    void getAuthoritiesDevuelveElRolDelUsuario() {
        Users usuario = new Users();
        usuario.setRole(Role.ADMINISTRADOR);

        Collection<? extends GrantedAuthority> authorities = usuario.getAuthorities();

        assertEquals(1, authorities.size());
        assertEquals("ADMINISTRADOR", authorities.iterator().next().getAuthority());
    }

    @Test
    void getUsernameDevuelveElEmail() {
        Users usuario = new Users();
        usuario.setEmail("isaac@example.com");

        assertEquals("isaac@example.com", usuario.getUsername());
    }

    @Test
    void isEnabledDevuelveElEstadoIsActive() {
        Users usuario = new Users();

        usuario.setIsActive(true);
        assertTrue(usuario.isEnabled());

        usuario.setIsActive(false);
        assertFalse(usuario.isEnabled());
    }

    @Test
    void lasBanderasDeLaCuentaNoEstanExpiradas() {
        Users usuario = new Users();

        assertTrue(usuario.isAccountNonExpired());
        assertTrue(usuario.isAccountNonLocked());
        assertTrue(usuario.isCredentialsNonExpired());
    }

    @Test
    void elConstructorAsignaTodosLosCampos() {
        Users usuario = new Users(
                "Isaac", "Lopez", "Perez", "isaac@gmail.com", "secreta",
                Role.USUARIO, false);

        assertEquals("Isaac", usuario.getName());
        assertEquals("Lopez", usuario.getPaternalName());
        assertEquals("Perez", usuario.getMaternalName());
        assertEquals("isaac@gmail.com", usuario.getEmail());
        assertEquals("secreta", usuario.getPassword());
        assertEquals(Role.USUARIO, usuario.getRole());
        assertFalse(usuario.getIsActive());
    }
}
