package com.quipux.backend_playlist.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class JwtTokenProviderTest {

    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setUp() {
        jwtTokenProvider = new JwtTokenProvider(
                "mySuperSecretKeymySuperSecretKeymySuperSecretKey12",
                3600000
        );
    }

    @Test
    void shouldGenerateAndValidateToken() {
        // given
        String email = "test@example.com";
        Set<String> roles = Set.of("USER");

        // when
        String token = jwtTokenProvider.generateToken(email, roles);

        // then
        assertThat(token).isNotBlank();

        // validateToken debe devolver true
        assertThat(jwtTokenProvider.validateToken(token)).isTrue();

        // getEmail debe devolver el mismo email
        String extractedEmail = jwtTokenProvider.getEmail(token);
        assertThat(extractedEmail).isEqualTo(email);
    }

    @Test
    void shouldInvalidateTamperedToken() {
        // given
        String email = "test@example.com";
        Set<String> roles = Set.of("USER");
        String token = jwtTokenProvider.generateToken(email, roles);

        // Modificamos el token para invalidarlo
        String tamperedToken = token + "a";

        // when
        boolean isValid = jwtTokenProvider.validateToken(tamperedToken);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    void shouldInvalidateEmptyToken() {
        assertThat(jwtTokenProvider.validateToken("")).isFalse();
        assertThat(jwtTokenProvider.validateToken(null)).isFalse();
    }
}
