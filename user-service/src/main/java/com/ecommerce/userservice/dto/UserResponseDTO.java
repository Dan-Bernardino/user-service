package com.ecommerce.userservice.dto;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String nombre,
        String email,
        String rol,
        Boolean autenticado,
        Boolean activo,
        LocalDateTime fechaCreacion
) {
}
