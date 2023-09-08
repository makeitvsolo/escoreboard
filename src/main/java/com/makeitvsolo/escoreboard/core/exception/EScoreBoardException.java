package com.makeitvsolo.escoreboard.core.exception;

public abstract class EScoreBoardException extends RuntimeException {
    protected EScoreBoardException(String message) {
        super(message);
    }
}
