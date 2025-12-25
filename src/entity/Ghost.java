package entity;

import main.GamePanel;

public class Ghost extends Animatronic {
    static final double CHANCE = 0.07;

    public void update(long now) {
        if (now < timeToNextMove) {
            return;
        }
        timeToNextMove = now + randomDelay(2000, 3000);
        double chance = Math.random();

        switch (position) {
            case SCENE:
                if (chance < CHANCE && GamePanel.inTablet) {
                    position = Position.OFFICE;
                    System.out.println("пришел");
                }
                break;
            case OFFICE:
                if (GamePanel.inTablet) {
                    System.out.println("ушел");
                    position = Position.SCENE;
                }
                else {
                    System.out.println("напугал");
                    position = Position.JUMPSCARE;
                }
                break;
            case JUMPSCARE:
                //timeToNextMove = now + randomDelay(2000, 3000);
                //забирает энергию
                position = Position.SCENE;
        }
    }
}
