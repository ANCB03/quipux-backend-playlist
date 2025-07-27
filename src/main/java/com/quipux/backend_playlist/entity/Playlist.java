package com.quipux.backend_playlist.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "playlist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Song> songs;
}
