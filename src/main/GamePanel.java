package main;

import entity.*;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {
    public static final int SCREEN_WIDTH = 1300, SCREEN_HEIGHT = 900;
    final int FPS = 30;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    public static Bonnie bonnie = new Bonnie();
    public static Freddy freddy = new Freddy();
    public static Ghost ghost = new Ghost();
    public static Office office = new Office();
    public static Tablet tablet = new Tablet();
    String time = "";
    int power;

    public static boolean leftDoorClosed = false, rightDoorClosed = false, inTablet;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        Draw.loadImages();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        long now = System.currentTimeMillis();
        bonnie.update(now);
        freddy.update(now);
        ghost.update(now);
        time = office.time(now);
        power = office.power(now);

        if (keyH.tablet) {
            inTablet = !inTablet;
            keyH.tablet = false;
        }
        if (inTablet) {
            keyH.rightDoor = false;
            keyH.leftDoor = false;
            tablet.update(keyH);
        }
        else {
            if (keyH.leftDoor) {
                leftDoorClosed = !leftDoorClosed;
                keyH.leftDoor = false;
            }
            if (keyH.rightDoor) {
                rightDoorClosed = !rightDoorClosed;
                keyH.rightDoor = false;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (inTablet) {
            Draw.cameras(g2);
        }
        else {
            Draw.office(g2);
            Draw.power(g2, power);
        }
        Draw.time(g2, time);
        Draw.jumpscares(g2, 100);
    }
}
