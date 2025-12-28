package entity;

import static main.GamePanel.oneHourInSeconds;

public class Animatronic {
    public Position position = Position.SCENE;
    long timeToNextMove;
    private long timeToNextHour;

    public long randomDelay(int minMs, int maxMs) {
        return minMs + (long) (Math.random() * (maxMs - minMs));
    }

    public double updateChanceEveryHour(long now, double chance, double up) {
        if (now < timeToNextHour) {
            return chance;
        }
        timeToNextHour = now + oneHourInSeconds;
        return chance + up;
    }
}
