package com.ecommerce.userservice.service;

import com.ecommerce.userservice.model.Users;
import com.ecommerce.userservice.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users crearUsuario(Users usuario) {
        return usersRepository.save(usuario);
    }

    public void borrarUsuario(long id){
        usersRepository.deleteById(id);
    }
}