package dev.bookstack.domain.exceptions;

public class UserNotFoundException extends DomainException{
    public UserNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }
}
