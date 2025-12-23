package entity;

import main.GamePanel;

public class Bonnie extends Animatronic {
    static final double CHANCE = 0.3;

    public void update(long now) {
        if (now < timeToNextMove) {
            return;
        }

        timeToNextMove = now + randomDelay(3000, 5000);
        double chance = Math.random();

        switch (position) {
            case SCENE:
                if (chance < CHANCE) {
                    position = Position.HALL;
                }
                break;
            case HALL:
                if (chance < CHANCE) {
                    position = Position.LEFT_HALL;
                }
                break;
            case LEFT_HALL:
                if (chance < CHANCE) {
                    position = Position.OFFICE;
                }
                break;
            case OFFICE:
                if (GamePanel.leftDoorClosed) {
                    position = Position.SCENE;
                }
                else {
                    position = Position.JUMPSCARE;
                }
                break;
        }
    }
}