package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.LoginRequestDTO;
import com.ecommerce.userservice.exception.LoginFailedException;
import com.ecommerce.userservice.model.Users;
import com.ecommerce.userservice.repository.UsersRepository;
import com.ecommerce.userservice.security.config.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.LoginException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsersRepository usersRepository;
    private final Clock clock;
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final int LOCK_DURATION_MINUTES = 5;

    public LoginService(AuthenticationManager authenticationManager,
                        JwtUtil jwtUtil, UsersRepository usersRepository, Clock clock){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.usersRepository = usersRepository;
        this.clock = clock;
    }

    @Transactional(noRollbackFor = LoginFailedException.class)
    public String login(LoginRequestDTO login) {
        LocalDateTime now = LocalDateTime.now(clock);
        Optional<Users> optionalUser = usersRepository.findByEmail(login.email());

        //Valida si la búsqueda por email devolvió un user
        if(optionalUser.isPresent()){
            Users user = optionalUser.get();

            if(isUserCurrentlyLocked(user, now)) throw new LoginFailedException();

            //Aplica para usuarios con bloqueo expirado
            if(hasExpiredLock(user, now)) resetBlockedState(user);
        }
        //Creamos un token usando las credenciales que ingresó el usuario en el login
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
        try {
            Authentication authentication = this.authenticationManager.authenticate(localToken);

            //Ingresar mal el email o contraseña incrementa los intentos fallidos de login
        }catch (BadCredentialsException e){
            optionalUser.ifPresent(user -> registerFailedAttempt(user, now));
            throw new LoginFailedException();

            //Login fallido por cuenta inactiva, bloqueada, etc. no incrementa los intentos fallidos de login
        }catch (AuthenticationException e){
            throw new LoginFailedException();
        }

        optionalUser.ifPresent(user -> {
            //Aplica para usuarios sin bloqueo pero con intentos fallidos de login
            //ó sin bloqueo y con 0 intentos fallidos de login
            resetBlockedState(user);
            usersRepository.save(user);
        });

        //Creamos el JWT usando las credenciales obtenidas de la BD
        String jwt = jwtUtil.create(login.email());

        return jwt;
    }

    //Verifica si el usuario está bloqueado temporalmente
    private Boolean isUserCurrentlyLocked(Users user, LocalDateTime now){
        return user.getLockedUntil() != null && user.getLockedUntil().isAfter(now);
    }

    //Verifica si el usuario sí estaba bloqueado temporalmente y si ya pasó su bloqueo
    private  Boolean hasExpiredLock(Users user, LocalDateTime now){
        return user.getLockedUntil() != null && !user.getLockedUntil().isAfter(now);
    }

    //Resetear a 0 los intentos de login fallidos y fecha de expiración de bloqueo a null
    private void resetBlockedState(Users user){
        user.setFailedLoginAttempts(0);
        user.setLockedUntil(null);
    }

    //Incrementa los intentos de login fallido y verifica si debe BLOQUEAR temporalmente al usuario
    private void registerFailedAttempt(Users user, LocalDateTime now){
        int currentAttempts = user.getFailedLoginAttempts();
        int updateAttempts = Math.min(currentAttempts + 1, MAX_FAILED_ATTEMPTS);

        user.setFailedLoginAttempts(updateAttempts);

        if(user.getFailedLoginAttempts() == MAX_FAILED_ATTEMPTS) {
            user.setLockedUntil(
                    now.plusMinutes(LOCK_DURATION_MINUTES));
        }

        usersRepository.save(user);
    }
}
