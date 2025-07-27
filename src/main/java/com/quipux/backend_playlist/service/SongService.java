package com.quipux.backend_playlist.service;

import com.quipux.backend_playlist.dto.request.SongRequest;
import com.quipux.backend_playlist.dto.response.SongResponse;

public interface SongService {
    SongResponse save(SongRequest request);
}
