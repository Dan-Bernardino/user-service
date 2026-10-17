package com.ecommerce.userservice.dto;

import com.ecommerce.userservice.model.Role;
import jakarta.validation.constraints.NotNull;

public record UserRoleRequestDTO(
        @NotNull(message = "El rol es obligatorio")
        Role role
) {
}
