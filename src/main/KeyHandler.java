package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean musicOn;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
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

        if (gamePanel.gameBehavior == GamePanel.titleBehavior) {
            if (gamePanel.ui.titleScreenBehavior == 0) {
                if (value == KeyEvent.VK_W || value == KeyEvent.VK_UP) {
                    if (gamePanel.ui.commandNum != 0)
                        gamePanel.ui.commandNum--;
                }
                if (value == KeyEvent.VK_S || value == KeyEvent.VK_DOWN) {
                    if (gamePanel.ui.commandNum != 2)
                        gamePanel.ui.commandNum++;
                }
                if (value == KeyEvent.VK_ENTER) {
                    if (gamePanel.ui.commandNum == 0) {
                        gamePanel.ui.titleScreenBehavior = 1;

                    }
                    if (gamePanel.ui.commandNum == 1) {

                    }
                    if (gamePanel.ui.commandNum == 2) {
                        System.exit(0);
                    }
                }
            } else if (gamePanel.ui.titleScreenBehavior == 1) {
                if (value == KeyEvent.VK_W || value == KeyEvent.VK_UP) {

                    if (gamePanel.ui.commandNum != 0)
                        gamePanel.ui.commandNum--;
                }
                if (value == KeyEvent.VK_S || value == KeyEvent.VK_DOWN) {
                    if (gamePanel.ui.commandNum != 3)
                        gamePanel.ui.commandNum++;
                }
                if (value == KeyEvent.VK_ENTER) {
                    if (gamePanel.ui.commandNum == 0) {
                        System.out.println("Do some fighter specific stuff!");
                        gamePanel.gameBehavior = GamePanel.playBehavior;
                        gamePanel.playMusic(0);
                        enterPressed = false;

                    }
                    if (gamePanel.ui.commandNum == 1) {
                        System.out.println("Do some thief specific stuff!");
                        gamePanel.gameBehavior = GamePanel.playBehavior;
                        gamePanel.playMusic(0);
                    }
                    if (gamePanel.ui.commandNum == 2) {
                        System.out.println("Do some sorcerer specific stuff!");
                        gamePanel.gameBehavior = GamePanel.playBehavior;
                        gamePanel.playMusic(0);
                    }
                    if (gamePanel.ui.commandNum == 3) {
                        gamePanel.ui.titleScreenBehavior = 0;
                        gamePanel.ui.commandNum = 0;
                    }
                }
            }
        } else if (gamePanel.gameBehavior == GamePanel.playBehavior) {
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
            if (value == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
            if (value == KeyEvent.VK_P) {
                gamePanel.gameBehavior = GamePanel.pauseBehavior;
            }
            if (value == KeyEvent.VK_T) {

                chkDrawTime = !chkDrawTime;
            }
            if (value == KeyEvent.VK_M) {
                musTrigger();
                System.out.println(musicOn);
            }
            if (value == KeyEvent.VK_C) {
                gamePanel.gameBehavior = GamePanel.characterBehavior;
            }
        } else if (gamePanel.gameBehavior == GamePanel.pauseBehavior) {
            if (value == KeyEvent.VK_P) {
                gamePanel.gameBehavior = GamePanel.playBehavior;
            }

        } else if (gamePanel.gameBehavior == GamePanel.dialogBehavior) {
            if (value == KeyEvent.VK_ENTER) {
                gamePanel.gameBehavior = GamePanel.playBehavior;
            }
        } else if (gamePanel.gameBehavior == GamePanel.characterBehavior) {
            if (value == KeyEvent.VK_C) {
                gamePanel.gameBehavior = GamePanel.playBehavior;
            }
        }
    }

    private void musTrigger() {
        if (musicOn) {
            gamePanel.stopMusic();
            musicOn = false;
        } else {
            gamePanel.playMusic(0);
            musicOn = true;
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
        if (value == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
    }
}
