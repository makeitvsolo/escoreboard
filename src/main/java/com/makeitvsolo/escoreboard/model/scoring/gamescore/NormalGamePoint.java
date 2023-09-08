package com.makeitvsolo.escoreboard.model.scoring.gamescore;

import com.makeitvsolo.escoreboard.model.scoring.exception.WrongPointException;

public enum NormalGamePoint {
    Zero("0"), Fifteen("15"), Thirty("30"), Forty("40"), Advantage("AD");

    private final String value;

    NormalGamePoint(final String value) {
        this.value = value;
    }

    public static NormalGamePoint initial() {
        return Zero;
    }

    public static NormalGamePoint winPoint() {
        return Forty;
    }

    public static NormalGamePoint advantage() {
        return Advantage;
    }

    public boolean greaterEqualsThan(NormalGamePoint other) {
        return switch (this) {
            case Zero -> switch (other) {
                case Zero -> true;
                case Fifteen, Thirty, Forty, Advantage -> false;
            };

            case Fifteen -> switch (other) {
                case Zero, Fifteen -> true;
                case Thirty, Forty, Advantage -> false;
            };

            case Thirty -> switch (other) {
                case Zero, Fifteen, Thirty -> true;
                case Forty, Advantage -> false;
            };

            case Forty -> switch (other) {
                case Zero, Fifteen, Thirty, Forty -> true;
                case  Advantage -> false;
            };

            case Advantage -> true;
        };
    }

    public NormalGamePoint up() {
        return switch (this) {
            case Zero -> Fifteen;
            case Fifteen -> Thirty;
            case Thirty -> Forty;
            case Forty -> Advantage;

            case Advantage -> throw new WrongPointException("No point after `AD`");
        };
    }
}
