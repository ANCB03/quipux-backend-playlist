package com.quipux.backend_playlist.dto.response;

import com.quipux.backend_playlist.dto.request.SongRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DescriptionResponse {
    List<SongRequest> songs;
}
