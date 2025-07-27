package com.quipux.backend_playlist.entity.song;

import com.quipux.backend_playlist.entity.Playlist;
import com.quipux.backend_playlist.entity.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SongJpaTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    void testPersistAndLoadSong() {
        Playlist playlist = new Playlist();
        playlist.setName("Test Playlist");
        entityManager.persist(playlist);

        Song song = new Song();
        song.setTitle("Test Song");
        song.setArtist("Test Artist");
        song.setAlbum("Test Album");
        song.setReleaseYear("2025");
        song.setGenre("Pop");
        song.setPlaylist(playlist);

        entityManager.persist(song);
        entityManager.flush();

        Song found = entityManager.find(Song.class, song.getId());

        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo("Test Song");
        assertThat(found.getArtist()).isEqualTo("Test Artist");
        assertThat(found.getPlaylist().getName()).isEqualTo("Test Playlist");
    }
}
