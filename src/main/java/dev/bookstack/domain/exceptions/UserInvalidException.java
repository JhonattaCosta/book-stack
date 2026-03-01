package dev.bookstack.domain.exceptions;

public class UserInvalidException extends DomainException {
    public UserInvalidException(String message, String errorCode) {
        super(message, errorCode);
    }
}
