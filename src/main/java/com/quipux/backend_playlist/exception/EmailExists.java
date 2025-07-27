package com.quipux.backend_playlist.exception;

public class EmailExists extends RuntimeException {
    public EmailExists(String message) {
        super(message);
    }
}
