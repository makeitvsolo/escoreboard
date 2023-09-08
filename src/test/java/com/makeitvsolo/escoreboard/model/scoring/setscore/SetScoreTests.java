package com.makeitvsolo.escoreboard.model.scoring.setscore;

import com.makeitvsolo.escoreboard.model.scoring.PlayerNumber;
import com.makeitvsolo.escoreboard.model.scoring.ScoreState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("player wins when he earns `6` points and difference is `>=2`; 6:0")
    public void sixZero() {
        setScore = SetScore.initial();

        IntStream.range(0, 5)
                .forEach((round) -> {
                    Assertions.assertEquals(ScoreState.UnknownWinner, setScore.pointFor(PlayerNumber.One));
                });

        Assertions.assertEquals(ScoreState.PlayerOneWin, setScore.pointFor(PlayerNumber.One));
        Assertions.assertEquals(6, setScore.totalPointsOf(PlayerNumber.One));
        Assertions.assertEquals(0, setScore.totalPointsOf(PlayerNumber.Two));
    }

    @Test
    @DisplayName("player wins when he earns `6` points and difference is `>=2`; 6:4")
    public void sixFour() {
        setScore = SetScore.initial();

        IntStream.range(0, 4)
                .forEach((round) -> {
                    Assertions.assertEquals(ScoreState.UnknownWinner, setScore.pointFor(PlayerNumber.One));
                    Assertions.assertEquals(ScoreState.UnknownWinner, setScore.pointFor(PlayerNumber.Two));
                });

        Assertions.assertEquals(ScoreState.UnknownWinner, setScore.pointFor(PlayerNumber.One));
        Assertions.assertEquals(ScoreState.PlayerOneWin, setScore.pointFor(PlayerNumber.One));
        Assertions.assertEquals(6, setScore.totalPointsOf(PlayerNumber.One));
        Assertions.assertEquals(4, setScore.totalPointsOf(PlayerNumber.Two));
    }

    @Test
    @DisplayName("player wins when he earns `6` points and difference is `>=2`; 8:6")
    public void eightSix() {
        setScore = SetScore.initial();

        IntStream.range(0, 6)
                .forEach((round) -> {
                    Assertions.assertEquals(ScoreState.UnknownWinner, setScore.pointFor(PlayerNumber.One));
                    Assertions.assertEquals(ScoreState.UnknownWinner, setScore.pointFor(PlayerNumber.Two));
                });

        Assertions.assertEquals(ScoreState.UnknownWinner, setScore.pointFor(PlayerNumber.One));
        Assertions.assertEquals(ScoreState.PlayerOneWin, setScore.pointFor(PlayerNumber.One));
        Assertions.assertEquals(8, setScore.totalPointsOf(PlayerNumber.One));
        Assertions.assertEquals(6, setScore.totalPointsOf(PlayerNumber.Two));
    }
}
