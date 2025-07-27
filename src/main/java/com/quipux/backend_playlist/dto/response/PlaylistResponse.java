package com.quipux.backend_playlist.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quipux.backend_playlist.dto.request.SongRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlaylistResponse {
    @JsonProperty("nombre")
    private String name;

    @JsonProperty("descripcion")
    private String description;

    @JsonProperty("canciones")
    private List<SongRequest> songs;
}
