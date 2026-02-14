package dev.bookstack.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateBookRequestDto (
        @NotBlank
        String title,
        @NotBlank
        String author,
        @NotBlank
        String category,
        @NotBlank
        String isbn){
}
