package com.ecommerce.userservice.exception.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponseValidation(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        Map<String, String> validationErrors
) {
}
