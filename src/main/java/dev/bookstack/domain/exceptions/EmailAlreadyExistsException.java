package dev.bookstack.domain.exceptions;

public class EmailAlreadyExistsException extends DomainException {
    public EmailAlreadyExistsException(String message, String errorCode) {
        super(message, errorCode);
    }
}
