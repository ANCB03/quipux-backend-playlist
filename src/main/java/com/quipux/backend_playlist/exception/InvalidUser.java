package com.quipux.backend_playlist.exception;

public class InvalidUser extends RuntimeException {
    public InvalidUser(String message) {
        super(message);
    }
}
