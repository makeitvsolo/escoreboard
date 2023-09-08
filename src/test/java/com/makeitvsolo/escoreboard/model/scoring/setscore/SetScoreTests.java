package com.makeitvsolo.escoreboard.model.scoring.setscore;

import com.makeitvsolo.escoreboard.model.scoring.PlayerNumber;
import com.makeitvsolo.escoreboard.model.scoring.ScoreState;
import org.junit.jupiter.api.*;

import java.util.stream.IntStream;

@DisplayName("SetScore")
public class SetScoreTests {
    private SetScore setScore;

    @Test
    @DisplayName("initial is `0:0`")
    public void initialIsZeroZero() {
        setScore = SetScore.initial();

        Assertions.assertEquals(0, setScore.totalPointsOf(PlayerNumber.One));
        Assertions.assertEquals(0, setScore.totalPointsOf(PlayerNumber.Two));
    }

    @Nested
    @DisplayName("while no player has `6` points")
    public class WhileNoPlayerHasSixPoints {

        @BeforeEach
        public void beforeEach() {
            setScore = SetScore.initial();

            Assertions.assertEquals(0, setScore.totalPointsOf(PlayerNumber.One));
            Assertions.assertEquals(0, setScore.totalPointsOf(PlayerNumber.Two));
        }

        @Test
        @DisplayName("players earn points and winner is unknown")
        public void playerEarnPointsAndWinnerIsUnknown() {
            IntStream.range(0, 5)
                    .forEach((round) -> {
                        Assertions.assertEquals(ScoreState.UnknownWinner, setScore.pointFor(PlayerNumber.One));
                        Assertions.assertEquals(ScoreState.UnknownWinner, setScore.pointFor(PlayerNumber.Two));
                    });
        }

        @Nested
        @DisplayName("when some player earn `6` points and difference `>=2`")
        public class WhenSomePlayerEarnSixPointsWithDifferenceGreaterEqualsThanTwo {

            @BeforeEach
            public void beforeEach() {
                IntStream.range(0, 5)
                        .forEach((round) -> {
                            Assertions.assertEquals(ScoreState.UnknownWinner, setScore.pointFor(PlayerNumber.One));
                        });
            }

            @Test
            @DisplayName("he is winner")
            public void heIsWinner() {
                Assertions.assertEquals(ScoreState.PlayerOneWin, setScore.pointFor(PlayerNumber.One));
                Assertions.assertEquals(6, setScore.totalPointsOf(PlayerNumber.One));
                Assertions.assertTrue(
                        setScore.totalPointsOf(PlayerNumber.One) - setScore.totalPointsOf(PlayerNumber.Two) >= 2
                );
            }
        }

        @Nested
        @DisplayName("when some player earn `6` points and difference `<2`")
        public class WhenSomePlayerEarnSixPointsWithDifferenceLesserThanTwo {

            @BeforeEach
            public void beforeEach() {
                IntStream.range(0, 5)
                        .forEach((round) -> {
                            Assertions.assertEquals(ScoreState.UnknownWinner, setScore.pointFor(PlayerNumber.One));
                            Assertions.assertEquals(ScoreState.UnknownWinner, setScore.pointFor(PlayerNumber.Two));
                        });

                setScore.pointFor(PlayerNumber.One);

                Assertions.assertTrue(
                        setScore.totalPointsOf(PlayerNumber.One) - setScore.totalPointsOf(PlayerNumber.Two) < 2
                );
            }

            @Test
            @DisplayName("if he wins the next one, he is winner")
            public void ifHeWinsNextOneHeIsWinner() {
                Assertions.assertEquals(ScoreState.PlayerOneWin, setScore.pointFor(PlayerNumber.One));
            }

            @Test
            @DisplayName("if he loses, winner is unknown")
            public void ifHeLosesThenWinnerIsUnknown() {
                Assertions.assertEquals(ScoreState.UnknownWinner, setScore.pointFor(PlayerNumber.Two));
            }

            @Nested
            @DisplayName("and when both player earn `6` points then tie-break played")
            public class AndWhenBothPlayerEarnSixPoints {

                @BeforeEach
                public void beforeEach() {
                    setScore.pointFor(PlayerNumber.Two);

                    Assertions.assertEquals(6, setScore.totalPointsOf(PlayerNumber.One));
                    Assertions.assertEquals(6, setScore.totalPointsOf(PlayerNumber.Two));
                }

                @Test
                @DisplayName("if player one wins tie-break, he is winner")
                public void ifPlayerOneWinsHeIsWinner() {
                    Assertions.assertEquals(ScoreState.PlayerOneWin, setScore.pointFor(PlayerNumber.One));
                }

                @Test
                @DisplayName("if player two wins tie-break, he is winner")
                public void ifPlayerTwoWinsHeIsWinner() {
                    Assertions.assertEquals(ScoreState.PlayerTwoWin, setScore.pointFor(PlayerNumber.Two));
                }
            }
        }
    }
}
