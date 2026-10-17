package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.UserRequestDTO;
import com.ecommerce.userservice.dto.UserResponseDTO;
import com.ecommerce.userservice.dto.UserStatusDTO;
import com.ecommerce.userservice.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    // =========================================================
    // Operaciones CRUD
    // =========================================================

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'CLIENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {

        UserResponseDTO userResponse = usersService.findById(id);

        return ResponseEntity.ok(userResponse);
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(usersService.findAll());
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequest) {

        UserResponseDTO usuarioGuardado = usersService.createUser(userRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioGuardado);
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'CLIENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO userRequest) {

        UserResponseDTO usuarioActualizado = usersService.updateUser(id, userRequest);

        return ResponseEntity.ok(usuarioActualizado);
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @PatchMapping("/{id}/activate")
    public ResponseEntity<UserStatusDTO> activateUser(@PathVariable Long id) {

        UserStatusDTO userResponse = usersService.activateAndDeactivateUser(id, true);

        return ResponseEntity.ok(userResponse);
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<UserStatusDTO> deactivateUser(@PathVariable Long id) {

        UserStatusDTO userResponse = usersService.activateAndDeactivateUser(id, false);

        return ResponseEntity.ok(userResponse);
    }

    // =========================================================
    // Consultar por estado isActive
    // =========================================================

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/{id}/isActive")
    public ResponseEntity<UserStatusDTO> getUserIsActive(@PathVariable Long id){

        UserStatusDTO userResponse = usersService.findUserStatusById(id);

        return ResponseEntity.ok(userResponse);
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/active")
    public ResponseEntity<List<UserResponseDTO>> getAllUsersActive() {

        List<UserResponseDTO> usersResponse = usersService.findAllUsersByIsActive(true);

        return ResponseEntity.ok(usersResponse);
    }


    // =========================================================
    // Consultar por Rol
    // =========================================================

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsersByRole(@RequestParam(required = false) String role){

        List<UserResponseDTO> usersResponse = usersService.findAllUsersByRole(role);

        return ResponseEntity.ok(usersResponse);
    }

}