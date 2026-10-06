package com.ecommerce.userservice.dto;

import java.time.LocalDateTime;

public record LoginResponseDTO(
        Long id,
        String nombre,
        String correo,
        String rol,
        Boolean activo,
        LocalDateTime fechaCreacion,
        String jwt
) {
}