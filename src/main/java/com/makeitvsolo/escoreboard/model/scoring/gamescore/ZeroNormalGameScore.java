package com.makeitvsolo.escoreboard.model.scoring.gamescore;

import com.makeitvsolo.escoreboard.model.scoring.Zero;

public final class ZeroNormalGameScore implements Zero<NormalGameScore> {

    public ZeroNormalGameScore() {
    }

    @Override
    public NormalGameScore zero() {
        return NormalGameScore.initial();
    }
}
