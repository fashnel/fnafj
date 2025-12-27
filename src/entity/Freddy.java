package entity;

import main.GamePanel;

public class Freddy extends Animatronic {
    double CONST_CHANCE = 0.5,
            chanceForMove;

    public void update(long now) {
        if (now < timeToNextMove) {
            return;
        }
        CONST_CHANCE = updateChance(now, CONST_CHANCE, 0.05);
        if (GamePanel.tablet.position == position && position != Position.SCENE) {
            chanceForMove = CONST_CHANCE / 1.5;
        }
        else {
            chanceForMove = CONST_CHANCE;
        }
        timeToNextMove = now + randomDelay(5000, 10000);
        double chance = Math.random();

        switch (position) {
            case SCENE, STAFF_ONLY, HALL, WATER_CLOSET:
                if (chance < chanceForMove) {
                    position = Position.random();
                }
                break;
            case RIGHT_HALL:
                if (chance < chanceForMove) {
                    position = Position.OFFICE;
                }
                break;
            case OFFICE:
                if (GamePanel.rightDoorClosed) {
                    position = Position.SCENE;
                } else {
                    position = Position.JUMPSCARE;
                }
                break;
        }
    }
}
