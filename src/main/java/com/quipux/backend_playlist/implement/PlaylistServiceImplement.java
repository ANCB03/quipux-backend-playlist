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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImplement implements PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final SongService  songService;

    @Override
    public List<PlaylistResponse> findAll() {
        return playlistRepository.findAll()
                .stream()
                .map(PlaylistResponse::new)
                .toList();
    }

    @Override
    public PlaylistResponse findByName(String name) {
        Playlist playlist = getAndValidatePlaylist(name);

        if (playlist.getSongs().isEmpty()) {
            return new PlaylistResponse();
        }

        return new PlaylistResponse(playlist);
    }

    @Override
    public Void deleteByName(String name) {
        Playlist playlist = getAndValidatePlaylist(name);
        playlistRepository.delete(playlist);
        return null;
    }

    @Override
    public PlaylistResponse create(PlaylistRequest playlistRequest) {
        Playlist playlist = playlistRepository.save(new Playlist(playlistRequest));
        PlaylistResponse response = new PlaylistResponse(playlist);
        List<SongResponse> songResponses = new ArrayList<>();
        for (SongRequest song : playlistRequest.getSongs()) {
            songResponses.add(songService.save(song, playlist));
        }
        response.setSongs(songResponses);
        return response;
    }

    private Playlist getAndValidatePlaylist(String name) {
        Playlist  playlist = playlistRepository.findByName(name);
        if (playlist == null){
            throw new ResourceNotFoundException("Playlist no encontrada");
        }
        return playlist;
    }
}
