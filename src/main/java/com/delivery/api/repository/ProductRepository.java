package com.delivery.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery.api.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // ¡Listo! Ya tenemos save(), findAll(), delete(), etc.
}
