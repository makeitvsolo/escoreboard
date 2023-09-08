package com.makeitvsolo.escoreboard.model.match.exception;

import com.makeitvsolo.escoreboard.core.exception.EScoreBoardException;

import java.text.MessageFormat;
import java.util.UUID;

public final class MatchIsOngoingException extends EScoreBoardException {
    public MatchIsOngoingException(UUID matchId) {
        super(MessageFormat.format("Match with id: {0} is still ongoing", matchId));
    }
}
