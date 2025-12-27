package entity;

public class Animatronic {
    public Position position = Position.SCENE;
    long timeToNextMove;
    private long timeToNextHour;

    public long randomDelay(int minMs, int maxMs) {
        return minMs + (long) (Math.random() * (maxMs - minMs));
    }

    public double updateChance(long now, double chance, double plus) {
        if (now < timeToNextHour) {
            return chance;
        }
        timeToNextHour = now + 30000;
        return chance + plus;
    }
}
