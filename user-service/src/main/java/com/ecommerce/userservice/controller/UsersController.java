package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.UserRequestDTO;
import com.ecommerce.userservice.dto.UserResponseDTO;
import com.ecommerce.userservice.dto.UserRoleRequestDTO;
import com.ecommerce.userservice.dto.UserStatusDTO;
import com.ecommerce.userservice.model.Users;
import com.ecommerce.userservice.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    // --- CRUD Operations ---

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {

        UserResponseDTO userResponse = usersService.findById(id);

        return ResponseEntity.ok(userResponse);
    }

    //Obtener todos los usuarios
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

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO userRequest) {

        UserResponseDTO usuarioActualizado = usersService.updateUser(id, userRequest);

        return ResponseEntity.ok(usuarioActualizado);
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<UserStatusDTO> activateUser(@PathVariable Long id) {

        UserStatusDTO userResponse = usersService.activateAndDeactivateUser(id, true);

        return ResponseEntity.ok(userResponse);
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<UserStatusDTO> deactivateUser(@PathVariable Long id) {

        UserStatusDTO userResponse = usersService.activateAndDeactivateUser(id, false);

        return ResponseEntity.ok(userResponse);
    }

    // --- Consultar estado isActive de User ---

    @GetMapping("/{id}/isActive")
    public ResponseEntity<UserStatusDTO> getUserStatus(@PathVariable Long id){

        UserStatusDTO userResponse = usersService.findUserStatusById(id);

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsersByRole(@RequestParam(required = false) String role){

        List<UserResponseDTO> usersResponse = usersService.findAllUsersByRole(role);

        return ResponseEntity.ok(usersResponse);
    }

    // Usuarios activos generales
    @GetMapping("/active")
    public ResponseEntity<List<UserResponseDTO>> getAllUsersActive() {

        List<UserResponseDTO> usersResponse = usersService.findAllUsersByIsActive(true);

        return ResponseEntity.ok(usersResponse);
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<UserResponseDTO> updateUserRole(@PathVariable Long id, @Valid @RequestBody UserRoleRequestDTO request){
        UserResponseDTO response = usersService.updateUserRole(id, request.role());
        return ResponseEntity.ok(response);
    }

}