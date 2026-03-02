package dev.bookstack.application.dto.request;

public record UpdateUserRequestDto(
        String name,
        String email
) {
}
