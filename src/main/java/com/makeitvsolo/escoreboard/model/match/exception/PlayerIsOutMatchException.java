package com.makeitvsolo.escoreboard.model.match.exception;

import com.makeitvsolo.escoreboard.core.exception.EScoreBoardException;

import java.text.MessageFormat;
import java.util.UUID;

public final class PlayerIsOutMatchException extends EScoreBoardException {
    public PlayerIsOutMatchException(UUID matchId, UUID playerId) {
        super(MessageFormat.format("Player with id: {0} is out of the match with id: {1}", playerId, matchId));
    }
}
