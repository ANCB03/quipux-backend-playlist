package com.quipux.backend_playlist.repository;

import com.quipux.backend_playlist.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should save and find user by email")
    void testSaveAndFindByEmail() {
        // given
        User user = new User();
        user.setEmail("test@example.com");
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setRoles(Set.of("ROLE_USER", "ROLE_ADMIN"));

        userRepository.save(user);

        // when
        Optional<User> found = userRepository.findByEmail("test@example.com");

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("testuser");
        assertThat(found.get().getRoles()).containsExactlyInAnyOrder("ROLE_USER", "ROLE_ADMIN");
    }

    @Test
    @DisplayName("Should return empty when email does not exist")
    void testFindByEmailNotFound() {
        Optional<User> found = userRepository.findByEmail("notfound@example.com");
        assertThat(found).isEmpty();
    }

    @Test
    @DisplayName("Should return true when user exists by email")
    void testExistsByEmail() {
        // given
        User user = new User();
        user.setEmail("exist@example.com");
        user.setUsername("existuser");
        user.setPassword("pass");
        user.setRoles(Set.of("ROLE_USER"));

        userRepository.save(user);

        // when
        Boolean exists = userRepository.existsByEmail("exist@example.com");

        // then
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Should return false when user does not exist by email")
    void testExistsByEmailNotFound() {
        Boolean exists = userRepository.existsByEmail("unknown@example.com");
        assertThat(exists).isFalse();
    }
}
