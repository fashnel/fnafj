package main;

import entity.*;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {
    public static final int SCREEN_WIDTH = 1300, SCREEN_HEIGHT = 900;
    public static final int oneHourInSeconds = 25000;
    final int FPS = 30;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    public static Bonnie bonnie;
    public static Freddy freddy;
    public static Ghost ghost;
    public static Office office;
    public static GameState gameState = GameState.MENU;
    public static Position camera = Position.SCENE;
    public static String time;
    public static int power;

    public static boolean inTablet,
            leftDoorClosed, rightDoorClosed;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        Draw.loadImages();
        Draw.loadFonts();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void inputInGame() {
        if (keyH.tablet) {
            inTablet = !inTablet;
            keyH.tablet = false;
        }
        if (inTablet) {
            keyH.rightDoor = false;
            keyH.leftDoor = false;
        }
        if (keyH.leftDoor) {
            leftDoorClosed = !leftDoorClosed;
            keyH.leftDoor = false;
        }
        if (keyH.rightDoor) {
            rightDoorClosed = !rightDoorClosed;
            keyH.rightDoor = false;
        }

    }

    public void updateEntity() {
        long now = System.currentTimeMillis();
        bonnie.update(now);
        freddy.update(now);
        ghost.update(now);
        time = office.time(now);
        power = office.power(now);
    }

    public void updateGameStates() {
        if (time.trim().equals("6AM")) {
            gameState = GameState.WIN;
        }
        if (power == 0) {
            gameState = GameState.GAME_OVER;
        }
    }

    public void startNewGame() {
        inTablet = false;
        leftDoorClosed = false;
        rightDoorClosed = false;
        freddy = new Freddy();
        bonnie = new Bonnie();
        ghost = new Ghost();
        office = new Office();
        camera = Position.SCENE;
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
        switch (gameState) {
            case GAME:
                updateEntity();
                updateGameStates();
                inputInGame();
                break;
            case MENU:
                startNewGame();
                break;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        switch (gameState) {
            case GAME:
                Draw.game(g2);
                break;
            case MENU:
                Draw.menu(g2);
                break;
            case GAME_OVER:
                Draw.endScreen(g2, "game_over");
                break;
            case WIN:
                Draw.endScreen(g2, "win");
                break;
        }
    }
}
