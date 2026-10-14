package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.UserRequestDTO;
import com.ecommerce.userservice.dto.UserResponseDTO;
import com.ecommerce.userservice.dto.UserStatusDTO;
import com.ecommerce.userservice.model.Role;
import com.ecommerce.userservice.model.Users;
import com.ecommerce.userservice.repository.UsersRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
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

        usuario = new Users();
        usuario.setId(1L);
        usuario.setName("Isaac");
        usuario.setPaternalName("Lopez");
        usuario.setMaternalName("Perez");
        usuario.setEmail("isaac@example.com");
        usuario.setPassword("passwordDePrueba");
        usuario.setRole(Role.CLIENTE);
        usuario.setIsActive(true);
        usuario.setCreatedAt(
                LocalDateTime.of(2026, 1, 1, 12, 0)
        );
    }

    @Test
    void createUser() {
        // Arrange
        UserRequestDTO request = new UserRequestDTO(
                "Isaac",
                "Lopez",
                "Perez",
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
                    nuevoUsuario.setCreatedAt(
                            LocalDateTime.of(2026, 1, 1, 12, 0)
                    );

                    return nuevoUsuario;
                });

        // Act
        UserResponseDTO respuesta =
                usersService.createUser(request);

        // Assert
        assertNotNull(respuesta);
        assertEquals(Long.valueOf(1L), respuesta.id());
        assertEquals("Isaac Lopez Perez", respuesta.fullName());
        assertEquals("isaac@example.com", respuesta.email());
        assertEquals("CLIENTE", respuesta.role());
        assertEquals(Boolean.TRUE, respuesta.isActive());
        assertEquals(
                LocalDateTime.of(2026, 1, 1, 12, 0),
                respuesta.createdAt()
        );

        verify(usersRepository).existsByEmail(request.email());
        verify(usersRepository).save(captor.capture());

        Users usuarioGuardado = captor.getValue();

        assertEquals("Isaac", usuarioGuardado.getName());
        assertEquals("Lopez", usuarioGuardado.getPaternalName());
        assertEquals("Perez", usuarioGuardado.getMaternalName());
        assertEquals("isaac@example.com", usuarioGuardado.getEmail());
        assertEquals(Role.CLIENTE, usuarioGuardado.getRole());
        assertEquals(Boolean.TRUE, usuarioGuardado.getIsActive());

        assertTrue(
                new BCryptPasswordEncoder().matches(
                        request.password(),
                        usuarioGuardado.getPassword()
                )
        );
    }

    @Test
    void findById() {
        // Arrange
        when(usersRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        // Act
        UserResponseDTO respuesta = usersService.findById(1L);

        // Assert
        assertNotNull(respuesta);
        assertEquals(usuario.getId(), respuesta.id());
        assertEquals("Isaac Lopez Perez", respuesta.fullName());
        assertEquals(usuario.getEmail(), respuesta.email());
        assertEquals(usuario.getRole().name(), respuesta.role());
        assertEquals(usuario.getIsActive(), respuesta.isActive());
        assertEquals(usuario.getCreatedAt(), respuesta.createdAt());

        verify(usersRepository).findById(1L);
    }

    @Test
    void findByEmail() {
        // Arrange
        when(usersRepository.findByEmail("isaac@example.com"))
                .thenReturn(Optional.of(usuario));

        // Act
        UserResponseDTO respuesta =
                usersService.findByEmail("isaac@example.com");

        // Assert
        assertNotNull(respuesta);
        assertEquals(usuario.getId(), respuesta.id());
        assertEquals("Isaac Lopez Perez", respuesta.fullName());
        assertEquals("isaac@example.com", respuesta.email());
        assertEquals("CLIENTE", respuesta.role());
        assertEquals(Boolean.TRUE, respuesta.isActive());
        assertEquals(usuario.getCreatedAt(), respuesta.createdAt());

        verify(usersRepository).findByEmail("isaac@example.com");
    }

    @Test
    void updateUser() {
        // Arrange
        UserRequestDTO request = new UserRequestDTO(
                "Carlos",
                "Garcia",
                "Sanchez",
                "nuevo@example.com",
                "otraClave"
        );

        when(usersRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        when(usersRepository.save(any(Users.class)))
                .thenAnswer(invocacion -> invocacion.getArgument(0));

        // Act
        UserResponseDTO respuesta =
                usersService.updateUser(1L, request);

        // Assert
        assertNotNull(respuesta);
        assertEquals(Long.valueOf(1L), respuesta.id());
        assertEquals("Carlos Garcia Sanchez", respuesta.fullName());
        assertEquals("nuevo@example.com", respuesta.email());
        assertEquals("CLIENTE", respuesta.role());
        assertEquals(Boolean.TRUE, respuesta.isActive());
        assertEquals(usuario.getCreatedAt(), respuesta.createdAt());

        assertEquals("Carlos", usuario.getName());
        assertEquals("Garcia", usuario.getPaternalName());
        assertEquals("Sanchez", usuario.getMaternalName());
        assertEquals("nuevo@example.com", usuario.getEmail());
        assertEquals("passwordDePrueba", usuario.getPassword());

        verify(usersRepository).findById(1L);
        verify(usersRepository).save(usuario);
    }

    @Test
    void activateAndDeactivateUser() {
        // Arrange
        when(usersRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        when(usersRepository.save(any(Users.class)))
                .thenAnswer(invocacion -> invocacion.getArgument(0));

        // Act
        UserStatusDTO respuesta =
                usersService.activateAndDeactivateUser(1L, false);

        // Assert
        assertNotNull(respuesta);
        assertEquals(Long.valueOf(1L), respuesta.id());
        assertFalse(respuesta.isActive());
        assertEquals(Boolean.FALSE, usuario.getIsActive());

        verify(usersRepository).findById(1L);
        verify(usersRepository).save(usuario);
    }

    @Test
    void findUserStatusById() {
        // Arrange
        when(usersRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        // Act
        UserStatusDTO respuesta =
                usersService.findUserStatusById(1L);

        // Assert
        assertNotNull(respuesta);
        assertEquals(Long.valueOf(1L), respuesta.id());
        assertTrue(respuesta.isActive());

        verify(usersRepository).findById(1L);
        verify(usersRepository, never()).save(any(Users.class));
    }

    @Test
    void findAllUsersByRole() {
        // Arrange
        when(usersRepository.findAllByRole(Role.CLIENTE))
                .thenReturn(List.of(usuario));

        // Act
        List<UserResponseDTO> respuesta =
                usersService.findAllUsersByRole("cliente");

        // Assert
        assertNotNull(respuesta);
        assertEquals(1, respuesta.size());

        UserResponseDTO usuarioEncontrado = respuesta.get(0);

        assertEquals(usuario.getId(), usuarioEncontrado.id());
        assertEquals(
                "Isaac Lopez Perez",
                usuarioEncontrado.fullName()
        );
        assertEquals(usuario.getEmail(), usuarioEncontrado.email());
        assertEquals("CLIENTE", usuarioEncontrado.role());
        assertEquals(
                usuario.getIsActive(),
                usuarioEncontrado.isActive()
        );
        assertEquals(
                usuario.getCreatedAt(),
                usuarioEncontrado.createdAt()
        );

        verify(usersRepository).findAllByRole(Role.CLIENTE);
    }
}