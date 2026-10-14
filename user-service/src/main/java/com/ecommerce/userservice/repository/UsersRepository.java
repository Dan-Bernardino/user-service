package com.ecommerce.userservice.repository;

import com.ecommerce.userservice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);
    List<Users> findAllByIsActive(Boolean isActive);
}
