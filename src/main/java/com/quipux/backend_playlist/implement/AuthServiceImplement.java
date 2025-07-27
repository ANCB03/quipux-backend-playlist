package com.quipux.backend_playlist.implement;

import com.quipux.backend_playlist.repository.UserRepository;
import com.quipux.backend_playlist.security.JwtTokenProvider;
import com.quipux.backend_playlist.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
    }
}
