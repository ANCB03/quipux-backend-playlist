package com.quipux.backend_playlist.security;

import com.quipux.backend_playlist.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SecurityConfigIntegrationTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("filterChain")
    private SecurityFilterChain securityFilterChain;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
        assertThat(passwordEncoder).isNotNull();
        assertThat(securityFilterChain).isNotNull();
        assertThat(jwtTokenProvider).isNotNull();
        assertThat(userRepository).isNotNull();
    }
}