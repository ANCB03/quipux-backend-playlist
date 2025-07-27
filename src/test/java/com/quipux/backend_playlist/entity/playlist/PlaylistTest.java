package com.quipux.backend_playlist.entity.playlist;

import com.quipux.backend_playlist.dto.request.PlaylistRequest;
import com.quipux.backend_playlist.entity.Playlist;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {

    @Test
    void testNoArgsConstructor() {
        Playlist playlist = new Playlist();
        assertNotNull(playlist);
        assertNull(playlist.getId());
        assertNull(playlist.getName());
        assertNull(playlist.getDescription());
        assertNotNull(playlist.getSongs());
        assertTrue(playlist.getSongs().isEmpty());
    }

    @Test
    void testAllArgsConstructor() {
        Playlist playlist = new Playlist(1L, "My Playlist", "Some description", null);
        assertEquals(1L, playlist.getId());
        assertEquals("My Playlist", playlist.getName());
        assertEquals("Some description", playlist.getDescription());
        assertNull(playlist.getSongs());
    }

    @Test
    void testConstructorFromRequest() {
        PlaylistRequest request = new PlaylistRequest();
        request.setName("Chill Vibes");
        request.setDescription("Relax and study");

        Playlist playlist = new Playlist(request);

        assertEquals("Chill Vibes", playlist.getName());
        assertEquals("Relax and study", playlist.getDescription());
        assertNotNull(playlist.getSongs());
        assertTrue(playlist.getSongs().isEmpty());
    }

    @Test
    void testSettersAndGetters() {
        Playlist playlist = new Playlist();
        playlist.setId(100L);
        playlist.setName("Workout");
        playlist.setDescription("Energetic songs");

        assertEquals(100L, playlist.getId());
        assertEquals("Workout", playlist.getName());
        assertEquals("Energetic songs", playlist.getDescription());
    }

    @Test
    void testToString() {
        Playlist playlist = new Playlist();
        playlist.setName("ToString Test");
        String toString = playlist.toString();
        assertTrue(toString.contains("ToString Test"));
    }
}
