package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.AdminUserResponseDTO;
import com.ecommerce.userservice.dto.PagedResponse;
import com.ecommerce.userservice.model.Role;
import com.ecommerce.userservice.model.UserStatus;
import com.ecommerce.userservice.model.Users;
import com.ecommerce.userservice.repository.UserSpecifications;
import com.ecommerce.userservice.repository.UsersRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminUserService {

    private static final int MAX_PAGE_SIZE = 5;

    private final UsersRepository usersRepository;

    public AdminUserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional(readOnly = true)
    public PagedResponse<AdminUserResponseDTO> findUsers(
            int page, int size, Role role, UserStatus status, String search) {

        if (page < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "page no puede ser negativo");
        }
        if (size < 1 || size > MAX_PAGE_SIZE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "size debe estar entre 1 y " + MAX_PAGE_SIZE);
        }

        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Order.desc("createdAt"), Sort.Order.desc("id")));

        Specification<Users> spec = Specification.allOf(
                UserSpecifications.hasRole(role),
                UserSpecifications.hasStatus(status),
                UserSpecifications.matchesSearch(search));

        return PagedResponse.from(
                usersRepository.findAll(spec, pageable).map(AdminUserResponseDTO::from));
    }
}