package com.delivery.api.controller;

import com.delivery.api.dto.OrderRequest;
import com.delivery.api.model.Order;
import com.delivery.api.model.Product;
import com.delivery.api.model.User;
import com.delivery.api.repository.OrderRepository;
import com.delivery.api.repository.ProductRepository;
import com.delivery.api.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    // Inyectamos LOS TRES repositorios (necesitamos acceder a todo)
    public OrderController(OrderRepository orderRepository, 
                           UserRepository userRepository, 
                           ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @PostMapping
    public Order createOrder(@RequestBody OrderRequest request, Authentication authentication) {
        
        // 1. OBTENER USUARIO: ¿Quién hace el pedido?
        // authentication.getName() nos da el username del que hizo login (ej. "pepe_cliente")
        User usuario = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. BUSCAR PRODUCTOS: Convertimos los IDs [1, 2] en objetos Product reales
        List<Product> productos = productRepository.findAllById(request.getProductIds());

        // 3. CALCULAR TOTAL: Sumamos los precios
        // Usamos un stream para sumar rápido. (Si no sabes streams, es como un bucle for automático)
        Double total = productos.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        // 4. CREAR EL PEDIDO
        Order order = new Order();
        order.setUser(usuario);         // Asignamos el dueño
        order.setDate(LocalDateTime.now()); // Fecha actual
        order.setProducts(productos);   // La lista de pizzas
        order.setTotal(total);          // El precio calculado

        // 5. GUARDAR Y DEVOLVER
        return orderRepository.save(order);
    }

    // Extra: Ver mis pedidos
    @GetMapping
    public List<Order> myOrders(Authentication authentication) {
        // Buscamos solo los pedidos de ESTE usuario
        return orderRepository.findByUserUsername(authentication.getName());
    }
}