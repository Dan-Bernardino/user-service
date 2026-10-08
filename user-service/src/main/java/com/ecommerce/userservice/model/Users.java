package com.ecommerce.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor //lo pide jpa
@Entity
@Table(name = "usuarios")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuarios")
    private Long id;

    @NotBlank //Evita cadenas vacias
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotBlank @Email //Evita cadenas vacías, formato válido de email
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank //Evita cadenas vacías
    @Column(name = "contrasenia", nullable = false, length = 255)
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false, length = 45)
    private Rol rol = Rol.CLIENTE;

    @Column(name = "autenticado", nullable = false)
    private Boolean autenticado = false;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @CreationTimestamp //La fecha la agrega hibernate justo antes de guardar Users en la BD
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    //constructor para nuevos usuarios
    public Users(String nombre, String email, String contrasenia, Rol rol, Boolean autenticado, Boolean activo) {
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.autenticado = autenticado;
        this.activo = activo;
    }
}