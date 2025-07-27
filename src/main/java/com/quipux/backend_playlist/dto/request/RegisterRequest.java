package com.quipux.backend_playlist.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterRequest {
    @Email
    @NotBlank(message = "email es requerido")
    private String email;

    private String username;

    @NotBlank(message = "password es requerido")
    private String password;
}
