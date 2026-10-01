package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.model.Users;
import com.ecommerce.userservice.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") //Ruta establecida en el PDF
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<Users> crearUsuario(@Valid @RequestBody Users usuario) {

        Users usuarioGuardado = usersService.crearUsuario(usuario);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioGuardado);
    }

    @GetMapping("/{id}")
    public Users obtenerUsuario(@PathVariable Long id){
        return usersService.obtenerPorId(id);
    }
}