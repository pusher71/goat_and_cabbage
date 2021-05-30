package Model;

// Направление
public class Direction {
    private final int _clock;

    private Direction(int clock) {
        _clock = clock;
    }

    public static Direction north() {
        return new Direction(0);
    }

    public static Direction east() {
        return new Direction(3);
    }

    public static Direction south() {
        return new Direction(6);
    }

    public static Direction west() {
        return new Direction(9);
    }

    public Direction opposite() {
        return new Direction((_clock + 6) % 12);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof Direction)) return false;
        Direction direct = (Direction)other;
        return _clock == direct._clock;
    }

    @Override
    public int hashCode() {
        return _clock;
    }
}
