package com.quipux.backend_playlist.entity.user;

import com.quipux.backend_playlist.entity.User;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testNoArgsConstructor() {
        User user = new User();
        assertNotNull(user);
        assertNull(user.getId());
        assertNull(user.getEmail());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getRoles());
    }

    @Test
    void testAllArgsConstructor() {
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_ADMIN");

        User user = new User(1L, "test@example.com", "testuser", "securepass", roles);

        assertEquals(1L, user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("testuser", user.getUsername());
        assertEquals("securepass", user.getPassword());
        assertEquals(2, user.getRoles().size());
        assertTrue(user.getRoles().contains("ROLE_USER"));
        assertTrue(user.getRoles().contains("ROLE_ADMIN"));
    }

    @Test
    void testSettersAndGetters() {
        User user = new User();
        user.setId(42L);
        user.setEmail("hello@world.com");
        user.setUsername("helloWorld");
        user.setPassword("123456");

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        user.setRoles(roles);

        assertEquals(42L, user.getId());
        assertEquals("hello@world.com", user.getEmail());
        assertEquals("helloWorld", user.getUsername());
        assertEquals("123456", user.getPassword());
        assertEquals(1, user.getRoles().size());
        assertTrue(user.getRoles().contains("ROLE_USER"));
    }

    @Test
    void testToString() {
        User user = new User();
        user.setUsername("ToStringUser");
        String result = user.toString();
        assertTrue(result.contains("ToStringUser"));
    }
}
