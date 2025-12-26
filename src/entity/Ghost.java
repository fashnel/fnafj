package entity;

import main.GamePanel;

public class Ghost extends Animatronic {
    static final double CHANCE = 0.07;
    boolean playerSeenGhost = false;

    public void update(long now) {
        if (now < timeToNextMove) {
            return;
        }
        if (timeToNextMove != 1)  {
            timeToNextMove = now + randomDelay(2000, 3000);
        }
        double chance = Math.random();

        switch (position) {
            case SCENE:
                if (chance < CHANCE && GamePanel.inTablet) {
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
                        timeToNextMove = now + 1300;
                    }
                    else {
                        position = Position.JUMPSCARE;
                    }

                }
                break;
            case JUMPSCARE:
                //timeToNextMove = now + randomDelay(2000, 3000);
                //забирает энергию
                position = Position.SCENE;
        }
    }
}
