package com.quipux.backend_playlist.repository;

import com.quipux.backend_playlist.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
    Playlist findByName(String name);
}
