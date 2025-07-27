package com.quipux.backend_playlist.entity;

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
}
