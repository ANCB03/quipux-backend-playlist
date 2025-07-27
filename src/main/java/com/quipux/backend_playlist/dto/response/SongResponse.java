package com.quipux.backend_playlist.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quipux.backend_playlist.entity.Song;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SongResponse {
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

    public SongResponse(Song song) {
        this.title = song.getTitle();
        this.artist = song.getArtist();
        this.album = song.getAlbum();
        this.year = song.getYear();
        this.genre = song.getGenre();
    }
}
