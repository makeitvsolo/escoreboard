package com.makeitvsolo.escoreboard.model.match;

import com.makeitvsolo.escoreboard.core.unique.Unique;

import java.util.UUID;

public final class Player {
    private final UUID id;
    private final String name;

    Player(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Player register(Unique<UUID> id, String name) {
        return new Player(id.unique(), name);
    }

    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Player other)) {
            return false;
        }

        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
