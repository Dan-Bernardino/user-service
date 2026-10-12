package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.UserRequestDTO;
import com.ecommerce.userservice.dto.UserResponseDTO;
import com.ecommerce.userservice.dto.UserStatusDTO;
import com.ecommerce.userservice.model.Rol;
import com.ecommerce.userservice.model.Users;
import com.ecommerce.userservice.repository.UsersRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsersServiceTest {

    private UsersRepository usersRepository;
    private UsersService usersService;
    private Users usuario;

    @BeforeEach
    void preparar() {
        usersRepository = mock(UsersRepository.class);
        usersService = new UsersService(usersRepository);

        usuario = new Users(
                "Isaac",
                "isaac@example.com",
                "contraseniaDePrueba",
                Rol.CLIENTE,
                false,
                true
        );

        usuario.setId(1L);
        usuario.setFechaCreacion(
                LocalDateTime.of(2026, 1, 1, 12, 0)
        );
    }

    @Test
    void crearUsuario() {
        // Arrange
        UserRequestDTO request = new UserRequestDTO(
                "Isaac",
                "isaac@example.com",
                "clave123"
        );

        ArgumentCaptor<Users> captor =
                ArgumentCaptor.forClass(Users.class);

        when(usersRepository.existsByEmail(request.email()))
                .thenReturn(false);

        when(usersRepository.save(any(Users.class)))
                .thenAnswer(invocacion -> {
                    Users nuevoUsuario = invocacion.getArgument(0);

                    nuevoUsuario.setId(1L);
                    nuevoUsuario.setFechaCreacion(
                            LocalDateTime.of(2026, 1, 1, 12, 0)
                    );

                    return nuevoUsuario;
                });

        // Act
        UserResponseDTO respuesta =
                usersService.crearUsuario(request);

        // Assert
        assertNotNull(respuesta);
        assertEquals(Long.valueOf(1L), respuesta.id());
        assertEquals("Isaac", respuesta.nombre());
        assertEquals("isaac@example.com", respuesta.email());
        assertEquals("CLIENTE", respuesta.rol());
        assertEquals(Boolean.FALSE, respuesta.autenticado());
        assertEquals(Boolean.TRUE, respuesta.activo());
        assertEquals(
                LocalDateTime.of(2026, 1, 1, 12, 0),
                respuesta.fechaCreacion()
        );

        verify(usersRepository).existsByEmail(request.email());
        verify(usersRepository).save(captor.capture());

        Users usuarioGuardado = captor.getValue();

        assertEquals("Isaac", usuarioGuardado.getNombre());
        assertEquals("isaac@example.com", usuarioGuardado.getEmail());
        assertEquals(Rol.CLIENTE, usuarioGuardado.getRol());
        assertEquals(Boolean.FALSE, usuarioGuardado.getAutenticado());

        assertTrue(
                new BCryptPasswordEncoder().matches(
                        request.contrasenia(),
                        usuarioGuardado.getContrasenia()
                )
        );
    }

    @Test
    void obtenerUsuarioPorId() {
        // Arrange
        when(usersRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        // Act
        UserResponseDTO respuesta =
                usersService.obtenerUsuarioPorId(1L);

        // Assert
        assertNotNull(respuesta);
        assertEquals(usuario.getId(), respuesta.id());
        assertEquals(usuario.getNombre(), respuesta.nombre());
        assertEquals(usuario.getEmail(), respuesta.email());
        assertEquals(usuario.getRol().name(), respuesta.rol());
        assertEquals(usuario.getAutenticado(), respuesta.autenticado());
        assertEquals(usuario.getActivo(), respuesta.activo());
        assertEquals(usuario.getFechaCreacion(), respuesta.fechaCreacion());

        verify(usersRepository).findById(1L);
    }

    @Test
    void buscarPorEmail() {
        // Arrange
        when(usersRepository.findByEmail("isaac@example.com"))
                .thenReturn(Optional.of(usuario));

        // Act
        UserResponseDTO respuesta =
                usersService.buscarPorEmail("isaac@example.com");

        // Assert
        assertNotNull(respuesta);
        assertEquals(usuario.getId(), respuesta.id());
        assertEquals("Isaac", respuesta.nombre());
        assertEquals("isaac@example.com", respuesta.email());
        assertEquals("CLIENTE", respuesta.rol());

        verify(usersRepository).findByEmail("isaac@example.com");
    }

    @Test
    void actualizarUsuario() {
        // Arrange
        UserRequestDTO request = new UserRequestDTO(
                "Isaac actualizado",
                "nuevo@example.com",
                "otraClave"
        );

        when(usersRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        when(usersRepository.save(any(Users.class)))
                .thenAnswer(invocacion -> invocacion.getArgument(0));

        // Act
        UserResponseDTO respuesta =
                usersService.actualizarUsuario(1L, request);

        // Assert
        assertEquals(Long.valueOf(1L), respuesta.id());
        assertEquals("Isaac actualizado", respuesta.nombre());
        assertEquals("nuevo@example.com", respuesta.email());

        assertEquals("Isaac actualizado", usuario.getNombre());
        assertEquals("nuevo@example.com", usuario.getEmail());
        assertEquals(
                "contraseniaDePrueba",
                usuario.getContrasenia()
        );

        verify(usersRepository).findById(1L);
        verify(usersRepository).save(usuario);
    }

    @Test
    void actualizarEstadoUsuario() {
        // Arrange
        UserStatusDTO request = new UserStatusDTO(false);

        when(usersRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        when(usersRepository.save(any(Users.class)))
                .thenAnswer(invocacion -> invocacion.getArgument(0));

        // Act
        UserResponseDTO respuesta =
                usersService.actualizarEstadoUsuario(1L, request);

        // Assert
        assertEquals(Long.valueOf(1L), respuesta.id());
        assertEquals(Boolean.FALSE, respuesta.activo());
        assertEquals(Boolean.FALSE, usuario.getActivo());

        //
        verify(usersRepository).findById(1L);
        verify(usersRepository).save(usuario);
    }
}