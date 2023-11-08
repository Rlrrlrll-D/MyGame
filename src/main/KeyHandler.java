package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean musicOn;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean chkDrawTime = false;
    GamePanel gamePanel;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int value = e.getKeyCode();
        if (gamePanel.gameBehavior == gamePanel.playBehavior) {
            if (value == KeyEvent.VK_W || value == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (value == KeyEvent.VK_S || value == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (value == KeyEvent.VK_A || value == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (value == KeyEvent.VK_D || value == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (value == KeyEvent.VK_P) {
                gamePanel.gameBehavior = gamePanel.pauseBehavior;
            }
            if (value == KeyEvent.VK_T) {

                chkDrawTime = !chkDrawTime;
            }
            if (value == KeyEvent.VK_M) {

                if (musicOn) {
                    gamePanel.stopMusic();
                    musicOn = false;
                } else {
                    gamePanel.playMusic(0);
                    musicOn = true;
                }
                System.out.println(musicOn);
            }
        } else if (gamePanel.gameBehavior == gamePanel.pauseBehavior) {
            if (value == KeyEvent.VK_P) {
                gamePanel.gameBehavior = gamePanel.playBehavior;
            }

        } else if (gamePanel.gameBehavior == gamePanel.dialogBehavior) {
            if (value == KeyEvent.VK_ENTER) {
                gamePanel.gameBehavior = gamePanel.playBehavior;
            }

        }


    }


    @Override
    public void keyReleased(KeyEvent e) {
        int value = e.getKeyCode();
        if (value == KeyEvent.VK_W || value == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (value == KeyEvent.VK_S || value == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (value == KeyEvent.VK_A || value == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (value == KeyEvent.VK_D || value == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
