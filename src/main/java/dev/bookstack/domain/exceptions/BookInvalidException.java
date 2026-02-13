package dev.bookstack.domain.exceptions;

public class BookInvalidException extends DomainException {
    public BookInvalidException(String message, String errorCode) {
        super(message, errorCode);
    }
}
