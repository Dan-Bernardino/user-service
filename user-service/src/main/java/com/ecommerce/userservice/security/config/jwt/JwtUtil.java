package com.ecommerce.userservice.security.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    @Value("${JWT_SECRET}")
    private static String SECRET_KEY="1dze3mXdb3SzOjU4UQQ7242hbvNuBtZTgt3sRsH9YmU=";
    //Algoritmo que se usara para crear el JWT
    private static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    //Creación del Jwt
    public String create(String username) {
        return JWT.create()
                //Se agrega el email
                .withSubject(username)
                //Se agrega quien emitio este JWT
                .withIssuer("Users-MS")
                //Se agrega fecha de emision
                .withIssuedAt(new Date())
                //Se agrega la fecha de expiracion
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                //Se genera la firma
                .sign(ALGORITHM);
    }

    //Verifica si un jwt es valido, será usado en JwtFilter
    public boolean isValid(String jwt){
        try{
            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);
            return true;
        } catch(JWTVerificationException e){
            return false;
        }
    }

    //Extraemos el username del Jwt
    public String getUsername(String jwt){
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();
    }

}
