package com.makeitvsolo.escoreboard.model.scoring.setscore;

import com.makeitvsolo.escoreboard.model.scoring.Zero;

public final class ZeroSetScore implements Zero<SetScore> {

    public ZeroSetScore() {
    }

    @Override
    public SetScore zero() {
        return SetScore.initial();
    }
}
