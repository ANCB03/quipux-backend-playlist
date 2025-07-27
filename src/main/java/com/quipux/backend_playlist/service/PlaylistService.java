package com.quipux.backend_playlist.service;

import com.quipux.backend_playlist.dto.response.DescriptionResponse;
import com.quipux.backend_playlist.dto.response.PlaylistResponse;

import java.util.List;

public interface PlaylistService {
    List<PlaylistResponse> findAll();

    DescriptionResponse findByName(String name);

    void deleteByName(String name);
}
