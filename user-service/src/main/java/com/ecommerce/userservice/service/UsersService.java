package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.UserRequestDTO;
import com.ecommerce.userservice.dto.UserResponseDTO;
import com.ecommerce.userservice.exception.UsersNotFoundException;
import com.ecommerce.userservice.model.Rol;
import com.ecommerce.userservice.model.Users;
import com.ecommerce.userservice.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO crearUsuario(UserRequestDTO userRequest) {
        if (usersRepository.emailExistente(userRequest.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Este correo ya tiene una cuenta registrada");
        }

        Users newUser = new Users();

        newUser.setNombre(userRequest.getNombre());
        newUser.setEmail(userRequest.getEmail());
        newUser.setContrasenia(passwordEncoder.encode(userRequest.getContrasenia()));
        newUser.setRol(Rol.AUTENTICADO);

        Users savedUser = usersRepository.save(newUser);


        UserResponseDTO response = new UserResponseDTO();

        response.setId(savedUser.getId());
        response.setNombre(savedUser.getNombre());
        response.setEmail(savedUser.getEmail());
        response.setRol(savedUser.getRol().toString());

        return response;
    }

    public UserResponseDTO obtenerPorId(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario no encontrado"
                ));

        UserResponseDTO response = new UserResponseDTO();

        response.setId(user.getId());
        response.setNombre(user.getNombre());
        response.setEmail(user.getEmail());
        response.setRol(user.getRol().toString());

        return response;
    }

    public UserResponseDTO modificarUsuario(Long id, UserRequestDTO userRequest){
        Users user = usersRepository.findById(id)
                .orElseThrow(()-> new UsersNotFoundException(id));

        user.setNombre(userRequest.getNombre());
        user.setEmail(userRequest.getEmail());

        Users updateUser = usersRepository.save(user);
        UserResponseDTO response = new UserResponseDTO();

        response.setNombre(updateUser.getNombre());
        response.setEmail(updateUser.getEmail());
        response.setRol(updateUser.getRol().toString());

        return response;
    }

}
