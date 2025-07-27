package com.quipux.backend_playlist.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DescriptionResponse {
    List<SongResponse> songs;
}
