package com.quipux.backend_playlist.entity.song;

import com.quipux.backend_playlist.dto.request.SongRequest;
import com.quipux.backend_playlist.entity.Playlist;
import com.quipux.backend_playlist.entity.Song;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongTest {

    @Test
    void testNoArgsConstructor() {
        Song song = new Song();
        assertNotNull(song);
        assertNull(song.getId());
        assertNull(song.getTitle());
        assertNull(song.getArtist());
        assertNull(song.getAlbum());
        assertNull(song.getReleaseYear());
        assertNull(song.getGenre());
        assertNull(song.getPlaylist());
    }

    @Test
    void testAllArgsConstructor() {
        Playlist playlist = new Playlist();
        Song song = new Song(1L, "Title", "Artist", "Album", "2023", "Rock", playlist);

        assertEquals(1L, song.getId());
        assertEquals("Title", song.getTitle());
        assertEquals("Artist", song.getArtist());
        assertEquals("Album", song.getAlbum());
        assertEquals("2023", song.getReleaseYear());
        assertEquals("Rock", song.getGenre());
        assertEquals(playlist, song.getPlaylist());
    }

    @Test
    void testConstructorFromRequest() {
        SongRequest request = new SongRequest();
        request.setTitle("Imagine");
        request.setArtist("John Lennon");
        request.setAlbum("Imagine");
        request.setYear("1971");
        request.setGenre("Rock");

        Playlist playlist = new Playlist();
        playlist.setName("Classic Rock");

        Song song = new Song(request, playlist);

        assertEquals("Imagine", song.getTitle());
        assertEquals("John Lennon", song.getArtist());
        assertEquals("Imagine", song.getAlbum());
        assertEquals("1971", song.getReleaseYear());
        assertEquals("Rock", song.getGenre());
        assertEquals(playlist, song.getPlaylist());
    }

    @Test
    void testSettersAndGetters() {
        Song song = new Song();
        song.setId(100L);
        song.setTitle("Bohemian Rhapsody");
        song.setArtist("Queen");
        song.setAlbum("A Night at the Opera");
        song.setReleaseYear("1975");
        song.setGenre("Rock");

        Playlist playlist = new Playlist();
        playlist.setName("Queen Hits");
        song.setPlaylist(playlist);

        assertEquals(100L, song.getId());
        assertEquals("Bohemian Rhapsody", song.getTitle());
        assertEquals("Queen", song.getArtist());
        assertEquals("A Night at the Opera", song.getAlbum());
        assertEquals("1975", song.getReleaseYear());
        assertEquals("Rock", song.getGenre());
        assertEquals(playlist, song.getPlaylist());
    }

    @Test
    void testToString() {
        Song song = new Song();
        song.setTitle("ToString Test");
        String result = song.toString();
        assertTrue(result.contains("ToString Test"));
    }
}
