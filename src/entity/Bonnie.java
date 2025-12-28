package entity;

import main.GamePanel;
import main.GameState;

public class Bonnie extends Animatronic {
    double CONST_CHANCE = 0.1,
            chanceForMove, chance;

    public void update(long now) {
        if (now < timeToNextMove) {
            return;
        }
        CONST_CHANCE = updateChanceEveryHour(now, CONST_CHANCE, 0.1);
        if (GamePanel.tablet.position == position) {
            chanceForMove = CONST_CHANCE * 1.5;
            timeToNextMove = now + randomDelay(1000, 2000);
        }
        else {
            chanceForMove = CONST_CHANCE;
            timeToNextMove = now + randomDelay(3000, 5000);
        }
        chance = Math.random();
        switch (position) {
            case SCENE:
                if (chance < chanceForMove) {
                    position = Position.HALL;
                }
                break;
            case HALL:
                if (chance < chanceForMove) {
                    position = Position.LEFT_HALL;
                }
                break;
            case LEFT_HALL:
                if (chance < chanceForMove) {
                    position = Position.OFFICE;
                }
                break;
            case OFFICE:
                if (GamePanel.leftDoorClosed ||
                        GamePanel.freddy.position == Position.JUMPSCARE) {
                    position = Position.SCENE;
                }
                else {
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