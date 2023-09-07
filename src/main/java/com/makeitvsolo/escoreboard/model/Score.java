package com.makeitvsolo.escoreboard.model;

import java.util.UUID;

public interface Score {
    ScoreState pointFor(UUID playerId);
}
