package com.ecommerce.userservice.exception;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
        super("Credenciales inválidas");
    }
}
