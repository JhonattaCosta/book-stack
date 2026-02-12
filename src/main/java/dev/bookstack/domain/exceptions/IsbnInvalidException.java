package dev.bookstack.domain.exceptions;

public class IsbnInvalidException extends DomainException {
    public IsbnInvalidException(String message, String errorCode) {
        super(message, errorCode);
    }
}
