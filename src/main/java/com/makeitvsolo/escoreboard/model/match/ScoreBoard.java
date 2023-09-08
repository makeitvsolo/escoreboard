package com.makeitvsolo.escoreboard.model.match;

import com.makeitvsolo.escoreboard.core.unique.Unique;
import com.makeitvsolo.escoreboard.model.match.exception.PlayerIsOutMatchException;
import com.makeitvsolo.escoreboard.model.scoring.PlayerNumber;
import com.makeitvsolo.escoreboard.model.scoring.Score;
import com.makeitvsolo.escoreboard.model.scoring.ScoreState;
import com.makeitvsolo.escoreboard.model.scoring.Zero;
import com.makeitvsolo.escoreboard.model.match.exception.WinnerAlreadyKnownException;

import java.util.Optional;
import java.util.UUID;

public final class ScoreBoard {
    private final UUID matchId;
    private final Player playerOne;
    private final Player playerTwo;
    private final Score score;
    private ScoreState state;

    ScoreBoard(UUID matchId, Player playerOne, Player playerTwo, Score score, ScoreState state) {
        this.matchId = matchId;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.score = score;
        this.state = state;
    }

    public static ScoreBoard startNewMatch(
            Unique<UUID> matchId,
            Player playerOne,
            Player playerTwo,
            Zero<Score> score
    ) {
        return new ScoreBoard(matchId.unique(), playerOne, playerTwo, score.zero(), ScoreState.UnknownWinner);
    }

    public UUID matchId() {
        return matchId;
    }

    public Player playerOne() {
        return playerOne;
    }

    public Player playerTwo() {
        return playerTwo;
    }

    public Optional<Player> winner() {
        return switch (state) {
            case UnknownWinner -> Optional.empty();

            case PlayerOneWin -> Optional.of(playerOne);

            case PlayerTwoWin -> Optional.of(playerTwo);
        };
    }

    public PlayerNumber numberOf(UUID playerId) {
        if (playerId.equals(playerOne.id())) {
            return PlayerNumber.One;
        }

        if (playerId.equals(playerTwo.id())) {
            return PlayerNumber.Two;
        }

        throw new PlayerIsOutMatchException(matchId, playerId);
    }

    public void pointFor(UUID playerId) {
        switch (state) {
            case UnknownWinner -> {
                state = score.pointFor(numberOf(playerId));
            }

            case PlayerOneWin, PlayerTwoWin -> {
                throw new WinnerAlreadyKnownException(matchId);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ScoreBoard other)) {
            return false;
        }

        return matchId.equals(other.matchId);
    }

    @Override
    public int hashCode() {
        return matchId.hashCode();
    }
}
