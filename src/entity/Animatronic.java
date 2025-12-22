package entity;

public class Animatronic {
    public Position position = Position.RIGHT_HALL;
    long timeToNextMove;

    public long randomDelay(int minMs, int maxMs) {
        return minMs + (long) (Math.random() * (maxMs - minMs));
    }
}
