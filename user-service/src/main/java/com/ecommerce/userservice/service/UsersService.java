package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.UserRequestDTO;
import com.ecommerce.userservice.dto.UserResponseDTO;
import com.ecommerce.userservice.exception.EmailAlreadyExistsException;
import com.ecommerce.userservice.exception.UsersNotFoundException;
import com.ecommerce.userservice.model.Rol;
import com.ecommerce.userservice.model.Users;
import com.ecommerce.userservice.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;


    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO crearUsuario(UserRequestDTO userRequest) {
        if (usersRepository.existsByEmail(userRequest.email())){
            throw new EmailAlreadyExistsException(
                    "El correo ya se encuentra registrado"
            );
        }

        Users newUser = new Users();

        newUser.setNombre(userRequest.nombre());
        newUser.setEmail(userRequest.email());
        newUser.setContrasenia(passwordEncoder.encode(userRequest.contrasenia()));
        newUser.setRol(Rol.CLIENTE);
        newUser.setAutenticado(false);

        Users savedUser = usersRepository.save(newUser);


        UserResponseDTO response = new UserResponseDTO(
                savedUser.getId(),
                savedUser.getNombre(),
                savedUser.getEmail(),
                savedUser.getRol().toString(),
                savedUser.getAutenticado()
        );

        return response;
    }

    public UserResponseDTO obtenerUsuarioPorId(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario no encontrado"
                ));

        UserResponseDTO response = new UserResponseDTO(
                user.getId(),
                user.getNombre(),
                user.getEmail(),
                user.getRol().name(),
                user.getAutenticado()
        );

        return response;
    }

    public UserResponseDTO actualizarUsuario(Long id, UserRequestDTO userRequest){
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new UsersNotFoundException(
                        "Usuario no encontrado"
                ));

        if(!userRequest.nombre().isEmpty()) {
            user.setNombre(userRequest.nombre());
        }
        if(!userRequest.email().isEmpty()) {
            user.setEmail(userRequest.email());
        }

        Users updatedUser = usersRepository.save(user);
        UserResponseDTO response = new UserResponseDTO(
                updatedUser.getId(),
                updatedUser.getNombre(),
                updatedUser.getEmail(),
                updatedUser.getRol().name(),
                updatedUser.getAutenticado()
        );

        return response;
    }

}
