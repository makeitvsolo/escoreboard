package com.makeitvsolo.escoreboard.model.exception;

import com.makeitvsolo.escoreboard.core.exception.ETennisException;

import java.text.MessageFormat;
import java.util.UUID;

public final class MatchAlreadyFinishedException extends ETennisException {
    public MatchAlreadyFinishedException(UUID matchId) {
        super(MessageFormat.format("Match with id: {0} already finished", matchId));
    }
}
