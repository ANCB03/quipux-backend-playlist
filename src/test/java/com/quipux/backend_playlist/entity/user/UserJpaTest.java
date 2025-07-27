package com.quipux.backend_playlist.entity.user;

import com.quipux.backend_playlist.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserJpaTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    void testPersistAndLoadUser() {
        User user = new User();
        user.setEmail("jpa@example.com");
        user.setUsername("jpaUser");
        user.setPassword("password123");
        user.setRoles(Set.of("ROLE_USER", "ROLE_ADMIN"));

        entityManager.persist(user);
        entityManager.flush();

        User found = entityManager.find(User.class, user.getId());

        assertThat(found).isNotNull();
        assertThat(found.getUsername()).isEqualTo("jpaUser");
        assertThat(found.getEmail()).isEqualTo("jpa@example.com");
        assertThat(found.getRoles()).containsExactlyInAnyOrder("ROLE_USER", "ROLE_ADMIN");
    }
}