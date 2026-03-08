package dev.bookstack.domain.exceptions;

public class CpfAlreadyExistsException extends DomainException {
    public CpfAlreadyExistsException(String message, String errorCode) {
        super(message, errorCode);
    }
}
