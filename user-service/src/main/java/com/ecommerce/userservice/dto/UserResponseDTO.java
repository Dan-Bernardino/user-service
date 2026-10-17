package com.ecommerce.userservice.dto;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String userIdentifier,
        String fullName,
        String email,
        String role,
        Boolean isActive,
        LocalDateTime createdAt
) {
}
