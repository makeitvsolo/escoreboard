package com.makeitvsolo.escoreboard.model;

import java.util.UUID;

public final class Score {

    Score() {
    }

    public static Score initial() {
        return new Score();
    }

    public ScoreState pointFor(UUID playerId) {
        return ScoreState.MatchIsOngoing;
    }
}
