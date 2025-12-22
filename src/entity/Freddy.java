package entity;

import main.GamePanel;

public class Freddy extends Animatronic {
    static final double CHANCE = 0.3;

    public void update(long now) {
        if (now < timeToNextMove) {
            return;
        }

        timeToNextMove = now + randomDelay(5000, 10000);
        double chance = Math.random();

        switch (position) {
            case SCENE:
                if (chance < CHANCE) {
                    position = Position.STAFF_ONLY;
                }
                break;
            case STAFF_ONLY:
                if (chance < CHANCE) {
                    position = Position.HALL;
                }
                break;
            case HALL:
                if (chance < CHANCE) {
                    position = Position.RIGHT_HALL;
                }
                break;
            case RIGHT_HALL:
                if (chance < CHANCE) {
                    position = Position.OFFICE;
                }
                break;
            case OFFICE:
                if (GamePanel.rightDoorClosed) {
                    position = Position.SCENE;
                }
                else {
                    position = Position.JUMPSCARE;
                }
                break;
        }
    }
}
