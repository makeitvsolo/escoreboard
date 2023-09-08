package com.makeitvsolo.escoreboard.model.scoring.gamescore;

import com.makeitvsolo.escoreboard.model.scoring.Zero;

public final class ZeroTieBreakGameScore implements Zero<TieBreakGameScore> {

    public ZeroTieBreakGameScore() {
    }

    @Override
    public TieBreakGameScore zero() {
        return TieBreakGameScore.initial();
    }
}
