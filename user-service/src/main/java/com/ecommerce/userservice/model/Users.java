package com.ecommerce.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuarios")
    private Long id;

    @NotBlank //Evita cadenas vacias
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank @Email //Evita cadenas vacías, formato válido de email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank //Evita cadenas vacías
    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Rol rol = Rol.CLIENTE;

    @Column(name = "autenticado", nullable = false)
    private Boolean autenticado;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    //lo pide jpa
    public Users() {
    }

    //constructor para nuevos usuarios
    public Users(String nombre, String email, String contrasenia, Rol rol, Boolean autenticado, Boolean activo) {
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.autenticado = autenticado;
        this.fechaCreacion = LocalDateTime.now();
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Boolean getAutenticado() { return autenticado; }

    public void setAutenticado(Boolean autenticado) { this.autenticado = autenticado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public Boolean getActivo() { return activo; }

    public void setActivo(Boolean activo) { this.activo = activo; }
}