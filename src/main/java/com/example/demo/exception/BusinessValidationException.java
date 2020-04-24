package com.example.demo.exception;

public class BusinessValidationException extends RuntimeException{

    public BusinessValidationException() {
        super();
    }

    public BusinessValidationException(String message) {
        super(message);
    }

    public BusinessValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessValidationException(Throwable cause) {
        super(cause);
    }

    protected BusinessValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
