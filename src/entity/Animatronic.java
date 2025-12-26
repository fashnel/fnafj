package entity;

public class Animatronic {
    public Position position = Position.SCENE;
    long timeToNextMove;

    public long randomDelay(int minMs, int maxMs) {
        return minMs + (long) (Math.random() * (maxMs - minMs));
    }
}
