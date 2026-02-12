package dev.bookstack.domain.exceptions;

public abstract class DomainException extends RuntimeException {
    private final String errorCode;

    public DomainException(String message, String errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode(){
        return errorCode;
    }

}
