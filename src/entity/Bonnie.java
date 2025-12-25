package entity;

import main.GamePanel;

public class Bonnie extends Animatronic {
    static final double CHANCE = 0.3;

    public void update(long now) {
        if (now < timeToNextMove) {
            return;
        }
        if (timeToNextMove != 1)  {
            timeToNextMove = now + randomDelay(3000, 5000);
        }
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
                    timeToNextMove = now + 2000;
                }
                break;
            case LEFT_HALL:
                timeToNextMove = 1;
                if (GamePanel.inTablet) {
                    position = Position.OFFICE;
                    timeToNextMove = now + randomDelay(3000, 5000);
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