package com.quipux.backend_playlist.config;

import com.quipux.backend_playlist.entity.User;
import com.quipux.backend_playlist.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {
        if (userRepository.count() == 0) {
            // Crear usuario admin
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setRoles(Set.of("ROLE_ADMIN"));
            userRepository.save(admin);

            // Crear usuario normal
            User user = new User();
            user.setUsername("user");
            user.setEmail("user@gmail.com");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setRoles(Set.of("ROLE_USER"));
            userRepository.save(user);

            System.out.println("Datos iniciales creados exitosamente");
        }
    }
}