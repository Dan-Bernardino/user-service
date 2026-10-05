package com.ecommerce.userservice.dto;

public record UserResponseDTO(
        Long id,
        String nombre,
        String email,
        String rol,
        Boolean autenticado
) {
}
