package com.quipux.backend_playlist.controller;

import com.quipux.backend_playlist.dto.request.PlaylistRequest;
import com.quipux.backend_playlist.dto.response.PlaylistResponse;
import com.quipux.backend_playlist.service.PlaylistService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Crear una nueva playlist")
    public ResponseEntity<PlaylistResponse> create(@RequestBody @Valid PlaylistRequest request) {
        return new ResponseEntity<>(playlistService.create(request), HttpStatus.CREATED);
    }

    @GetMapping()
    @Operation(summary = "Obtener todas las playlist")
    public ResponseEntity<List<PlaylistResponse>> getAll() {
        return new ResponseEntity<>(playlistService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{listName}")
    @Operation(summary = "Obtenen playlist por nombre")
    public ResponseEntity<PlaylistResponse> getByListName(@PathVariable String listName) {
        return new ResponseEntity<>(playlistService.findByName(listName), HttpStatus.OK);
    }

    @DeleteMapping("/{listName}")
    @Operation(summary = "Eliminar playlist por nombre")
    public ResponseEntity<Void> deleteByListName(@PathVariable String listName) {
        return new ResponseEntity<>(playlistService.deleteByName(listName), HttpStatus.NO_CONTENT);
    }
}
