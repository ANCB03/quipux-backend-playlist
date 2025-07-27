package com.quipux.backend_playlist.implement;

import com.quipux.backend_playlist.dto.response.DescriptionResponse;
import com.quipux.backend_playlist.dto.response.PlaylistResponse;
import com.quipux.backend_playlist.dto.response.SongResponse;
import com.quipux.backend_playlist.repository.PlaylistRepository;
import com.quipux.backend_playlist.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImplement implements PlaylistService {
    private final PlaylistRepository playlistRepository;

    @Override
    public List<PlaylistResponse> findAll() {
        return playlistRepository.findAll()
                .stream()
                .map(PlaylistResponse::new)
                .toList();
    }

    @Override
    public DescriptionResponse findByName(String name) {
        return new DescriptionResponse(playlistRepository.findByName(name).getSongs()
                .stream()
                .map(SongResponse::new)
                .toList());
    }

    @Override
    public void deleteByName(String name) {
        playlistRepository.deleteByName(name);
    }
}
