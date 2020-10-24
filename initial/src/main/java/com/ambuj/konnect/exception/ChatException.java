package com.ambuj.konnect.exception;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class ChatException extends RuntimeException {

    private String errorCode;
    private String errorMessage;
    private Throwable cause;

    public ChatException(String errorCode, String errorMessage, Throwable cause){
        this.errorCode= errorCode;
        this.errorMessage = errorMessage;
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }

    @Override
    public String getLocalizedMessage() {
        return this.errorMessage;
    }

    @Override
    public synchronized Throwable getCause() {
        return cause;
    }
}