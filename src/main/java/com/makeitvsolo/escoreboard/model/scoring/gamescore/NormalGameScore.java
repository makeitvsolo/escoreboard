package com.makeitvsolo.escoreboard.model.scoring.gamescore;

import com.makeitvsolo.escoreboard.model.scoring.PlayerNumber;
import com.makeitvsolo.escoreboard.model.scoring.Score;
import com.makeitvsolo.escoreboard.model.scoring.ScoreState;

import java.util.EnumMap;
import java.util.Map;

public final class NormalGameScore implements Score {
    private final Map<PlayerNumber, NormalGamePoint> points;

    NormalGameScore(Map<PlayerNumber, NormalGamePoint> points) {
        this.points = points;
    }

    public static NormalGameScore initial() {
        var points = new EnumMap<>(
                Map.ofEntries(
                        Map.entry(PlayerNumber.One, NormalGamePoint.initial()),
                        Map.entry(PlayerNumber.Two, NormalGamePoint.initial())
                )
        );

        return new NormalGameScore(points);
    }

    public boolean gameWinnerIs(PlayerNumber playerNumber) {
        var playerPoints = totalPointsOf(playerNumber);
        var otherPlayerPoints = totalPointsOf(playerNumber.other());

        if (otherPlayerPoints.greaterEqualsThan(NormalGamePoint.winPoint())) {
            return playerPoints.greaterEqualsThan(NormalGamePoint.advantage());
        }

        return playerPoints.greaterEqualsThan(NormalGamePoint.winPoint());
    }

    public NormalGamePoint totalPointsOf(PlayerNumber playerNumber) {
        return points.get(playerNumber);
    }

    @Override
    public ScoreState pointFor(PlayerNumber playerNumber) {
        points.put(playerNumber, totalPointsOf(playerNumber).up());

        if (gameWinnerIs(playerNumber)) {
            return ScoreState.winnerIs(playerNumber);
        }

        return ScoreState.unknownWinner();
    }
}
