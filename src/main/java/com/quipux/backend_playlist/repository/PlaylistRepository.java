package com.quipux.backend_playlist.repository;

import com.quipux.backend_playlist.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
    Playlist findByName(String name);

    @Query(value = "delete from playlist as p where p.name = :name",
    nativeQuery = true)
    void deleteByName(String name);
}
