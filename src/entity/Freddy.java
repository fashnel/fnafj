package entity;

import main.GamePanel;
import main.GameState;

public class Freddy extends Animatronic {
    double CONST_CHANCE = 0.4,
            chanceForMove,
            chance;

    public void update(long now) {
        if (now < timeToNextMove) {
            return;
        }
        CONST_CHANCE = updateChanceEveryHour(now, CONST_CHANCE, 0.1);
        if (GamePanel.camera == position && position != Position.SCENE) {
            chanceForMove = CONST_CHANCE / 2;
            timeToNextMove = now + randomDelay(4000, 6000);
        }
        else {
            chanceForMove = CONST_CHANCE;
            timeToNextMove = now + randomDelay(500, 1000);
        }
        chance = Math.random();
        switch (position) {
            case SCENE, STAFF_ONLY, HALL, WATER_CLOSET:
                if (chance < chanceForMove) {
                    position = Position.random();
                }
                break;
            case RIGHT_HALL:
                if (chance < chanceForMove) {
                    position = Position.OFFICE;
                    timeToNextMove = now + randomDelay(6000, 8000);
                }
                break;
            case OFFICE:
                if (GamePanel.rightDoorClosed ||
                        GamePanel.bonnie.position == Position.JUMPSCARE) {
                    position = Position.random();
                } else {
                    timeToNextMove = now + randomDelay(3000, 5000);
                    position = Position.JUMPSCARE;
                }
                break;
            case JUMPSCARE:
                GamePanel.gameState = GameState.GAME_OVER;
                position = Position.SCENE;
        }
    }
}
