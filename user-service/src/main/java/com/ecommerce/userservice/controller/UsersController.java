package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.UserRequestDTO;
import com.ecommerce.userservice.dto.UserResponseDTO;
import com.ecommerce.userservice.dto.UserStatusDTO;
import com.ecommerce.userservice.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

//    @GetMapping()    <------- OBTENER TODOS LOS USUARIOS
//    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
//
//        List<UserResponseDTO> usersResponse = usersService.findAll();
//
//        return ResponseEntity.ok(usersResponse);
//    }

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
    public ResponseEntity<List<UserResponseDTO>> getAllUsersByRole(@RequestParam(required = true) String role){

        List<UserResponseDTO> usersResponse = usersService.findAllUsersByRole(role);

        return ResponseEntity.ok(usersResponse);
    }

//    @GetMapping("/active")    <------- OBTENER TODOS LOS USUARIOS ACTIVOS
//    public ResponseEntity<List<UserResponseDTO>> getAllUsersActive() {
//
//        List<UserResponseDTO> usersResponse = usersService.findAllUsersByIsActive(true);
//
//        return ResponseEntity.ok(usersResponse);
//    }
}