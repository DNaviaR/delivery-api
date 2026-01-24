package com.delivery.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.delivery.api.repository.UserRepository;

@Configuration
public class CustomUserDetailsService {

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            // 1. Buscamos nuestro usuario en BBDD
            com.delivery.api.model.User usuarioBBDD = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            // 2. Lo convertimos al usuario que entiende Spring Security
            // (Fíjate que usamos User.builder() de Spring, no el nuestro)
            return User.builder()
                    .username(usuarioBBDD.getUsername())
                    .password(usuarioBBDD.getPassword())
                    .roles(usuarioBBDD.getRole())
                    .build();
        };
    }
}
