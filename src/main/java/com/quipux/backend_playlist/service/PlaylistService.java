package com.quipux.backend_playlist.service;

import com.quipux.backend_playlist.dto.request.PlaylistRequest;
import com.quipux.backend_playlist.dto.response.PlaylistResponse;

import java.util.List;

public interface PlaylistService {
    List<PlaylistResponse> findAll();

    PlaylistResponse findByName(String name);

    Void deleteByName(String name);

    PlaylistResponse create(PlaylistRequest playlistRequest);
}
