package com.ecommerce.userservice.exception;

public class UsersNotFoundException extends RuntimeException {
    public UsersNotFoundException(Long id) {
        super("No se encontró un usuario con el id: " + id);
    }
}
