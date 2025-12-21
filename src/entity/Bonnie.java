package entity;

import main.GamePanel;

public class Bonnie extends Animatronic {
    public void update(long now) {
        if (now < timeToNextMove) {
            return;
        }

        timeToNextMove = now + randomDelay(3000, 6000);
        double chance = Math.random();

        switch (position) {
            case SCENE:
                if (chance < 0.3) {
                    position = Position.HALL;
                }
                break;
            case HALL:
                if (chance < 0.3) {
                    position = Position.LEFT_HALL;
                }
                break;
            case LEFT_HALL:
                if (chance < 0.3) {
                    position = Position.OFFICE;
                }
                break;
            case OFFICE:
                if (GamePanel.leftDoorClosed) {
                    position = Position.SCENE;
                }
                break;
        }
    }
}