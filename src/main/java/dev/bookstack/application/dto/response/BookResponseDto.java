package dev.bookstack.application.dto.response;

import dev.bookstack.domain.entities.valueObjects.Isbn;

import java.time.LocalDateTime;

public record BookResponseDto (
        Long id,
        String title,
        String author,
        String category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Isbn isbn
) {
}
