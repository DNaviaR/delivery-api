package com.delivery.api.controller;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.delivery.api.model.Product;
import com.delivery.api.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    // Inyectamos el repositorio (El camarero necesita acceso al almacén)
    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    // 1. Ver el menú (GET)
    @GetMapping
    public List<Product> findAll() {
        return repository.findAll();
    }

    // 2. Crear plato (POST)
    @PostMapping
    public Product create(@Valid @RequestBody Product product) {
        return repository.save(product);
    }
}
