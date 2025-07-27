package com.quipux.backend_playlist.service;

import com.quipux.backend_playlist.repository.UserRepository;
import com.quipux.backend_playlist.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface AuthService {
    public String login(String username, String password);
}
