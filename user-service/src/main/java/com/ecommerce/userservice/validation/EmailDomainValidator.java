package com.ecommerce.userservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;

public class EmailDomainValidator implements ConstraintValidator<ValidEmailDomain, String> {

    private static final Set<String> DOMINIOS_PERMITIDOS = Set.of(
            "gmail.com", "outlook.com", "hotmail.com", "yahoo.com", "icloud.com"
    );

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || !email.contains("@")) {
            return true; // de eso se encargan @NotBlank y @Pattern
        }
        String dominio = email.substring(email.lastIndexOf('@') + 1).toLowerCase();
        return DOMINIOS_PERMITIDOS.contains(dominio);
    }
}