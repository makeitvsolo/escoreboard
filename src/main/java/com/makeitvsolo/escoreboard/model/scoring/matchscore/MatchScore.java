package com.makeitvsolo.escoreboard.model.scoring.matchscore;

import com.makeitvsolo.escoreboard.model.scoring.PlayerNumber;
import com.makeitvsolo.escoreboard.model.scoring.Score;
import com.makeitvsolo.escoreboard.model.scoring.ScoreState;

import java.util.EnumMap;
import java.util.Map;

public final class MatchScore implements Score {
    private static final int InitialPoint = 0;
    private static final int OnePoint = 1;
    private static final int WinPoint = 2;

    private final Map<PlayerNumber, Integer> points;

    MatchScore(Map<PlayerNumber, Integer> points) {
        this.points = points;
    }

    public static MatchScore initial() {
        var points = new EnumMap<>(
                Map.ofEntries(
                        Map.entry(PlayerNumber.One, InitialPoint),
                        Map.entry(PlayerNumber.Two, InitialPoint)
                )
        );

        return new MatchScore(points);
    }

    public boolean matchWinnerIs(PlayerNumber playerNumber) {
        return totalPointsOf(playerNumber) >= WinPoint;
    }

    public int totalPointsOf(PlayerNumber playerNumber) {
        return points.get(playerNumber);
    }

    @Override
    public ScoreState pointFor(PlayerNumber playerNumber) {
        points.merge(playerNumber, OnePoint, Integer::sum);

        if (matchWinnerIs(playerNumber)) {
            return ScoreState.winnerIs(playerNumber);
        }

        return ScoreState.unknownWinner();
    }
}
