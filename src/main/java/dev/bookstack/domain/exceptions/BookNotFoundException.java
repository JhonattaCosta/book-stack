package dev.bookstack.domain.exceptions;

public class BookNotFoundException extends DomainException {
    public BookNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }
}
