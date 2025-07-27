package com.quipux.backend_playlist.implement;

import com.quipux.backend_playlist.dto.request.SongRequest;
import com.quipux.backend_playlist.dto.response.SongResponse;
import com.quipux.backend_playlist.entity.Playlist;
import com.quipux.backend_playlist.entity.Song;
import com.quipux.backend_playlist.repository.SongRepository;
import com.quipux.backend_playlist.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongServiceImplement implements SongService {
    private final SongRepository songRepository;

    @Override
    public SongResponse save(SongRequest request, Playlist playlist) {
        return new SongResponse(songRepository.save(new Song(request, playlist)));
    }
}
