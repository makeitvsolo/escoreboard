package com.makeitvsolo.escoreboard.model.scoring.matchscore;

import com.makeitvsolo.escoreboard.model.scoring.PlayerNumber;
import com.makeitvsolo.escoreboard.model.scoring.ScoreState;
import org.junit.jupiter.api.*;

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

    @Nested
    @DisplayName("while no player has `2` points")
    public class WhileNoPlayerHasSixPoints {

        @BeforeEach
        public void beforeEach() {
            matchScore = MatchScore.initial();
        }

        @Test
        @DisplayName("players earn points and winner is unknown")
        public void playerEarnPointsAndWinnerIsUnknown() {
            IntStream.range(0, 1)
                    .forEach((round) -> {
                        Assertions.assertEquals(ScoreState.UnknownWinner, matchScore.pointFor(PlayerNumber.One));
                        Assertions.assertEquals(ScoreState.UnknownWinner, matchScore.pointFor(PlayerNumber.Two));
                    });
        }

        @Nested
        @DisplayName("when some player earn `2` points")
        public class WhenPlayerEarnTwoPointsHeIsWinner {

            @BeforeEach
            public void beforeEach() {
                IntStream.range(0, 1)
                        .forEach((round) -> {
                            matchScore.pointFor(PlayerNumber.One);
                            matchScore.pointFor(PlayerNumber.Two);
                        });
            }

            @Test
            @DisplayName("winner is player one if he earn `2` points")
            public void winnerIsPlayerOneIfHeEarnTwoPoints() {
                Assertions.assertEquals(ScoreState.PlayerOneWin, matchScore.pointFor(PlayerNumber.One));
                Assertions.assertEquals(2, matchScore.totalPointsOf(PlayerNumber.One));
            }

            @Test
            @DisplayName("winner is player two if he earn `2` points")
            public void winnerIsPlayerTwoIfHeEarnTwoPoints() {
                Assertions.assertEquals(ScoreState.PlayerTwoWin, matchScore.pointFor(PlayerNumber.Two));
                Assertions.assertEquals(2, matchScore.totalPointsOf(PlayerNumber.Two));
            }
        }
    }
}
