package com.quipux.backend_playlist.security;

import com.quipux.backend_playlist.entity.User;
import com.quipux.backend_playlist.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UserRepository userRepository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    private JwtAuthenticationFilter filter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        filter = new JwtAuthenticationFilter(jwtTokenProvider, userRepository);
        SecurityContextHolder.clearContext(); // limpia antes de cada test
    }

    @Test
    void shouldAuthenticateUserWhenValidToken() throws Exception {
        // given
        String token = "valid.jwt.token";
        String email = "test@example.com";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtTokenProvider.validateToken(token)).thenReturn(true);
        when(jwtTokenProvider.getEmail(token)).thenReturn(email);

        User user = new User();
        user.setEmail(email);
        user.setRoles(Set.of("USER"));

        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.of(user));

        // when
        filter.doFilterInternal(request, response, filterChain);

        // then
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        assertThat(authentication).isNotNull();
        assertThat(authentication.getPrincipal()).isEqualTo(email);
        assertThat(authentication.getAuthorities()).hasSize(1);
        assertThat(authentication.getAuthorities().iterator().next().getAuthority()).isEqualTo("USER");

        verify(filterChain).doFilter(request, response);
    }

    @Test
    void shouldNotAuthenticateWhenNoToken() throws Exception {
        // given
        when(request.getHeader("Authorization")).thenReturn(null);

        // when
        filter.doFilterInternal(request, response, filterChain);

        // then
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtTokenProvider, userRepository);
    }

    @Test
    void shouldNotAuthenticateWhenInvalidToken() throws Exception {
        // given
        String token = "invalid.token";
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtTokenProvider.validateToken(token)).thenReturn(false);

        // when
        filter.doFilterInternal(request, response, filterChain);

        // then
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
        verify(filterChain).doFilter(request, response);
        verify(jwtTokenProvider).validateToken(token);
        verifyNoMoreInteractions(jwtTokenProvider);
        verifyNoInteractions(userRepository);
    }

    @Test
    void shouldNotAuthenticateWhenUserNotFound() throws Exception {
        // given
        String token = "valid.token";
        String email = "notfound@example.com";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtTokenProvider.validateToken(token)).thenReturn(true);
        when(jwtTokenProvider.getEmail(token)).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.empty());

        // when
        filter.doFilterInternal(request, response, filterChain);

        // then
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
        verify(filterChain).doFilter(request, response);
    }
}
