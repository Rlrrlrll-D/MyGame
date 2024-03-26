package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed, spacePressed;
    public boolean chkDrawTime = false;

    public boolean modeOfGod;
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
            try {
                titleBehavior(value);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if (gamePanel.gameBehavior == GamePanel.playBehavior) {
            try {
                playBehavior(value);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if (gamePanel.gameBehavior == GamePanel.pauseBehavior) {
            pauseBehavior(value);

        } else if (gamePanel.gameBehavior == GamePanel.dialogBehavior || gamePanel.gameBehavior == GamePanel.cutSceneBehavior) {
            dialogBehavior(value);
        } else if (gamePanel.gameBehavior == GamePanel.characterBehavior) {
            characterBehavior(value);
        } else if (gamePanel.gameBehavior == GamePanel.optionsBehavior) {
            optionsBehavior(value);
        } else if (gamePanel.gameBehavior == GamePanel.gameOverBehavior) {
            gameOverBehavior(value);
        } else if (gamePanel.gameBehavior == GamePanel.tradeBehavior) {
            tradeBehavior(value);
        } else if (gamePanel.gameBehavior == GamePanel.mapBehavior) {
            mapBehavior(value);
        }

    }

    private void mapBehavior(int value) {
        if (value == KeyEvent.VK_CONTROL) {
            gamePanel.gameBehavior = GamePanel.playBehavior;

        }
    }

    private void tradeBehavior(int value) {
        if (value == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (gamePanel.ui.subBehavior == 0) {
            if (value == KeyEvent.VK_W || value == KeyEvent.VK_UP) {
                if (gamePanel.ui.commandNum > 0) {
                    gamePanel.ui.commandNum--;
                    gamePanel.playSFX(12);
                }
            }
            if (value == KeyEvent.VK_S || value == KeyEvent.VK_DOWN) {
                if (gamePanel.ui.commandNum < 2) {
                    gamePanel.ui.commandNum++;
                    gamePanel.playSFX(12);
                }
            }
        }
        if (gamePanel.ui.subBehavior == 1) {
            npcInventory(value);
            if (value == KeyEvent.VK_ESCAPE) {
                gamePanel.ui.subBehavior = 0;
            }
        }
        if (gamePanel.ui.subBehavior == 2) {
            playerInventory(value);
            if (value == KeyEvent.VK_ESCAPE) {
                gamePanel.ui.subBehavior = 0;
            }
        }
    }

    private void gameOverBehavior(int value) {
        if (value == KeyEvent.VK_W || value == KeyEvent.VK_UP) {
            if (gamePanel.ui.commandNum != 0 && gamePanel.ui.commandNum != -1) {
                gamePanel.ui.commandNum--;
                gamePanel.playSFX(12);
            }
        }
        if (value == KeyEvent.VK_S || value == KeyEvent.VK_DOWN) {
            if (gamePanel.ui.commandNum != 1 && gamePanel.ui.commandNum != -1) {
                gamePanel.ui.commandNum++;
                gamePanel.playSFX(12);
            } else if (gamePanel.ui.commandNum == -1) {
                gamePanel.ui.commandNum += 2;
                gamePanel.playSFX(12);
            }
        }
        if (value == KeyEvent.VK_ENTER) {
            if (gamePanel.ui.commandNum == 0 || gamePanel.ui.commandNum == -1) {
                gamePanel.resetGame(false);
                gamePanel.gameBehavior = GamePanel.playBehavior;
                gamePanel.ui.subBehavior = 0;
                musicCheck(0);
            } else if (gamePanel.ui.commandNum == 1) {
                gamePanel.ui.commandNum = 0;
                gamePanel.gameBehavior = GamePanel.titleBehavior;
                gamePanel.ui.subBehavior = 0;
                gamePanel.resetGame(true);
            }
        }
    }

    private void optionsBehavior(int value) {

        if (value == KeyEvent.VK_ESCAPE) {
            gamePanel.gameBehavior = GamePanel.playBehavior;
        }
        if (value == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        if (value == KeyEvent.VK_W || value == KeyEvent.VK_UP) {
            if (gamePanel.ui.subBehavior == 0 || gamePanel.ui.subBehavior == 3) {
                if (gamePanel.ui.commandNum != 0) {
                    gamePanel.ui.commandNum--;
                    gamePanel.playSFX(12);
                }
            }
        }
        if (value == KeyEvent.VK_S || value == KeyEvent.VK_DOWN) {
            if (gamePanel.ui.subBehavior == 0) {
                if (gamePanel.ui.commandNum != 5) {
                    gamePanel.ui.commandNum++;
                    gamePanel.playSFX(12);
                }
            } else if (gamePanel.ui.subBehavior == 3) {
                if (gamePanel.ui.commandNum != 1) {
                    gamePanel.ui.commandNum++;
                    gamePanel.playSFX(12);
                }
            }
        }
        if (value == KeyEvent.VK_A || value == KeyEvent.VK_LEFT) {
            if (gamePanel.ui.subBehavior == 0) {
                if (gamePanel.ui.commandNum == 1 && gamePanel.sound.volumeScale > 0) {
                    gamePanel.sound.volumeScale--;
                    gamePanel.sound.chkVolume();
                    gamePanel.playSFX(12);
                }
                if (gamePanel.ui.commandNum == 2 && gamePanel.SFX.volumeScale > 0) {
                    gamePanel.SFX.volumeScale--;
                    gamePanel.playSFX(12);
                }
            }
        }
        if (value == KeyEvent.VK_D || value == KeyEvent.VK_RIGHT) {
            if (gamePanel.ui.subBehavior == 0) {
                if (gamePanel.ui.commandNum == 1 && gamePanel.sound.volumeScale < 5) {
                    gamePanel.sound.volumeScale++;
                    gamePanel.sound.chkVolume();
                    gamePanel.playSFX(12);
                }
                if (gamePanel.ui.commandNum == 2 && gamePanel.SFX.volumeScale < 5) {
                    gamePanel.SFX.volumeScale++;
                    gamePanel.playSFX(12);
                }
            }
        }
    }

    private void characterBehavior(int value) {
        if (value == KeyEvent.VK_C) {
            gamePanel.gameBehavior = GamePanel.playBehavior;
        }
        if (value == KeyEvent.VK_ENTER) {
            gamePanel.player.selectItem();
        }
        playerInventory(value);

    }

    private void playerInventory(int value) {
        if (value == KeyEvent.VK_W || value == KeyEvent.VK_UP) {
            if (gamePanel.ui.playerSlotRow > 0) {
                gamePanel.ui.playerSlotRow--;
                gamePanel.playSFX(12);
            }
        }
        if (value == KeyEvent.VK_S || value == KeyEvent.VK_DOWN) {
            if (gamePanel.ui.playerSlotRow < 3) {
                gamePanel.ui.playerSlotRow++;
                gamePanel.playSFX(12);
            }
        }

        if (value == KeyEvent.VK_A || value == KeyEvent.VK_LEFT) {
            if (gamePanel.ui.playerSlotCol > 0) {
                gamePanel.ui.playerSlotCol--;
                gamePanel.playSFX(12);
            }
        }

        if (value == KeyEvent.VK_D || value == KeyEvent.VK_RIGHT) {
            if (gamePanel.ui.playerSlotCol < 4) {
                gamePanel.ui.playerSlotCol++;
                gamePanel.playSFX(12);
            }
        }
    }

    private void npcInventory(int value) {
        if (value == KeyEvent.VK_W || value == KeyEvent.VK_UP) {
            if (gamePanel.ui.npcSlotRow > 0) {
                gamePanel.ui.npcSlotRow--;
                gamePanel.playSFX(12);
            }
        }
        if (value == KeyEvent.VK_S || value == KeyEvent.VK_DOWN) {
            if (gamePanel.ui.npcSlotRow < 3) {
                gamePanel.ui.npcSlotRow++;
                gamePanel.playSFX(12);
            }
        }

        if (value == KeyEvent.VK_A || value == KeyEvent.VK_LEFT) {
            if (gamePanel.ui.npcSlotCol > 0) {
                gamePanel.ui.npcSlotCol--;
                gamePanel.playSFX(12);
            }
        }

        if (value == KeyEvent.VK_D || value == KeyEvent.VK_RIGHT) {
            if (gamePanel.ui.npcSlotCol < 4) {
                gamePanel.ui.npcSlotCol++;
                gamePanel.playSFX(12);
            }
        }
    }

    private void dialogBehavior(int value) {
        if (value == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
    }

    private void pauseBehavior(int value) {
        if (value == KeyEvent.VK_P) {
            gamePanel.gameBehavior = GamePanel.playBehavior;
        }
    }

    private void playBehavior(int value) throws Exception {
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
        if (value == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }
        if (value == KeyEvent.VK_P) {
            gamePanel.gameBehavior = GamePanel.pauseBehavior;
        }
        if (value == KeyEvent.VK_T) {
            chkDrawTime = !chkDrawTime;
        }
        if (value == KeyEvent.VK_G) {
            modeOfGod = !modeOfGod;
        }
        if (value == KeyEvent.VK_M) {
            musTrigger();
        }
        if (value == KeyEvent.VK_R) {
            switch (gamePanel.currentMap) {
                case 0:
                    gamePanel.tileManager.loadMap("/maps/world01.txt", 0);
                    break;
                case 1:
                    gamePanel.tileManager.loadMap("/maps/world02.txt", 1);
                    break;
                case 2:
                    gamePanel.tileManager.loadMap("/maps/dungeon01.txt", 2);
                    break;
                case 3:
                    gamePanel.tileManager.loadMap("/maps/dungeon02.txt", 3);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + gamePanel.currentMap);
            }
        }
        if (value == KeyEvent.VK_C) {
            gamePanel.gameBehavior = GamePanel.characterBehavior;
        }
        if (value == KeyEvent.VK_ESCAPE) {
            gamePanel.gameBehavior = GamePanel.optionsBehavior;
        }
        if (value == KeyEvent.VK_CONTROL) {
            gamePanel.gameBehavior = GamePanel.mapBehavior;
        }
        if (value == KeyEvent.VK_X) {
            gamePanel.map.miniMap = !gamePanel.map.miniMap;
        }
        if (value == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }

    }

    private void titleBehavior(int value) throws IOException, ClassNotFoundException {
        if (value == KeyEvent.VK_W || value == KeyEvent.VK_UP) {
            if (gamePanel.ui.commandNum != 0) {
                gamePanel.ui.commandNum--;
                gamePanel.playSFX(12);
            }
        }
        if (value == KeyEvent.VK_S || value == KeyEvent.VK_DOWN) {
            if (gamePanel.ui.commandNum != 2) {
                gamePanel.ui.commandNum++;
                gamePanel.playSFX(12);
            }
        }
        if (value == KeyEvent.VK_ENTER) {
            if (gamePanel.ui.commandNum == 0) {
                gamePanel.gameBehavior = GamePanel.playBehavior;
                gamePanel.musicOn = false;
                musicCheck(0);
            }
            if (gamePanel.ui.commandNum == 1) {
                gamePanel.saveLoad.load();
                gamePanel.gameBehavior = GamePanel.playBehavior;
                gamePanel.musicOn = false;
                musicCheck(0);
            }
            if (gamePanel.ui.commandNum == 2) {
                System.exit(0);
            }
        }
    }

    protected void musicCheck(int value) {
        if (!gamePanel.musicOn) {
            gamePanel.playMusic(value);
            gamePanel.musicOn = true;
        }
    }

    public void musTrigger() {
        if (gamePanel.musicOn) {
            if (gamePanel.sound.volumeScale > 0) {
                gamePanel.sound.volumeScale = 0;
            } else {
                gamePanel.sound.volumeScale = 2;
            }
            gamePanel.sound.chkVolume();
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
        if (value == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }
        if (value == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if (value == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }
}
