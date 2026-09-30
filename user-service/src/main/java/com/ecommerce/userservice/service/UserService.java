package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.UserRequestDTO;
import com.ecommerce.userservice.dto.UserResponseDTO;
import com.ecommerce.userservice.model.Users;
import com.ecommerce.userservice.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequest) {

        Users newUser = new Users();

        newUser.setNombre(userRequest.getNombre());
        newUser.setEmail(userRequest.getEmail());
        newUser.setContrasenia(userRequest.getContrasenia());

        Users savedUser = usersRepository.save(newUser);

        UserResponseDTO response = new UserResponseDTO();

        response.setId(savedUser.getId());
        response.setNombre(savedUser.getNombre());
        response.setEmail(savedUser.getEmail());
        response.setRol(savedUser.getRol().toString());

        return response;
    }
}
