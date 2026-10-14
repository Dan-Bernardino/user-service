package com.ecommerce.userservice.dto;

import com.ecommerce.userservice.validation.ValidEmailDomain;
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
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+(\\\\.[A-Za-z]{2,})+$",
            message = "El correo no tiene un formato válido"
    )
    @ValidEmailDomain
    String email,

    @NotBlank(message = "El password es obligatorio")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,72}$",
            /*La contraseña debe incluir:
                - Minimo 8 caracteres, Maximo 72 caracteres
                - Al menos una letra minúscula
                - Al menos una letra mayúscula
                - Al menos un dígito
                - Al menos un carácter especial
             */
            message = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un caracter especial"
    )
    String password){
}
