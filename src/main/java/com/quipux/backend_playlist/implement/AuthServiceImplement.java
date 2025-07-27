package com.quipux.backend_playlist.implement;

import com.quipux.backend_playlist.entity.User;
import com.quipux.backend_playlist.exception.EmailExists;
import com.quipux.backend_playlist.exception.InvalidUser;
import com.quipux.backend_playlist.repository.UserRepository;
import com.quipux.backend_playlist.security.JwtTokenProvider;
import com.quipux.backend_playlist.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> jwtTokenProvider.generateToken(user.getEmail(), user.getRoles()))
                .orElseThrow(() -> new InvalidUser("Usuario invalido"));
    }

    @Override
    public void register(String email, String username, String password) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(email))) {
            throw new EmailExists("El email ya está en uso");
        }

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        user.setRoles(Set.of("USER"));

        userRepository.save(user);
    }
}
