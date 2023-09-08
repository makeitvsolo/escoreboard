package com.makeitvsolo.escoreboard.model.scoring.matchscore;

import com.makeitvsolo.escoreboard.model.scoring.Zero;

public final class ZeroMatchScore implements Zero<MatchScore> {

    ZeroMatchScore() {
    }

    @Override
    public MatchScore zero() {
        return MatchScore.initial();
    }
}
