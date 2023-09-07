package com.makeitvsolo.escoreboard.model.exception;

import com.makeitvsolo.escoreboard.core.exception.ETennisException;

import java.text.MessageFormat;
import java.util.UUID;

public final class MatchIsOngoingException extends ETennisException {
    public MatchIsOngoingException(UUID matchId) {
        super(MessageFormat.format("Match with id: {0} is still ongoing", matchId));
    }
}
