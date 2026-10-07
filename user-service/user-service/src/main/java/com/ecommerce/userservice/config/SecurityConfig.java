package com.ecommerce.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){

        http
                // Deshabilitar la seguridad basica (csrf)
                .csrf((csrf)-> csrf.disable())
                .authorizeHttpRequests((auth) -> auth
                        .anyRequest()
                        .permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    @Bean //Bean para que regrese el Hash de contraseña, no la contraseña
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}