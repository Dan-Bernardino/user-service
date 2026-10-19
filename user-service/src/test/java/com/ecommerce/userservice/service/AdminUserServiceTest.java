package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.AdminUserResponseDTO;
import com.ecommerce.userservice.dto.PagedResponse;
import com.ecommerce.userservice.model.Role;
import com.ecommerce.userservice.model.UserStatus;
import com.ecommerce.userservice.model.Users;
import com.ecommerce.userservice.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AdminUserServiceTest {

    private static final LocalDateTime CREATED_AT = LocalDateTime.of(2026, 1, 1, 12, 0);

    private UsersRepository usersRepository;
    private AdminUserService adminUserService;

    @BeforeEach
    void preparar() {
        usersRepository = mock(UsersRepository.class);
        adminUserService = new AdminUserService(usersRepository);
    }

    @Test
    void findUsersRechazaPaginaNegativa() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> adminUserService.findUsers(-1, 5, null, null, null));

        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
        verify(usersRepository, never()).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    void findUsersRechazaSizeMenorAUno() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> adminUserService.findUsers(0, 0, null, null, null));

        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }

    @Test
    void findUsersRechazaSizeMayorAlMaximo() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> adminUserService.findUsers(0, 6, null, null, null));

        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }

    @Test
    void findUsersDevuelveLaPaginaMapeada() {
        Users user = usuarioDePrueba();
        Page<Users> page = new PageImpl<>(List.of(user), PageRequest.of(0, 5), 1);
        when(usersRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);

        PagedResponse<AdminUserResponseDTO> response =
                adminUserService.findUsers(0, 5, Role.ADMINISTRADOR, UserStatus.ACTIVE, "isaac");

        assertEquals(1, response.content().size());
        assertEquals(0, response.page());
        assertEquals(5, response.size());
        assertEquals(1, response.totalElements());
        assertEquals(1, response.totalPages());
        assertTrue(response.first());
        assertTrue(response.last());
        assertFalse(response.empty());

        AdminUserResponseDTO dto = response.content().get(0);
        assertEquals(10L, dto.id());
        assertEquals("ADM-010-A", dto.userIdentifier());
        assertEquals("Isaac", dto.name());
        assertEquals("Lopez", dto.paternalName());
        assertEquals("Perez", dto.maternalName());
        assertEquals("isaac@gmail.com", dto.email());
        assertEquals(Role.ADMINISTRADOR, dto.role());
        assertTrue(dto.isActive());
        assertEquals(CREATED_AT, dto.createdAt());
    }

    @Test
    void findUsersAceptaFiltrosOpcionalesNulos() {
        Page<Users> page = new PageImpl<>(List.of(), PageRequest.of(0, 5), 0);
        when(usersRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);

        PagedResponse<AdminUserResponseDTO> response =
                adminUserService.findUsers(0, 5, null, null, null);

        assertTrue(response.content().isEmpty());
        assertTrue(response.empty());
    }

    private Users usuarioDePrueba() {
        Users user = new Users();
        user.setId(10L);
        user.setUserIdentifier("ADM-010-A");
        user.setName("Isaac");
        user.setPaternalName("Lopez");
        user.setMaternalName("Perez");
        user.setEmail("isaac@gmail.com");
        user.setRole(Role.ADMINISTRADOR);
        user.setIsActive(true);
        user.setCreatedAt(CREATED_AT);
        return user;
    }
}
