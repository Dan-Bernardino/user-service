package com.ecommerce.userservice.exception;

public class UsersNotFoundException extends RuntimeException {
    public UsersNotFoundException(String message) {super(message);
    }
}
