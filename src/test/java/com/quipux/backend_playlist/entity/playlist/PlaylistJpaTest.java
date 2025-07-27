package com.quipux.backend_playlist.entity.playlist;

import com.quipux.backend_playlist.entity.Playlist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PlaylistJpaTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    void testPersistAndLoadPlaylist() {
        Playlist playlist = new Playlist();
        playlist.setName("Integration Test Playlist");
        playlist.setDescription("Testing persistence");

        entityManager.persist(playlist);
        entityManager.flush();

        Playlist found = entityManager.find(Playlist.class, playlist.getId());

        assertThat(found).isNotNull();
        assertThat(found.getId()).isNotNull();
        assertThat(found.getName()).isEqualTo("Integration Test Playlist");
        assertThat(found.getDescription()).isEqualTo("Testing persistence");
    }
}