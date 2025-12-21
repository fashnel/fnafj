package main;

import entity.Animatronic;
import entity.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Draw {
    public static final int SCREEN_WIDTH = GamePanel.SCREEN_WIDTH,
            SCREEN_HEIGHT = GamePanel.SCREEN_HEIGHT;

    static BufferedImage officeImage, leftDoorImage, rightDoorImage,
            mapImage, leftDoorOnMap, rightDoorOnMap,
            sceneImage, hallImage, leftHallImage, rightHallImage, staffOnlyImage,
            freddyOnScene, freddyInOffice, freddyInHall, freddyInRightHall, freddyInStaffOnly,
            bonnieOnScene, bonnieInOffice, bonnieInHall, bonnieInLeftHall,
            cam1, cam2, cam3, cam4, cam5, cam6;

    static public void cameras(Graphics2D g2) {
        if (GamePanel.scene) {
            g2.drawImage(sceneImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);

            drawAnimatronics(g2, freddyOnScene, bonnieOnScene, Position.SCENE);
            drawMap(g2, cam1);
        }
        else if (GamePanel.staffOnly) {
            g2.drawImage(staffOnlyImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);

            drawAnimatronic(g2, freddyInStaffOnly, GamePanel.freddy, Position.STAFF_ONLY);
            drawMap(g2, cam2);
        }
        else if (GamePanel.hall) {
            g2.drawImage(hallImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);

            drawAnimatronics(g2, freddyInHall, bonnieInHall, Position.HALL);
            drawMap(g2, cam3);
        }
        else if (GamePanel.waterCloset) {
//                g2.drawImage(waterClosetImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
//
//                drawAnimatronic(g2, freddyInWaterCloset, freddy, Position.WATER_CLOSET);
            drawMap(g2, cam4);
        }
        else if (GamePanel.leftHall) {
            g2.drawImage(leftHallImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);

            drawAnimatronic(g2, bonnieInLeftHall, GamePanel.bonnie, Position.LEFT_HALL);
            drawMap(g2, cam5);
        }
        else if (GamePanel.rightHall) {
            g2.drawImage(rightHallImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);

            drawAnimatronic(g2, freddyInRightHall, GamePanel.freddy, Position.RIGHT_HALL);
            drawMap(g2, cam6);
        }
    }

    static public void office(Graphics2D g2) {
        g2.drawImage(officeImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);

        drawAnimatronics(g2, freddyInOffice, bonnieInOffice, Position.OFFICE);
        drawDoors(g2);
    }

    public static void loadImages() {
        try {
            officeImage = ImageIO.read(new File("assets\\office\\empty.JPG"));
            leftDoorImage = ImageIO.read(new File("assets\\office\\leftDoorClose.PNG"));
            rightDoorImage = ImageIO.read(new File("assets\\office\\rightDoorClose.PNG"));
            mapImage = ImageIO.read(new File("assets\\cameras\\map.PNG"));
            sceneImage = ImageIO.read(new File("assets\\cameras\\scene.JPG"));
            hallImage = ImageIO.read(new File("assets\\cameras\\hall.JPG"));
            leftHallImage = ImageIO.read(new File("assets\\cameras\\leftHall.JPG"));
            rightHallImage = ImageIO.read(new File("assets\\cameras\\rightHall.JPG"));
            staffOnlyImage = ImageIO.read(new File("assets\\cameras\\staffOnly.JPG"));
            freddyOnScene = ImageIO.read(new File("assets\\freddy\\scene.PNG"));
            bonnieOnScene = ImageIO.read(new File("assets\\bonnie\\scene.PNG"));
            freddyInOffice = ImageIO.read(new File("assets\\freddy\\office.PNG"));
            bonnieInOffice = ImageIO.read(new File("assets\\bonnie\\office.PNG"));
            freddyInHall = ImageIO.read(new File("assets\\freddy\\hall.PNG"));
            bonnieInHall = ImageIO.read(new File("assets\\bonnie\\hall.PNG"));
            freddyInRightHall = ImageIO.read(new File("assets\\freddy\\rightHall.PNG"));
            bonnieInLeftHall = ImageIO.read(new File("assets\\bonnie\\leftHall.PNG"));
            freddyInStaffOnly = ImageIO.read(new File("assets\\freddy\\staffOnly.PNG"));
            leftDoorOnMap = ImageIO.read(new File("assets\\cameras\\leftDoorOnMap.PNG"));;
            rightDoorOnMap = ImageIO.read(new File("assets\\cameras\\rightDoorOnMap.PNG"));;
            cam1 = ImageIO.read(new File("assets\\cameras\\cam1.PNG"));
            cam2 = ImageIO.read(new File("assets\\cameras\\cam2.PNG"));
            cam3 = ImageIO.read(new File("assets\\cameras\\cam3.PNG"));
            cam4 = ImageIO.read(new File("assets\\cameras\\cam4.PNG"));
            cam5 = ImageIO.read(new File("assets\\cameras\\cam5.PNG"));
            cam6 = ImageIO.read(new File("assets\\cameras\\cam6.PNG"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void drawDoors(Graphics2D g2) {
        if (GamePanel.leftDoorClosed) {
            g2.drawImage(leftDoorImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
        }
        if (GamePanel.rightDoorClosed) {
            g2.drawImage(rightDoorImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
        }
    }

    static public void drawAnimatronics(Graphics2D g2, BufferedImage placeFreddy, BufferedImage placeBonnie, Position position) {
        if (GamePanel.freddy.position == position) {
            g2.drawImage(placeFreddy, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
        }
        if (GamePanel.bonnie.position == position) {
            g2.drawImage(placeBonnie, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
        }
    }

    static public void drawAnimatronic(Graphics2D g2, BufferedImage place, Animatronic animatronic, Position position) {
        if (animatronic.position == position) {
            g2.drawImage(place, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
        }
    }

    static public void drawMap(Graphics2D g2, BufferedImage cam) {
        int x = -90, y = 460;
        g2.drawImage(mapImage, x, y, 650, 470, null);
        if (GamePanel.leftDoorClosed) {
            g2.drawImage(leftDoorOnMap, x, y, 650, 470, null);
        }
        if (GamePanel.rightDoorClosed) {
            g2.drawImage(rightDoorOnMap, x, y, 650, 470, null);
        }
        g2.drawImage(cam, x, y, 650, 470, null);
    }
}
