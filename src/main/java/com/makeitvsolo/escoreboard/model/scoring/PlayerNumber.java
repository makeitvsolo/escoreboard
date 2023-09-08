package com.makeitvsolo.escoreboard.model.scoring;

public enum PlayerNumber {
    One, Two;

    public PlayerNumber other() {
        return switch (this) {
            case One -> Two;

            case Two -> One;
        };
    }
}
