package com.makeitvsolo.escoreboard.model.scoring;

import java.util.UUID;

public interface Score {
    ScoreState pointFor(UUID playerId);
}
