package entity;

import main.KeyHandler;

public class Tablet {
    public Position position = Position.SCENE;

    public void update(KeyHandler keyH) {
        if (keyH.scene) {
            position = Position.SCENE;
        }
        if (keyH.staffOnly) {
            position = Position.STAFF_ONLY;
        }
        if (keyH.hall) {
            position = Position.HALL;
        }
        if (keyH.waterCloset) {
            position = Position.WATER_CLOSET;
        }
        if (keyH.rightHall) {
            position = Position.RIGHT_HALL;
        }
        if (keyH.leftHall) {
            position = Position.LEFT_HALL;
        }
    }
}
