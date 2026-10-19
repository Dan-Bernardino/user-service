package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.AdminUserResponseDTO;
import com.ecommerce.userservice.dto.PagedResponse;
import com.ecommerce.userservice.model.Role;
import com.ecommerce.userservice.model.UserStatus;
import com.ecommerce.userservice.service.AdminUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<PagedResponse<AdminUserResponseDTO>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) Role role,
            @RequestParam(required = false) UserStatus status,
            @RequestParam(required = false) String search) {

        return ResponseEntity.ok(adminUserService.findUsers(page, size, role, status, search));
    }
}