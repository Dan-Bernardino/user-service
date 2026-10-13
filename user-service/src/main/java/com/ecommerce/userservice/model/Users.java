package com.ecommerce.userservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor //lo pide jpa
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "paternal_lastname", nullable = false, length = 100)
    private String paternalName;

    @Column(name = "maternal_lastname", nullable = false, length = 100)
    private String maternalName;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 45)
    private Role role = Role.CLIENTE;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp //La fecha la agrega hibernate justo antes de guardar Users en la BD
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    //constructor para nuevos usuarios
    public Users(String name, String paternalName, String maternalName, String email, String password, Role role, Boolean isActive) {
        this.paternalName = name;
        this.maternalName = paternalName;
        this.name = maternalName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }
}