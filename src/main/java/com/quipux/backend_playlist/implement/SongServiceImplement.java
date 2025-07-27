package com.quipux.backend_playlist.implement;

import com.quipux.backend_playlist.dto.request.SongRequest;
import com.quipux.backend_playlist.dto.response.SongResponse;
import com.quipux.backend_playlist.entity.Playlist;
import com.quipux.backend_playlist.entity.Song;
import com.quipux.backend_playlist.repository.SongRepository;
import com.quipux.backend_playlist.service.SongService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongServiceImplement implements SongService {
    private final SongRepository songRepository;

    @Override
    public SongResponse save(SongRequest request, Playlist playlist) {
        log.info("Guardando canción '{}' en la playlist '{}'", request.getTitle(), playlist.getName());
        return new SongResponse(songRepository.save(new Song(request, playlist)));
    }
}
