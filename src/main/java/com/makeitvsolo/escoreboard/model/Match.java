package com.makeitvsolo.escoreboard.model;

import com.makeitvsolo.escoreboard.core.unique.Unique;

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

    public static Match result(Unique<UUID> id, Player playerOne, Player playerTwo, Player winner) {
        return new Match(id.unique(), playerOne, playerTwo, winner);
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
