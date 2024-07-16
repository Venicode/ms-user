package com.ms.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDto(
        @NotBlank
        String name,
        @NotBlank
        String lastName,
        @Email @NotBlank
        String email,
        @NotBlank
        String password
) {
}
