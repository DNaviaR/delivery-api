package com.delivery.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders") // "order" es palabra reservada en SQL, usa plural
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date; // Fecha y hora del pedido

    @Column(nullable = false)
    private Double total; // El precio total calculado

    // Relación: Muchos pedidos pueden ser de Un usuario
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Relación: Un pedido contiene muchos productos
    @ManyToMany
    @JoinTable(
        name = "order_products", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
}