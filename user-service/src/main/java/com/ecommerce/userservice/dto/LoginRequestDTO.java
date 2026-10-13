package com.ecommerce.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "El email es obligatorio")
        @Email
        String email,
        @NotBlank(message = "El password es obligatorio")
        String password) {
}
