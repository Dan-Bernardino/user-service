package com.ecommerce.userservice.service;

import com.ecommerce.userservice.model.Rol;
import com.ecommerce.userservice.model.Users;
import com.ecommerce.userservice.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users crearUsuario(Users usuario) {
        if (usersRepository.emailExistente(usuario.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este correo ya tiene una cuenta registrada");
        }
        usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
        usuario.setRol(Rol.AUTENTICADO);
        return usersRepository.save(usuario);
    }
    @GetMapping("/{id}")
    public Users obtenerPorId(Long id){
        return usersRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuario no encontrado"));
    }


}