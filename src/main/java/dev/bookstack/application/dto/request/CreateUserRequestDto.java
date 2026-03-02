package dev.bookstack.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequestDto(
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String cpf
){
}
