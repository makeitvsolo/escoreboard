package com.makeitvsolo.escoreboard.model.scoring.matchscore;

import com.makeitvsolo.escoreboard.model.scoring.PlayerNumber;
import com.makeitvsolo.escoreboard.model.scoring.ScoreState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

@DisplayName("MatchScore")
public class MatchScoreTests {
    private MatchScore matchScore;

    @Test
    @DisplayName("initial is `0:0`")
    public void initialIsZeroZero() {
        matchScore = MatchScore.initial();

        Assertions.assertEquals(0, matchScore.totalPointsOf(PlayerNumber.One));
        Assertions.assertEquals(0, matchScore.totalPointsOf(PlayerNumber.Two));
    }

    @Test
    @DisplayName("player wins when he earns two points; 2:0")
    public void TwoZero() {
        matchScore = MatchScore.initial();

        IntStream.range(0, 1)
                .forEach((round) -> {
                    var scoreState = matchScore.pointFor(PlayerNumber.One);
                    Assertions.assertEquals(ScoreState.UnknownWinner, scoreState);
                });

        var scoreState = matchScore.pointFor(PlayerNumber.One);
        Assertions.assertEquals(ScoreState.PlayerOneWin, scoreState);
    }

    @Test
    @DisplayName("player wins when he earns two points; 2:1")
    public void TwoOne() {
        matchScore = MatchScore.initial();

        IntStream.range(0, 1)
                .forEach((round) -> {
                    Assertions.assertEquals(ScoreState.UnknownWinner, matchScore.pointFor(PlayerNumber.One));
                    Assertions.assertEquals(ScoreState.UnknownWinner, matchScore.pointFor(PlayerNumber.Two));
                });

        var scoreState = matchScore.pointFor(PlayerNumber.One);
        Assertions.assertEquals(ScoreState.PlayerOneWin, scoreState);
    }
}
