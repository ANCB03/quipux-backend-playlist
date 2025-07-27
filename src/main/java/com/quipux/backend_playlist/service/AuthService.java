package com.quipux.backend_playlist.service;


public interface AuthService {
    String login(String email, String password);

    void register(String email, String username, String password);
}
