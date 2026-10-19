package com.ecommerce.userservice.exception;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException(String message) {
        super("Credenciales inválidas");
    }
}
