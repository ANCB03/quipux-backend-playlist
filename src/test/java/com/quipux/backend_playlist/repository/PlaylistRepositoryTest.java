package com.quipux.backend_playlist.repository;

import com.quipux.backend_playlist.entity.Playlist;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PlaylistRepositoryTest {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Test
    @DisplayName("Should save and find playlist by name")
    void testSaveAndFindByName() {
        // given
        Playlist playlist = new Playlist();
        playlist.setName("My Test Playlist");
        playlist.setDescription("A test playlist for unit testing");

        // when
        playlistRepository.save(playlist);
        Playlist found = playlistRepository.findByName("My Test Playlist");

        // then
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("My Test Playlist");
        assertThat(found.getDescription()).isEqualTo("A test playlist for unit testing");
    }

    @Test
    @DisplayName("Should find by id using JpaRepository")
    void testFindById() {
        // given
        Playlist playlist = new Playlist();
        playlist.setName("Playlist by Id");
        playlist.setDescription("Testing findById");
        playlistRepository.save(playlist);

        // when
        Optional<Playlist> optional = playlistRepository.findById(playlist.getId());

        // then
        assertThat(optional).isPresent();
        assertThat(optional.get().getName()).isEqualTo("Playlist by Id");
    }

    @Test
    @DisplayName("Should return null when playlist name does not exist")
    void testFindByNameNotFound() {
        Playlist found = playlistRepository.findByName("Non Existing Playlist");
        assertThat(found).isNull();
    }
}
