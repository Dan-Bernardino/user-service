package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.UserRequestDTO;
import com.ecommerce.userservice.dto.UserResponseDTO;
import com.ecommerce.userservice.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/") //Ruta establecida en el PDF
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/{id}")
    public UserResponseDTO obtenerUsuario(@PathVariable Long id){

        return usersService.obtenerUsuarioPorId(id);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> crearUsuario(@RequestBody UserRequestDTO userRequest) {

        UserResponseDTO usuarioGuardado = usersService.crearUsuario(userRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioGuardado);
    }

    @PutMapping
    public ResponseEntity<UserResponseDTO> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody UserRequestDTO userRequest){

        UserResponseDTO usuarioActualizado = usersService.actualizarUsuario(id, userRequest);

        return ResponseEntity.ok(usuarioActualizado);
    }
}