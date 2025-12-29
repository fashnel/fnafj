package entity;

import main.GamePanel;

public class Ghost extends Animatronic {
    double CHANCE = 0.1,
            chance;
    boolean playerSeenGhost = false;

    public void update(long now) {
        if (now < timeToNextMove) {
            return;
        }
        CHANCE = updateChanceEveryHour(now, CHANCE, 0.05);
        if (timeToNextMove != 1)  {
            timeToNextMove = now + randomDelay(2000, 3000);
        }
        chance = Math.random();

        switch (position) {
            case SCENE:
                if (chance < CHANCE) {
                    position = Position.OFFICE;
                    timeToNextMove = 1;
                }
                break;
            case OFFICE:
                if (GamePanel.inTablet) {
                    if (playerSeenGhost) {
                        playerSeenGhost = false;
                        position = Position.SCENE;
                    }
                    else {
                        timeToNextMove = 1;
                    }
                }
                else {
                    if (timeToNextMove == 1) {
                        playerSeenGhost = true;
                        timeToNextMove = now + 1000;
                    }
                    else if ((GamePanel.bonnie.position == Position.JUMPSCARE) ||
                            (GamePanel.freddy.position == Position.JUMPSCARE)) {
                        position = Position.SCENE;
                    }
                    else {
                        position = Position.JUMPSCARE;
                        if (GamePanel.office.powerPercent < 10) {
                            GamePanel.office.powerPercent = 0;
                        }
                        else {
                            GamePanel.office.powerPercent -= 10;
                        }
                    }
                }
                break;
            case JUMPSCARE:
                position = Position.SCENE;
        }
    }
}
