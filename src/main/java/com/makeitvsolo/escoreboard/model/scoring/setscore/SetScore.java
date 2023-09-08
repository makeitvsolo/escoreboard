package com.makeitvsolo.escoreboard.model.scoring.setscore;

import com.makeitvsolo.escoreboard.model.scoring.PlayerNumber;
import com.makeitvsolo.escoreboard.model.scoring.Score;
import com.makeitvsolo.escoreboard.model.scoring.ScoreState;

import java.util.EnumMap;
import java.util.Map;

public final class SetScore implements Score {
    private static final int InitialPoint = 0;
    private static final int OnePoint = 1;
    private static final int WinPoint = 6;
    private static final int WinCondition = 2;

    private final Map<PlayerNumber, Integer> points;

    SetScore(Map<PlayerNumber, Integer> points) {
        this.points = points;
    }

    public static SetScore initial() {
        var points = new EnumMap<>(
                Map.ofEntries(
                        Map.entry(PlayerNumber.One, InitialPoint),
                        Map.entry(PlayerNumber.Two, InitialPoint)
                )
        );

        return new SetScore(points);
    }

    public boolean setWinnerIs(PlayerNumber playerNumber) {
        var playerPoints = totalPointsOf(playerNumber);
        var otherPlayerPoints = totalPointsOf(playerNumber.other());

        if (otherPlayerPoints >= WinPoint) {
            return playerPoints > otherPlayerPoints;
        }

        return playerPoints >= WinPoint &&
                       (playerPoints - otherPlayerPoints) >= WinCondition;
    }

    public int totalPointsOf(PlayerNumber playerNumber) {
        return points.get(playerNumber);
    }

    @Override
    public ScoreState pointFor(PlayerNumber playerNumber) {
        points.merge(playerNumber, OnePoint, Integer::sum);

        if (setWinnerIs(playerNumber)) {
            return ScoreState.winnerIs(playerNumber);
        }

        return ScoreState.unknownWinner();
    }
}
