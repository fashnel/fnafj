package main;

import entity.Position;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean leftDoor, rightDoor, tablet;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (GamePanel.gameState) {
            case GAME_OVER, WIN:
                if (code == KeyEvent.VK_ENTER) {
                    GamePanel.gameState = GameState.MENU;
                }
                break;

            case MENU:
                if (code == KeyEvent.VK_ENTER) {
                    GamePanel.gameState = GameState.GAME;
                }
                break;
        }

        if (GamePanel.inTablet) {
            switch (code) {
                case KeyEvent.VK_1:
                    GamePanel.camera = Position.SCENE;
                    break;

                case KeyEvent.VK_2:
                    GamePanel.camera = Position.STAFF_ONLY;
                    break;

                case KeyEvent.VK_3:
                    GamePanel.camera = Position.HALL;
                    break;

                case KeyEvent.VK_4:
                    GamePanel.camera = Position.WATER_CLOSET;
                    break;

                case KeyEvent.VK_5:
                    GamePanel.camera = Position.LEFT_HALL;
                    break;

                case KeyEvent.VK_6:
                    GamePanel.camera = Position.RIGHT_HALL;
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (GamePanel.gameState == GameState.GAME) {
            if (code == KeyEvent.VK_SPACE) {
                tablet = true;
            }
            if (code == KeyEvent.VK_Q) {
                leftDoor = true;
            }
            if (code == KeyEvent.VK_E) {
                rightDoor = true;
            }
        }
    }
}
