package com.makeitvsolo.escoreboard.model.match.exception;

import com.makeitvsolo.escoreboard.core.exception.ETennisException;

import java.text.MessageFormat;
import java.util.UUID;

public final class WinnerAlreadyKnownException extends ETennisException {
    public WinnerAlreadyKnownException(UUID matchId) {
        super(MessageFormat.format("Winner in match with id: {0} already known", matchId));
    }
}
