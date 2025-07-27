package com.quipux.backend_playlist.repository;

import com.quipux.backend_playlist.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song,Long> {
}
