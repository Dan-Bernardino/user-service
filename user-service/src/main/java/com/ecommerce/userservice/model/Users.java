package com.ecommerce.userservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuarios")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Rol rol = Rol.INVITADO;

<<<<<<< HEAD
=======
    //lo pide jpa
>>>>>>> 3df0aa3ae751343bdfcf3e0fa8e98af17733c6bc
    public Users() {
    }

    //constructor para nuevos usuarios
    public Users(String nombre, String email, String contrasenia) {
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public Long getId() {
        return id;
    }

<<<<<<< HEAD
    public void setId(long id) {
        this.id = id;
    }

=======
>>>>>>> 3df0aa3ae751343bdfcf3e0fa8e98af17733c6bc
    public String getNombre() {
        return nombre;
    }

<<<<<<< HEAD
    public void setNombre(String name) {
        this.nombre = name;
=======
    public void setNombre(String nombre) {
        this.nombre = nombre;
>>>>>>> 3df0aa3ae751343bdfcf3e0fa8e98af17733c6bc
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

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", rol=" + rol +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users users)) return false;
        return id != null && id.equals(users.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}