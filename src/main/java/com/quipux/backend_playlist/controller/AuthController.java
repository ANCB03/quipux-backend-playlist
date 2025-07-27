package com.quipux.backend_playlist.controller;

import com.quipux.backend_playlist.dto.request.LoginRequest;
import com.quipux.backend_playlist.dto.request.RegisterRequest;
import com.quipux.backend_playlist.dto.response.LoginResponse;
import com.quipux.backend_playlist.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuario y obtener token JWT")
    public LoginResponse login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getEmail(), request.getPassword());
        return new LoginResponse(token);
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar nuevo usuario")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request) {
        authService.register(request.getEmail(), request.getUsername(), request.getPassword());
        return new ResponseEntity<>("Usuario registrado correctamente", HttpStatus.CREATED);
    }
}
