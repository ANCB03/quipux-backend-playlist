package com.quipux.backend_playlist.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quipux.backend_playlist.entity.Song;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SongRequest {

    @JsonProperty("titulo")
    private String title;

    @JsonProperty("artista")
    private String artist;

    @JsonProperty("album")
    private String album;

    @JsonProperty("anno")
    private String year;

    @JsonProperty("genero")
    private String genre;

    public SongRequest(Song song) {
        this.title = song.getTitle();
        this.artist = song.getArtist();
        this.album = song.getAlbum();
        this.year = song.getReleaseYear();
        this.genre = song.getGenre();
    }
}
