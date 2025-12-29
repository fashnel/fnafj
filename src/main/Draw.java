package main;

import entity.Animatronic;
import entity.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Draw {
    private static final int SCREEN_WIDTH = GamePanel.SCREEN_WIDTH,
            SCREEN_HEIGHT = GamePanel.SCREEN_HEIGHT;
    private static final Random RANDOM = new Random();
    private static Font GAME_FONT;
    public static Color enterColor = Color.WHITE, escColor = Color.WHITE;

    static BufferedImage officeImage, leftDoorImage, rightDoorImage, mapImage,
            sceneImage, hallImage, waterClosetImage, leftHallImage, rightHallImage, staffOnlyImage,
            freddyOnScene, freddyInOffice, freddyInHall, freddyInRightHall,
            freddyInStaffOnly, freddyInWaterCloset, jumpscareFreddy,
            bonnieOnScene, bonnieInOffice, bonnieInHall, bonnieInLeftHall, jumpscareBonnie,
            ghostInOffice, jumpscareGhost,
            cam1, cam2, cam3, cam4, cam5, cam6,
            menuImage, loseImage, winImage;

    private static void drawCameras(Graphics2D g2) {
        switch (GamePanel.camera) {
            case SCENE:
                drawCamera(g2, sceneImage, cam1,
                        null, bonnieOnScene);
                break;

            case STAFF_ONLY:
                drawCamera(g2, staffOnlyImage, cam2,
                        freddyInStaffOnly, null);
                break;

            case HALL:
                drawCamera(g2, hallImage, cam3,
                        freddyInHall, bonnieInHall);
                break;

            case WATER_CLOSET:
                drawCamera(g2, waterClosetImage, cam4,
                        freddyInWaterCloset, null);
                break;

            case LEFT_HALL:
                drawCamera(g2, leftHallImage, cam5,
                        null, bonnieInLeftHall);
                break;

            case RIGHT_HALL:
                drawCamera(g2, rightHallImage, cam6,
                        freddyInRightHall, null);
                break;
        }
    }

    private static void drawOffice(Graphics2D g2) {
        g2.drawImage(officeImage, 0, 0, null);
        drawAnimatronicsInOffice(g2, freddyInOffice, bonnieInOffice, ghostInOffice);
        drawDoors(g2);
    }

    public static void game(Graphics2D g2) {
        if (GamePanel.inTablet) {
            drawCameras(g2);
        }
        else {
            drawOffice(g2);
            drawPower(g2, GamePanel.power);
        }
        drawTime(g2, GamePanel.time);
        drawJumpscares(g2);
    }

    public static void menu(Graphics2D g2) {
        g2.drawImage(menuImage, 0, 0, null);

        drawString(g2, "Enter to start", 78, 700, enterColor, 60);
        drawString(g2, "Esc to exit", 78, 800, escColor, 60);
    }

    public static void endScreen(Graphics2D g2, String picture) {
        if (picture.equals("win")) {
            g2.drawImage(winImage, 0, 0, null);
        } else {
            g2.drawImage(loseImage, 0, 0,null);
        }
        drawString(g2, "Enter to continue", 150, 800, enterColor, 60);
    }

    public static void loadImages() {
        try {
            officeImage = ImageIO.read(new File("assets\\office\\empty.JPG"));
            leftDoorImage = ImageIO.read(new File("assets\\office\\leftDoorClose.PNG"));
            rightDoorImage = ImageIO.read(new File("assets\\office\\rightDoorClose.PNG"));
            mapImage = ImageIO.read(new File("assets\\cameras\\map.PNG"));
            sceneImage = ImageIO.read(new File("assets\\cameras\\scene.JPG"));
            hallImage = ImageIO.read(new File("assets\\cameras\\hall.JPG"));
            waterClosetImage = ImageIO.read(new File("assets\\cameras\\waterCloset.JPG"));
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
            freddyInWaterCloset = ImageIO.read(new File("assets\\freddy\\waterCloset.PNG"));
            ghostInOffice = ImageIO.read(new File("assets\\ghost\\office.PNG"));
            cam1 = ImageIO.read(new File("assets\\cameras\\cam1.PNG"));
            cam2 = ImageIO.read(new File("assets\\cameras\\cam2.PNG"));
            cam3 = ImageIO.read(new File("assets\\cameras\\cam3.PNG"));
            cam4 = ImageIO.read(new File("assets\\cameras\\cam4.PNG"));
            cam5 = ImageIO.read(new File("assets\\cameras\\cam5.PNG"));
            cam6 = ImageIO.read(new File("assets\\cameras\\cam6.PNG"));
            jumpscareFreddy = ImageIO.read(new File("assets\\freddy\\jumpscare.JPG"));
            jumpscareBonnie = ImageIO.read(new File("assets\\bonnie\\jumpscare.JPG"));
            jumpscareGhost = ImageIO.read(new File("assets\\ghost\\jumpscare.PNG"));
            menuImage = ImageIO.read(new File("assets\\menu\\menu.JPG"));
            loseImage = ImageIO.read(new File("assets\\menu\\lose.JPG"));
            winImage = ImageIO.read(new File("assets\\menu\\win.JPG"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadFonts() {
        try {
            InputStream loadFont = Draw.class.getResourceAsStream("/main/fnaf_font.ttf");
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, loadFont);
            GAME_FONT = baseFont.deriveFont(Font.PLAIN, 70);
        } catch (IOException | FontFormatException e) {
            GAME_FONT = new Font("Concolas", Font.PLAIN, 70);
        }
    }

    private static void drawCamera(Graphics2D g2, BufferedImage place, BufferedImage cam,
                                   BufferedImage freddyImage, BufferedImage bonnieImage) {
        drawPlace(g2, place);
        drawAnimatronics(g2, freddyImage, bonnieImage, GamePanel.camera);
//        drawMap(g2, cam);
    }

    private static void drawPlace(Graphics2D g2, BufferedImage camera) {
        g2.drawImage(camera, 0, 0, null);
    }

    private static void drawDoors(Graphics2D g2) {
        if (GamePanel.leftDoorClosed) {
            g2.drawImage(leftDoorImage, 0, 0, null);
        }
        if (GamePanel.rightDoorClosed) {
            g2.drawImage(rightDoorImage, 0, 0, null);
        }
    }

    private static void drawAnimatronicsInOffice(Graphics2D g2,
                                                 BufferedImage freddyImage,
                                                 BufferedImage bonnieImage,
                                                 BufferedImage ghostImage) {
        if (GamePanel.freddy.position == Position.OFFICE) {
            g2.drawImage(freddyImage, 0, 0, null);
        }
        if (GamePanel.bonnie.position == Position.OFFICE) {
            g2.drawImage(bonnieImage, 0, 0, null);
        }
        if (GamePanel.ghost.position == Position.OFFICE) {
            g2.drawImage(ghostImage, 0, 0, null);
        }
    }

    private static void drawAnimatronics(Graphics2D g2, BufferedImage placeFreddy,
                                        BufferedImage placeBonnie, Position position) {
        if (GamePanel.freddy.position == position && placeFreddy != null) {
            g2.drawImage(placeFreddy, 0, 0, null);
        }
        if (GamePanel.bonnie.position == position && placeBonnie != null) {
            g2.drawImage(placeBonnie, 0, 0, null);
        }
    }

    private static void drawMap(Graphics2D g2, BufferedImage cam) {
        int x = -90, y = 460;
        g2.drawImage(mapImage, x, y, null);
        g2.drawImage(cam, x, y, null);
    }

    private static void jumpscare(Graphics2D g2, Animatronic animatronic,
                                 BufferedImage image, int value) {
        if (animatronic.position == Position.JUMPSCARE) {
            int randInt1 = RANDOM.nextInt(-value, value),
                    randInt2 = RANDOM.nextInt(-value, value),
                    startPoint = RANDOM.nextInt(-value, 0);
            g2.drawImage(image, startPoint, startPoint,
                    SCREEN_WIDTH + value * 3 + randInt1,
                    SCREEN_HEIGHT + value * 2 + randInt2, null);
        }
    }

    private static void drawJumpscares(Graphics2D g2) {
        jumpscare(g2, GamePanel.bonnie, jumpscareBonnie, 30);
        jumpscare(g2, GamePanel.freddy, jumpscareFreddy, 100);
        jumpscare(g2, GamePanel.ghost, jumpscareGhost, 5);
    }

    private static void drawTime(Graphics2D g2, String string) {
        drawString(g2, string, 1090, 50, Color.WHITE, 40);
    }

    private static void drawPower(Graphics2D g2, int power) {
        drawString(g2, String.format("Power:%s%%", power), 10, 890, Color.WHITE, 40);
    }

    private static void drawString(Graphics2D g2, String string, int x, int y, Color color, int size) {
        g2.setColor(color);
        g2.setFont(Draw.GAME_FONT.deriveFont(Font.PLAIN, size));
        g2.drawString(string, x, y);
    }
}
