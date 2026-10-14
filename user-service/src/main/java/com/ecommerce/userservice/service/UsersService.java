package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.UserRequestDTO;
import com.ecommerce.userservice.dto.UserResponseDTO;
import com.ecommerce.userservice.dto.UserStatusDTO;
import com.ecommerce.userservice.exception.EmailAlreadyExistsException;
import com.ecommerce.userservice.exception.RoleNotFoundException;
import com.ecommerce.userservice.exception.UsersNotFoundException;
import com.ecommerce.userservice.model.Role;
import com.ecommerce.userservice.model.Users;
import com.ecommerce.userservice.repository.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    //Creación de usuarios
    public UserResponseDTO createUser(UserRequestDTO userRequest) {
        if (usersRepository.existsByEmail(userRequest.email())){
            throw new EmailAlreadyExistsException(
                    "El correo ya se encuentra registrado"
            );
        }

        Users newUser = new Users();

        newUser.setName(userRequest.name());
        newUser.setPaternalName(userRequest.paternalName());
        newUser.setMaternalName(userRequest.maternalName());
        newUser.setEmail(userRequest.email());
        newUser.setPassword(passwordEncoder.encode(userRequest.password()));
        newUser.setRole(Role.CLIENTE);

        Users savedUser = usersRepository.save(newUser);


        UserResponseDTO response = new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName() + " " + savedUser.getPaternalName() + " " + savedUser.getMaternalName(),
                savedUser.getEmail(),
                savedUser.getRole().toString(),
                savedUser.getIsActive(),
                savedUser.getCreatedAt()
        );

        return response;
    }

    //Obtención de usuario por ID
    public UserResponseDTO findById(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new UsersNotFoundException(
                        "Usuario no encontrado"
                        ));

        UserResponseDTO response = new UserResponseDTO(
                user.getId(),
                user.getName() + " " + user.getPaternalName() + " " + user.getMaternalName(),
                user.getEmail(),
                user.getRole().name(),
                user.getIsActive(),
                user.getCreatedAt()
        );

        return response;
    }

    //Búsqueda de usuario por email
    public UserResponseDTO findByEmail(String email) {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsersNotFoundException(
                        "Usuario no encontrado"
                ));

        UserResponseDTO response = new UserResponseDTO(
                user.getId(),
                user.getName() + " " + user.getPaternalName() + " " + user.getMaternalName(),
                user.getEmail(),
                user.getRole().name(),
                user.getIsActive(),
                user.getCreatedAt()
        );

        return response;
    }

    //Actualización de datos de usuario
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequest){
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new UsersNotFoundException(
                        "Usuario no encontrado"
                ));

        if(!userRequest.name().isEmpty()) {
            user.setName(userRequest.name());
        }
        if(!userRequest.paternalName().isEmpty()) {
            user.setPaternalName(userRequest.paternalName());
        }
        if(!userRequest.maternalName().isEmpty()) {
            user.setMaternalName(userRequest.maternalName());
        }
        if(!userRequest.email().isEmpty()) {
            user.setEmail(userRequest.email());
        }
        if (!userRequest.password().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userRequest.password())
            );
        }


        Users updatedUser = usersRepository.save(user);
        UserResponseDTO response = new UserResponseDTO(
                updatedUser.getId(),
                updatedUser.getName() + " " + updatedUser.getPaternalName() + " " + updatedUser.getMaternalName(),
                updatedUser.getEmail(),
                updatedUser.getRole().name(),
                updatedUser.getIsActive(),
                updatedUser.getCreatedAt()
        );

        return response;
    }

    //Actualización de usuarios por ID
    public UserStatusDTO activateAndDeactivateUser(Long id, Boolean isActive){
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new UsersNotFoundException("Usuario no encontrado"));

        user.setIsActive(isActive);
        Users updatedUser = usersRepository.save(user);

        UserStatusDTO response = new UserStatusDTO(
                updatedUser.getId(),
                updatedUser.getIsActive()
        );

        return response;
    }

    public UserStatusDTO findUserStatusById(Long id){
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new UsersNotFoundException("Usuario no encontrado"));

        UserStatusDTO response = new UserStatusDTO(
                user.getId(),
                user.getIsActive()
        );

        return response;
    }

    public List<UserResponseDTO> findAllUsersByRole(String role){

        Role roleToEnum;

        try {
            roleToEnum = Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new RoleNotFoundException("El rol no es válido: " + role);
        }

        List<Users> users = usersRepository.findAllByRole(roleToEnum);

        //Convertimos cada elemento Users de la lista en un UserResponseDTO
        return users.stream()
                .map(user -> new UserResponseDTO(
                            user.getId(),
                            user.getName() + " " + user.getPaternalName() + " " + user.getMaternalName(),
                            user.getEmail(),
                            user.getRole().name(),
                            user.getIsActive(),
                            user.getCreatedAt()
                ))
                .toList();
    }

    // Obetner ususarios activos general
    public List<UserResponseDTO> findAllUsersByIsActive(Boolean isActive) {

        List<Users> users = usersRepository.findAllByIsActive(isActive);

        return users.stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getName() + " " + user.getPaternalName() + " " + user.getMaternalName(),
                        user.getEmail(),
                        user.getRole().name(),
                        user.getIsActive(),
                        user.getCreatedAt()
                ))
                .toList();
    }

}
