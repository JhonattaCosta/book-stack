package dev.bookstack.domain.exceptions;

public class IsbnAlreadyExistsException extends DomainException {
    public IsbnAlreadyExistsException(String message, String errorCode) {
        super(message, errorCode);
    }
}
