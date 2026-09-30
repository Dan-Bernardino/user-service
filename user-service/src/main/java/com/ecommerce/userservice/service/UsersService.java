package com.ecommerce.userservice.service;

import com.ecommerce.userservice.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    private final com.ecommerce.userservice.repository.UsersRepository userRepository;

    @Autowired
    public UsersService(com.ecommerce.userservice.repository.UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public Users crearUsers(Users users) {
        return userRepository.save(users);
    }

}
