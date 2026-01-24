package com.delivery.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data; // Import de Lombok
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "products")
@Data // Genera Getters, Setters, toString, etc. automáticamente
@NoArgsConstructor // Constructor vacío (Obligatorio para JPA)
@AllArgsConstructor // Constructor con todos los argumentos
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    private String description;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private Double price;
}
