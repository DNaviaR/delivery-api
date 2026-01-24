package com.delivery.api.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
// Importante: importar PasswordEncoder si lo fuéramos a usar ya, 
// pero por ahora guardaremos la password en texto plano para probar (Paso intermedio).

import com.delivery.api.model.User;
import com.delivery.api.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 1. Inyectamos el encriptador

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        // 2. Antes de guardar, encriptamos la contraseña
        String passwordEncriptada = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncriptada);
        
        return userRepository.save(user);
    }
}