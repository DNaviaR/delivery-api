package com.delivery.api.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private List<Long> productIds; // Solo necesitamos los IDs de las pizzas
}