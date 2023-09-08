package com.makeitvsolo.escoreboard.model.scoring;

public enum ScoreState {
    UnknownWinner, PlayerOneWin, PlayerTwoWin;

    public static ScoreState unknownWinner() {
        return UnknownWinner;
    }

    public static ScoreState winnerIs(PlayerNumber playerNumber) {
        return switch (playerNumber) {
            case One -> PlayerOneWin;

            case Two -> PlayerTwoWin;
        };
    }
}
