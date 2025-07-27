package com.quipux.backend_playlist.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlaylistRequest {
    @JsonProperty("nombre")
    @NotEmpty(message = "Se requiere el nombre")
    private String name;

    @JsonProperty("descripcion")
    private String description;

    @JsonProperty("canciones")
    private List<SongRequest> songs;
}
