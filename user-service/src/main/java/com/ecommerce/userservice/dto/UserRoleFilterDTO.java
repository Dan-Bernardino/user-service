package com.ecommerce.userservice.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRoleFilterDTO(
        @NotBlank(message = "El rol es obligatorio")
        String role
) {
}