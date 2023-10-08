package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int value = e.getKeyCode();
        if (value == KeyEvent.VK_W||value ==KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (value == KeyEvent.VK_S||value==KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (value == KeyEvent.VK_A||value==KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (value == KeyEvent.VK_D||value==KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int value = e.getKeyCode();
        if (value == KeyEvent.VK_W||value ==KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (value == KeyEvent.VK_S||value==KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (value == KeyEvent.VK_A||value==KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (value == KeyEvent.VK_D||value==KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
