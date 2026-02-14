package dev.bookstack.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateBookRequestDto (
        String title,
        String author,
        String category,
        String isbn){
}
