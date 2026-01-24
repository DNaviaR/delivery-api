package com.delivery.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // Usamos "users" para evitar conflictos con palabras reservadas de SQL
@Data
@NoArgsConstructor // Obligatorio para JPA (hibernate)
@AllArgsConstructor // Útil para nosotros (para crear objetos rápido)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // El username no puede repetirse
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // Aquí guardaremos "ADMIN" o "USER"
}
