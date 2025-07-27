package com.quipux.backend_playlist.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quipux.backend_playlist.entity.Playlist;
import com.quipux.backend_playlist.entity.Song;
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
    private List<SongResponse> songs;

    public PlaylistResponse(Playlist playlist) {
        this.name = playlist.getName();
        this.description = playlist.getDescription();
        this.songs = new java.util.ArrayList<>();
        if (!playlist.getSongs().isEmpty()) {
            for (Song song : playlist.getSongs()){
                this.songs.add(new SongResponse(song));
            }
        }
    }
}
