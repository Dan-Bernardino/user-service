package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.model.Users;
import com.ecommerce.userservice.repository.UsersRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class UsersController {
    private final UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // Metodo para crear un usuario
    @PostMapping("/users")
    public Users createUsers(@RequestBody Users users){
        return usersRepository.save(users);
    }


}
