package com.quipux.backend_playlist.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
}
