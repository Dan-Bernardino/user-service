package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.LoginRequestDTO;
import com.ecommerce.userservice.security.config.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public LoginService(AuthenticationManager authenticationManager,
                        JwtUtil jwtUtil){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public String login(LoginRequestDTO login) {
        //Creamos un token usando las credenciales que mandó el usuario en el login
        UsernamePasswordAuthenticationToken localToken =
                new UsernamePasswordAuthenticationToken(login.email(), login.password());

		/*
		Si se obtiene un valor es porque se logró la autenticación
		Si se logró, tenemos acceso métodos:
			authentication.isAuthenticated()
			authentication.getPrincipal()
				-> devuelve User[ username, password, enabled, accountNonExpired,
												  CredentialNonExpired, AccountNonLocked,
												  GrantedAuthorities[ROLE_ADMIN, random_access] ]
		*/
        Authentication authentication = this.authenticationManager.authenticate(localToken);

        //Creamos el JWT usando las credenciales obtenidas de la BD
        String jwt = jwtUtil.create(login.email());

        return jwt;
    }
}
