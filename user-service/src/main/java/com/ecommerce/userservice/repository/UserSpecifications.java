package com.ecommerce.userservice.repository;

import com.ecommerce.userservice.model.Role;
import com.ecommerce.userservice.model.UserStatus;
import com.ecommerce.userservice.model.Users;
import org.springframework.data.jpa.domain.Specification;

public final class UserSpecifications {

    private UserSpecifications() {}

    public static Specification<Users> hasRole(Role role) {
        return (root, query, cb) -> role == null ? null : cb.equal(root.get("role"), role);
    }

    public static Specification<Users> hasStatus(UserStatus status) {
        return (root, query, cb) -> status == null
                ? null
                : cb.equal(root.get("isActive"), status == UserStatus.ACTIVE);
    }

    public static Specification<Users> matchesSearch(String search) {
        return (root, query, cb) -> {
            if (search == null || search.isBlank()) return null;
            String pattern = "%" + search.trim().toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("name")), pattern),
                    cb.like(cb.lower(root.get("paternalName")), pattern),
                    cb.like(cb.lower(root.get("maternalName")), pattern),
                    cb.like(cb.lower(root.get("email")), pattern)
            );
        };
    }
}
