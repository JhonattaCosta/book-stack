package dev.bookstack.application.dto.response;

import java.time.LocalDateTime;

public record UserResponseDto (
        Long id,
        String name,
        String email,
        String cpf,
        boolean isAdmin,
        boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
