package dev.bookstack.domain.exceptions;

public class EmailInvalidException extends DomainException {
    public EmailInvalidException(String message, String errorCode) {
        super(message, errorCode);
    }
}
