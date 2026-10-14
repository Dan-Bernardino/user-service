package com.ecommerce.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRequestDTO (
    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(
            regexp = "[\\p{L}]+(?:[ ]+[\\p{L}]+)*",
            message = "El nombre solo puede contener letras y espacios"
    )
    String name,

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Pattern(
            regexp = "[\\p{L}]+(?:[ ]+[\\p{L}]+)*",
            message = "El apellido paterno solo puede contener letras y espacios"
    )
    String paternalName,

    @NotBlank(message = "El apellido materno es obligatorio")
    @Pattern(
            regexp = "[\\p{L}]+(?:[ ]+[\\p{L}]+)*",
            message = "El apellido materno solo puede contener letras y espacios"
    )
    String maternalName,
    @NotBlank(message = "El email es obligatorio")
    @Email
    String email,

    @NotBlank(message = "El password es obligatorio")
    String password){
}
