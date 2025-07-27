package com.quipux.backend_playlist.controller;

import com.quipux.backend_playlist.dto.request.PlaylistRequest;
import com.quipux.backend_playlist.dto.response.PlaylistResponse;
import com.quipux.backend_playlist.service.PlaylistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lists")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @PostMapping()
    public ResponseEntity<PlaylistResponse> create(@RequestBody @Valid PlaylistRequest request) {
        return new ResponseEntity<>(playlistService.create(request), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<PlaylistResponse>> getAll() {
        return new ResponseEntity<>(playlistService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{listName}")
    public ResponseEntity<PlaylistResponse> getByListName(@PathVariable String listName) {
        return new ResponseEntity<>(playlistService.findByName(listName), HttpStatus.OK);
    }

    @DeleteMapping("/{listName}")
    public ResponseEntity<Void> deleteByListName(@PathVariable String listName) {
        return new ResponseEntity<>(playlistService.deleteByName(listName), HttpStatus.NO_CONTENT);
    }
}
