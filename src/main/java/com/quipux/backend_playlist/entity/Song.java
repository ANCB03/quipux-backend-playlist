package com.quipux.backend_playlist.entity;

import com.quipux.backend_playlist.dto.request.SongRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "song")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String artist;

    private String album;

    private String year;

    private String genre;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    public Song(SongRequest request) {
        this.title = request.getTitle();
        this.artist = request.getArtist();
        this.album = request.getAlbum();
        this.year = request.getYear();
        this.genre = request.getGenre();
    }

}
