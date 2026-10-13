package com.ecommerce.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO (
    @NotBlank(message = "El nombre es obligatorio")
    String name,
    @NotBlank(message = "El apellido paterno es obligatorio")
    String paternalName,
    @NotBlank(message = "El apellido materno es obligatorio")
    String maternalName,
    @NotBlank(message = "El email es obligatorio")
    @Email
    String email,
    @NotBlank(message = "El password es obligatorio")
    String password){
}
