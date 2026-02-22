package dev.bookstack.domain.exceptions;

public class CpfInvalidException extends DomainException {
    public CpfInvalidException(String message, String errorCode) {
        super(message, errorCode);
    }
}
