package com.ecommerce.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PasswordRequestDTO(
        @NotBlank
        String currentPassword,

        @NotBlank(message = "El newPassword es obligatorio")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,72}$",

                message = "La contraseña requiere mínimo 8 caracteres, mayúscula, minúscula, número y símbolo."
        )
        String newPassword,

        @NotBlank(message = "El currentPassword es obligatorio")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,72}$",

                message = "La contraseña requiere mínimo 8 caracteres, mayúscula, minúscula, número y símbolo."
        )
        String confirmPassword
) {
}
