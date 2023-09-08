package com.makeitvsolo.escoreboard.model.scoring.exception;

import com.makeitvsolo.escoreboard.core.exception.EScoreBoardException;

public final class WrongPointException extends EScoreBoardException {
    public WrongPointException(String message) {
        super(message);
    }
}
