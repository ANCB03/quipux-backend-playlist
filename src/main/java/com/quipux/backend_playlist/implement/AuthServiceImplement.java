package com.quipux.backend_playlist.implement;

import com.quipux.backend_playlist.entity.User;
import com.quipux.backend_playlist.exception.EmailExists;
import com.quipux.backend_playlist.exception.InvalidUser;
import com.quipux.backend_playlist.repository.UserRepository;
import com.quipux.backend_playlist.security.JwtTokenProvider;
import com.quipux.backend_playlist.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(String email, String password) {
        log.info("Intentando login para el email: {}", email);
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> jwtTokenProvider.generateToken(user.getEmail(), user.getRoles()))
                .orElseThrow(() -> new InvalidUser("Usuario invalido"));
    }

    @Override
    public void register(String email, String username, String password) {
        log.info("Intentando registrar nuevo usuario con email: {}", email);

        if (Boolean.TRUE.equals(userRepository.existsByEmail(email))) {
            log.error("Intento de registro fallido: el email {} ya está en uso", email);
            throw new EmailExists("El email ya está en uso");
        }

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        user.setRoles(Set.of("USER"));

        userRepository.save(user);

        log.info("Usuario registrado exitosamente con email: {}", email);
    }
}
