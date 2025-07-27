package com.quipux.backend_playlist.implement;

import com.quipux.backend_playlist.dto.request.SongRequest;
import com.quipux.backend_playlist.dto.response.SongResponse;
import com.quipux.backend_playlist.entity.Playlist;
import com.quipux.backend_playlist.entity.Song;
import com.quipux.backend_playlist.repository.SongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SongServiceImplementTest {

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private SongServiceImplement songService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should save song and return SongResponse")
    void testSave() {
        // given
        SongRequest request = new SongRequest();
        request.setTitle("Song Title");
        request.setArtist("Artist Name");
        request.setAlbum("Album Name");
        request.setYear("2023");
        request.setGenre("Pop");

        Playlist playlist = new Playlist();
        playlist.setId(1L);
        playlist.setName("My Playlist");

        // Creamos la canción que debería devolver el repositorio
        Song savedSong = new Song(request, playlist);
        savedSong.setId(100L); // Simulamos que la base le asigna un ID

        when(songRepository.save(any(Song.class))).thenReturn(savedSong);

        // when
        SongResponse response = songService.save(request, playlist);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getTitle()).isEqualTo(savedSong.getTitle());
        assertThat(response.getArtist()).isEqualTo(savedSong.getArtist());
        assertThat(response.getAlbum()).isEqualTo(savedSong.getAlbum());
        assertThat(response.getYear()).isEqualTo(savedSong.getReleaseYear());
        assertThat(response.getGenre()).isEqualTo(savedSong.getGenre());

        // Verificamos que se haya llamado a save()
        ArgumentCaptor<Song> songCaptor = ArgumentCaptor.forClass(Song.class);
        verify(songRepository).save(songCaptor.capture());

        Song capturedSong = songCaptor.getValue();
        assertThat(capturedSong.getTitle()).isEqualTo("Song Title");
        assertThat(capturedSong.getPlaylist()).isEqualTo(playlist);
    }
}
