package com.makeitvsolo.escoreboard.model.scoring.gamescore;

import com.makeitvsolo.escoreboard.model.scoring.PlayerNumber;
import com.makeitvsolo.escoreboard.model.scoring.Score;
import com.makeitvsolo.escoreboard.model.scoring.ScoreState;
import com.makeitvsolo.escoreboard.model.scoring.matchscore.MatchScore;

import java.util.EnumMap;
import java.util.Map;

public final class TieBreakGameScore implements Score {
    private static final int InitialPoint = 0;
    private static final int OnePoint = 1;
    private static final int WinPoint = 7;
    private static final int WinCondition = 2;

    private final Map<PlayerNumber, Integer> points;

    TieBreakGameScore(Map<PlayerNumber, Integer> points) {
        this.points = points;
    }

    public static TieBreakGameScore initial() {
        var points = new EnumMap<>(
                Map.ofEntries(
                        Map.entry(PlayerNumber.One, InitialPoint),
                        Map.entry(PlayerNumber.Two, InitialPoint)
                )
        );

        return new TieBreakGameScore(points);
    }

    public boolean gameWinnerIs(PlayerNumber playerNumber) {
        var playerPoints = totalPointsOf(playerNumber);
        var otherPlayerPoints = totalPointsOf(playerNumber.other());

        return playerPoints >= WinPoint
                       && (playerPoints - otherPlayerPoints) >= WinCondition;
    }

    public int totalPointsOf(PlayerNumber playerNumber) {
        return points.get(playerNumber);
    }

    @Override
    public ScoreState pointFor(PlayerNumber playerNumber) {
        points.merge(playerNumber, OnePoint, Integer::sum);

        if (gameWinnerIs(playerNumber)) {
            return ScoreState.winnerIs(playerNumber);
        }

        return ScoreState.unknownWinner();
    }
}
