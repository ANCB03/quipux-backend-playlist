package com.quipux.backend_playlist.implement;

import com.quipux.backend_playlist.dto.request.PlaylistRequest;
import com.quipux.backend_playlist.dto.request.SongRequest;
import com.quipux.backend_playlist.dto.response.PlaylistResponse;
import com.quipux.backend_playlist.dto.response.SongResponse;
import com.quipux.backend_playlist.entity.Playlist;
import com.quipux.backend_playlist.exception.ResourceNotFoundException;
import com.quipux.backend_playlist.repository.PlaylistRepository;
import com.quipux.backend_playlist.service.PlaylistService;
import com.quipux.backend_playlist.service.SongService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlaylistServiceImplement implements PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final SongService  songService;

    @Override
    public List<PlaylistResponse> findAll() {
        log.info("Buscando todas las playlists");

        return playlistRepository.findAll()
                .stream()
                .map(PlaylistResponse::new)
                .toList();
    }

    @Override
    public PlaylistResponse findByName(String name) {
        log.info("Buscando playlist por nombre: {}", name);

        Playlist playlist = getAndValidatePlaylist(name);

        if (playlist.getSongs().isEmpty()) {
            log.warn("La playlist '{}' no tiene canciones", name);
            return new PlaylistResponse();
        }

        log.info("Playlist '{}' encontrada con {} canciones", name, playlist.getSongs().size());
        return new PlaylistResponse(playlist);
    }

    @Override
    public Void deleteByName(String name) {
        log.info("Eliminando playlist con nombre: {}", name);
        Playlist playlist = getAndValidatePlaylist(name);
        playlistRepository.delete(playlist);
        log.info("Playlist '{}' eliminada correctamente", name);
        return null;
    }

    @Override
    public PlaylistResponse create(PlaylistRequest playlistRequest) {
        log.info("Creando playlist con nombre: {}", playlistRequest.getName());

        Playlist playlist = playlistRepository.save(new Playlist(playlistRequest));
        PlaylistResponse response = new PlaylistResponse(playlist);
        List<SongResponse> songResponses = new ArrayList<>();
        for (SongRequest song : playlistRequest.getSongs()) {
            log.debug("Añadiendo canción '{}' a la playlist '{}'", song.getTitle(), playlistRequest.getName());
            songResponses.add(songService.save(song, playlist));
        }
        response.setSongs(songResponses);
        log.info("Playlist '{}' creada exitosamente con {} canciones", playlistRequest.getName(), songResponses.size());
        return response;
    }

    private Playlist getAndValidatePlaylist(String name) {
        Playlist  playlist = playlistRepository.findByName(name);
        if (playlist == null){
            log.error("Playlist '{}' no encontrada", name);
            throw new ResourceNotFoundException("Playlist no encontrada");
        }
        return playlist;
    }
}
