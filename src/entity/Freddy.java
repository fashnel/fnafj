package entity;

import main.GamePanel;

public class Freddy extends Animatronic {
    static double CHANCE = 0.3;

    public void update(long now) {
        if (now < timeToNextMove) {
            return;
        }

        timeToNextMove = now + randomDelay(5000, 10000);
        double chance = Math.random();
        Position previousPosition;

        switch (position) {
            case SCENE, STAFF_ONLY, HALL, WATER_CLOSET:
                if (chance < CHANCE) {
                    previousPosition = position;
                    while (previousPosition == position){
                        position = Position.random();
                    }
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
