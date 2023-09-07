package com.makeitvsolo.escoreboard.core.exception;

public abstract class ETennisException extends RuntimeException {
    protected ETennisException(String message) {
        super(message);
    }
}
