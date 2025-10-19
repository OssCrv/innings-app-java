package com.ossman.innings_app.backend.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank @Size(max = 50) String username,
        @NotBlank String password,
        @NotBlank @Email @Size(max = 120) String email) {
}
