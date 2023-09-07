package com.makeitvsolo.escoreboard.model;

import com.makeitvsolo.escoreboard.core.unique.Unique;
import com.makeitvsolo.escoreboard.model.exception.WinnerAlreadyKnownException;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

@DisplayName("ScoreBoard")
public class ScoreBoardTest {
    private ScoreBoard scoreBoard;
    private Player playerOne;
    private Player playerTwo;
    @Mock
    private Unique<UUID> matchId;
    @Mock
    private Zero<Score> zeroScore;
    @Mock
    private Score score;

    private AutoCloseable closeable;

    @BeforeEach
    public void beforeEach() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void afterEach() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("winner is unknown after start")
    public void matchIsOngoingAfterStart() {
        playerOne = new Player(UUID.randomUUID(), "Player One");
        playerTwo = new Player(UUID.randomUUID(), "Player Two");

        Mockito.when(zeroScore.zero())
                .thenReturn(score);

        scoreBoard = ScoreBoard.startNewMatch(matchId, playerOne, playerTwo, zeroScore);

        Assertions.assertEquals(Optional.empty(), scoreBoard.winner());
    }

    @Nested
    @DisplayName("while winner is unknown")
    public class WhileWinnerIsUnknown {

        @BeforeEach
        public void beforeEach() {
            playerOne = new Player(UUID.randomUUID(), "Player One");
            playerTwo = new Player(UUID.randomUUID(), "Player Two");
            Mockito.when(zeroScore.zero())
                    .thenReturn(score);

            scoreBoard = ScoreBoard.startNewMatch(matchId, playerOne, playerTwo, zeroScore);
        }

        @Test
        @DisplayName("players can earn points and match continues")
        public void playersCanEarnPoints() {
            Mockito.when(score.pointFor(playerOne.id()))
                    .thenReturn(ScoreState.MatchIsOngoing);

            Assertions.assertEquals(Optional.empty(), scoreBoard.winner());
        }

        @Test
        @DisplayName("when player one wins, he's the winner.")
        public void whenPlayerOneWinsHeIsWinner() {
            Mockito.when(score.pointFor(playerOne.id()))
                    .thenReturn(ScoreState.PlayerOneWin);

            scoreBoard.pointFor(playerOne.id());

            Assertions.assertEquals(Optional.of(playerOne), scoreBoard.winner());
        }

        @Test
        @DisplayName("when player two wins, he's the winner.")
        public void whenPlayerTwoWinsHeIsWinner() {
            Mockito.when(score.pointFor(playerTwo.id()))
                    .thenReturn(ScoreState.PlayerTwoWin);

            scoreBoard.pointFor(playerTwo.id());

            Assertions.assertEquals(Optional.of(playerTwo), scoreBoard.winner());
        }

        @Nested
        @DisplayName("when winner is known")
        public class WhenWinnerIsKnown {

            @BeforeEach
            public void beforeEach() {
                Mockito.when(score.pointFor(playerOne.id()))
                        .thenReturn(ScoreState.PlayerOneWin);

                scoreBoard.pointFor(playerOne.id());
            }

            @Test
            @DisplayName("no player can earn points anymore")
            public void noPlayersCanEarnPointsAnymore() {
                Assertions.assertThrows(WinnerAlreadyKnownException.class, () -> scoreBoard.pointFor(playerOne.id()));
                Assertions.assertThrows(WinnerAlreadyKnownException.class, () -> scoreBoard.pointFor(playerTwo.id()));
            }
        }
    }
}
