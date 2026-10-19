package com.ecommerce.userservice.dto;

import com.ecommerce.userservice.model.Role;
import com.ecommerce.userservice.model.Users;
import java.time.LocalDateTime;

public record AdminUserResponseDTO(
        Long id,
        String userIdentifier,
        String name,
        String paternalName,
        String maternalName,
        String email,
        Role role,
        Boolean isActive,
        LocalDateTime createdAt
) {
    public static AdminUserResponseDTO from(Users u) {
        return new AdminUserResponseDTO(
                u.getId(), u.getUserIdentifier(), u.getName(), u.getPaternalName(),
                u.getMaternalName(), u.getEmail(), u.getRole(), u.getIsActive(), u.getCreatedAt());
    }
}