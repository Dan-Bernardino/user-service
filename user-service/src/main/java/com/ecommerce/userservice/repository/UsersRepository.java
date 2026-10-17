package com.ecommerce.userservice.repository;

import com.ecommerce.userservice.model.Role;
import com.ecommerce.userservice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    boolean existsByEmail(String email);

    Optional<Users> findByEmail(String email);

    List<Users> findAllByRole(Role role);

    List<Users> findAllByIsActive(Boolean isActive);

    //Existe este email en la BD y le pertenece a otro usuario?
    boolean existsByEmailAndIdNot(String email, Long id);

}
