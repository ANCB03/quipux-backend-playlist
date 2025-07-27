package com.quipux.backend_playlist.implement;

import com.quipux.backend_playlist.dto.request.PlaylistRequest;
import com.quipux.backend_playlist.dto.request.SongRequest;
import com.quipux.backend_playlist.dto.response.PlaylistResponse;
import com.quipux.backend_playlist.dto.response.SongResponse;
import com.quipux.backend_playlist.entity.Playlist;
import com.quipux.backend_playlist.entity.Song;
import com.quipux.backend_playlist.exception.ResourceNotFoundException;
import com.quipux.backend_playlist.repository.PlaylistRepository;
import com.quipux.backend_playlist.service.SongService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlaylistServiceImplementTest {

    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private SongService songService;

    @InjectMocks
    private PlaylistServiceImplement playlistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should find all playlists")
    void testFindAll() {
        // given
        Playlist playlist = new Playlist();
        playlist.setId(1L);
        playlist.setName("My Playlist");
        playlist.setDescription("Description");

        when(playlistRepository.findAll()).thenReturn(List.of(playlist));

        // when
        List<PlaylistResponse> responses = playlistService.findAll();

        // then
        assertThat(responses).isNotEmpty();
        assertThat(responses.get(0).getName()).isEqualTo("My Playlist");
        verify(playlistRepository).findAll();
    }

    @Test
    @DisplayName("Should find playlist by name")
    void testFindByName() {
        // given
        Playlist playlist = new Playlist();
        playlist.setId(1L);
        playlist.setName("Rock");
        playlist.setDescription("Best rock songs");
        playlist.setSongs(List.of(new Song()));

        when(playlistRepository.findByName("Rock")).thenReturn(playlist);

        // when
        PlaylistResponse response = playlistService.findByName("Rock");

        // then
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("Rock");
        verify(playlistRepository).findByName("Rock");
    }

    @Test
    @DisplayName("Should throw exception when playlist not found")
    void testFindByName_NotFound() {
        when(playlistRepository.findByName("Unknown")).thenReturn(null);

        assertThatThrownBy(() -> playlistService.findByName("Unknown"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Playlist no encontrada");
    }

    @Test
    @DisplayName("Should delete playlist by name")
    void testDeleteByName() {
        // given
        Playlist playlist = new Playlist();
        playlist.setId(1L);
        playlist.setName("Pop");

        when(playlistRepository.findByName("Pop")).thenReturn(playlist);

        // when
        playlistService.deleteByName("Pop");

        // then
        verify(playlistRepository).delete(playlist);
    }

    @Test
    @DisplayName("Should create playlist with songs")
    void testCreate() {
        // given
        PlaylistRequest request = new PlaylistRequest();
        request.setName("Chill");
        request.setDescription("Relax music");

        SongRequest songRequest = new SongRequest();
        songRequest.setTitle("Calm Song");
        songRequest.setArtist("Artist");
        songRequest.setAlbum("Album");
        songRequest.setYear("2024");
        songRequest.setGenre("Ambient");
        request.setSongs(List.of(songRequest));

        Playlist savedPlaylist = new Playlist();
        savedPlaylist.setId(1L);
        savedPlaylist.setName("Chill");
        savedPlaylist.setDescription("Relax music");

        SongResponse songResponse = new SongResponse();
        songResponse.setTitle("Calm Song");
        songResponse.setArtist("Artist");

        when(playlistRepository.save(any(Playlist.class))).thenReturn(savedPlaylist);
        when(songService.save(any(SongRequest.class), eq(savedPlaylist))).thenReturn(songResponse);

        // when
        PlaylistResponse response = playlistService.create(request);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("Chill");
        assertThat(response.getSongs()).hasSize(1);
        assertThat(response.getSongs().getFirst().getTitle()).isEqualTo("Calm Song");

        verify(playlistRepository).save(any(Playlist.class));
        verify(songService).save(any(SongRequest.class), eq(savedPlaylist));
    }
}
