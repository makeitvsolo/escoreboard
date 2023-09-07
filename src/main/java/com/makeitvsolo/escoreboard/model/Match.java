package com.makeitvsolo.escoreboard.model;

import com.makeitvsolo.escoreboard.core.unique.Unique;
import com.makeitvsolo.escoreboard.model.exception.MatchIsOngoingException;

import java.util.UUID;

public final class Match {
    private final UUID id;
    private final Player playerOne;
    private final Player playerTwo;
    private final Player winner;

    Match(UUID id, Player playerOne, Player playerTwo, Player winner) {
        this.id = id;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.winner = winner;
    }

    public static Match resultFrom(ScoreBoard scoreBoard) {
        return new Match(
                scoreBoard.matchId(),
                scoreBoard.playerOne(),
                scoreBoard.playerTwo(),
                scoreBoard.winner().
                        orElseThrow(() -> new MatchIsOngoingException(scoreBoard.matchId()))
        );
    }

    public UUID id() {
        return id;
    }

    public Player playerOne() {
        return playerOne;
    }

    public Player playerTwo() {
        return playerTwo;
    }

    public Player winner() {
        return winner;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Match other)) {
            return false;
        }

        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
