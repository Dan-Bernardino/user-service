package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.AdminUserResponseDTO;
import com.ecommerce.userservice.dto.PagedResponse;
import com.ecommerce.userservice.model.Role;
import com.ecommerce.userservice.model.UserStatus;
import com.ecommerce.userservice.service.AdminUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AdminUserControllerTest {

    private AdminUserService adminUserService;
    private AdminUserController adminUserController;

    @BeforeEach
    void preparar() {
        adminUserService = mock(AdminUserService.class);
        adminUserController = new AdminUserController(adminUserService);
    }

    @Test
    void getUsersDevuelveLaPaginaSolicitada() {
        AdminUserResponseDTO dto = new AdminUserResponseDTO(
                1L, "ADM-001-A", "Isaac", "Lopez", "Perez", "isaac@gmail.com",
                Role.ADMINISTRADOR, true, LocalDateTime.of(2026, 1, 1, 12, 0));
        PagedResponse<AdminUserResponseDTO> esperado = new PagedResponse<>(
                List.of(dto), 1, 3, 1, 1, true, true, false);
        when(adminUserService.findUsers(1, 3, Role.ADMINISTRADOR, UserStatus.ACTIVE, "isaac"))
                .thenReturn(esperado);

        ResponseEntity<PagedResponse<AdminUserResponseDTO>> resultado =
                adminUserController.getUsers(1, 3, Role.ADMINISTRADOR, UserStatus.ACTIVE, "isaac");

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertSame(esperado, resultado.getBody());
        verify(adminUserService).findUsers(1, 3, Role.ADMINISTRADOR, UserStatus.ACTIVE, "isaac");
    }
}
