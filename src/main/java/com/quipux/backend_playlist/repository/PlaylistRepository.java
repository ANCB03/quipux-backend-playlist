package com.quipux.backend_playlist.repository;

import com.quipux.backend_playlist.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
    Playlist findByNameIgnoreCase(String name);
}
