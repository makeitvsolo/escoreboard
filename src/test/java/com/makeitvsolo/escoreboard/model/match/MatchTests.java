package com.makeitvsolo.escoreboard.model.match;

import com.makeitvsolo.escoreboard.model.common.exception.MatchIsOngoingException;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

@DisplayName("Match")
public class MatchTests {
    private Match match;
    @Mock
    private ScoreBoard scoreBoard;

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
    @DisplayName("can record result from ScoreBoard when some player win")
    public void canRecordScoreBoardWhenSomePlayerWin() {
        var playerOne = new Player(UUID.randomUUID(), "Player One");
        var playerTwo = new Player(UUID.randomUUID(), "Player One");
        var winner = playerOne;

        Mockito.when(scoreBoard.matchId())
                .thenReturn(UUID.randomUUID());
        Mockito.when(scoreBoard.playerOne())
                .thenReturn(playerOne);
        Mockito.when(scoreBoard.playerTwo())
                .thenReturn(playerTwo);
        Mockito.when(scoreBoard.winner())
                .thenReturn(Optional.of(playerOne));

        match = Match.resultFrom(scoreBoard);

        Assertions.assertEquals(playerOne, match.playerOne());
        Assertions.assertEquals(playerTwo, match.playerTwo());
        Assertions.assertEquals(winner, match.winner());
    }

    @Test
    @DisplayName("or throws when match is ongoing")
    public void orThrowsWhenMatchIsOngoing() {
        var playerOne = new Player(UUID.randomUUID(), "Player One");
        var playerTwo = new Player(UUID.randomUUID(), "Player One");

        Mockito.when(scoreBoard.matchId())
                .thenReturn(UUID.randomUUID());
        Mockito.when(scoreBoard.playerOne())
                .thenReturn(playerOne);
        Mockito.when(scoreBoard.playerTwo())
                .thenReturn(playerTwo);
        Mockito.when(scoreBoard.winner())
                .thenReturn(Optional.empty());

        Assertions.assertThrows(MatchIsOngoingException.class, () -> match = Match.resultFrom(scoreBoard));
    }
}
