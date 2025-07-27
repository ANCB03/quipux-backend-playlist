package com.quipux.backend_playlist.implement;

import com.quipux.backend_playlist.entity.User;
import com.quipux.backend_playlist.exception.EmailExists;
import com.quipux.backend_playlist.exception.InvalidUser;
import com.quipux.backend_playlist.repository.UserRepository;
import com.quipux.backend_playlist.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class AuthServiceImplementTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImplement authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should login successfully with correct email and password")
    void testLoginSuccess() {
        // given
        String email = "test@example.com";
        String rawPassword = "password";
        String encodedPassword = "encodedPassword";
        Set<String> roles = Set.of("USER");
        String token = "jwt-token";

        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setRoles(roles);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);
        when(jwtTokenProvider.generateToken(email, roles)).thenReturn(token);

        // when
        String result = authService.login(email, rawPassword);

        // then
        assertThat(result).isEqualTo(token);

        verify(userRepository).findByEmail(email);
        verify(passwordEncoder).matches(rawPassword, encodedPassword);
        verify(jwtTokenProvider).generateToken(email, roles);
    }

    @Test
    @DisplayName("Should throw InvalidUser when password does not match")
    void testLoginInvalidPassword() {
        String email = "test@example.com";
        String rawPassword = "wrongPassword";

        User user = new User();
        user.setEmail(email);
        user.setPassword("encodedPassword");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(rawPassword, "encodedPassword")).thenReturn(false);

        // when + then
        assertThatThrownBy(() -> authService.login(email, rawPassword))
                .isInstanceOf(InvalidUser.class)
                .hasMessage("Usuario invalido");

        verify(userRepository).findByEmail(email);
        verify(passwordEncoder).matches(rawPassword, "encodedPassword");
        verify(jwtTokenProvider, never()).generateToken(any(), any());
    }

    @Test
    @DisplayName("Should throw InvalidUser when email not found")
    void testLoginEmailNotFound() {
        String email = "unknown@example.com";
        String password = "password";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authService.login(email, password))
                .isInstanceOf(InvalidUser.class)
                .hasMessage("Usuario invalido");

        verify(userRepository).findByEmail(email);
        verify(passwordEncoder, never()).matches(any(), any());
        verify(jwtTokenProvider, never()).generateToken(any(), any());
    }

    @Test
    @DisplayName("Should register new user successfully")
    void testRegisterSuccess() {
        String email = "new@example.com";
        String username = "newuser";
        String rawPassword = "password";
        String encodedPassword = "encodedPassword";

        when(userRepository.existsByEmail(email)).thenReturn(false);
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        // when
        authService.register(email, username, rawPassword);

        // then
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertThat(savedUser.getEmail()).isEqualTo(email);
        assertThat(savedUser.getUsername()).isEqualTo(username);
        assertThat(savedUser.getPassword()).isEqualTo(encodedPassword);
        assertThat(savedUser.getRoles()).containsExactly("USER");
    }

    @Test
    @DisplayName("Should throw EmailExists when email already registered")
    void testRegisterEmailExists() {
        String email = "existing@example.com";

        when(userRepository.existsByEmail(email)).thenReturn(true);

        assertThatThrownBy(() -> authService.register(email, "username", "password"))
                .isInstanceOf(EmailExists.class)
                .hasMessage("El email ya está en uso");

        verify(userRepository, never()).save(any());
    }
}
