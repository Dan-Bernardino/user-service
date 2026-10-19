package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.LoginRequestDTO;
import com.ecommerce.userservice.service.LoginService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequestDTO loginRequest) {

        //Si recibimos el jwt, la authentication fue exitosa
        String jwt = loginService.login(loginRequest);
        //Devolvemos un Body vacio, el jwt en el Header y un estado 200
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }
}