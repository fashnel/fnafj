package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean leftDoor, rightDoor;
    public boolean tablet, scene, waterCloset, staffOnly,
            hall, leftHall, rightHall;;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        scene = code == KeyEvent.VK_1;
        hall = code == KeyEvent.VK_3;
        leftHall = code == KeyEvent.VK_5;
        rightHall = code == KeyEvent.VK_6;
        waterCloset = code == KeyEvent.VK_4;
        staffOnly = code == KeyEvent.VK_2;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

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
