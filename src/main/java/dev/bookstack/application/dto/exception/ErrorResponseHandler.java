package dev.bookstack.application.dto.exception;

import java.time.LocalDateTime;

public record ErrorResponseHandler(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        String errorCode
) {
}
