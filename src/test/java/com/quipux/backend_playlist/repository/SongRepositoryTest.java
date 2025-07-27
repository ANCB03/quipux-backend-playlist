package com.quipux.backend_playlist.repository;

import com.quipux.backend_playlist.entity.Playlist;
import com.quipux.backend_playlist.entity.Song;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SongRepositoryTest {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Test
    @DisplayName("Should save and find song by id")
    void testSaveAndFindById() {
        // given
        Playlist playlist = new Playlist();
        playlist.setName("My Playlist");
        playlist.setDescription("Playlist for testing songs");
        playlistRepository.save(playlist);

        Song song = new Song();
        song.setTitle("Test Song");
        song.setArtist("Test Artist");
        song.setAlbum("Test Album");
        song.setReleaseYear("2024");
        song.setGenre("Rock");
        song.setPlaylist(playlist);

        // when
        songRepository.save(song);
        Optional<Song> found = songRepository.findById(song.getId());

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Test Song");
        assertThat(found.get().getArtist()).isEqualTo("Test Artist");
        assertThat(found.get().getPlaylist().getName()).isEqualTo("My Playlist");
    }

    @Test
    @DisplayName("Should find all songs")
    void testFindAll() {
        // given
        Playlist playlist = new Playlist();
        playlist.setName("Another Playlist");
        playlistRepository.save(playlist);

        Song song1 = new Song();
        song1.setTitle("Song One");
        song1.setPlaylist(playlist);

        Song song2 = new Song();
        song2.setTitle("Song Two");
        song2.setPlaylist(playlist);

        songRepository.save(song1);
        songRepository.save(song2);

        // when
        List<Song> songs = songRepository.findAll();

        // then
        assertThat(songs).hasSize(2);
        assertThat(songs).extracting(Song::getTitle)
                .containsExactlyInAnyOrder("Song One", "Song Two");
    }

    @Test
    @DisplayName("Should return empty when song id not exists")
    void testFindByIdNotFound() {
        Optional<Song> found = songRepository.findById(999L);
        assertThat(found).isEmpty();
    }
}
