package main;

import entity.Entity;
import entity.Player;
import objects.CoinBronze;
import objects.Heart;
import objects.ManaCrystal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {

    public String dialogue;
    public boolean msgOn;
    public int count;
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    public int commandNum = 0;
    public int titleScreenBehavior = 0;

    GamePanel gamePanel;
    BufferedImage heart_f, heart_h, heart_e, crystal_f, crystal_e, coin;
    Graphics2D graphics2D;
    public Font Purisa, Pixel, Monica;
    ArrayList<String> message = new ArrayList<>();
    int subBehavior;
    int temp;
    ArrayList<Integer> counter = new ArrayList<>();
    public Entity npc;


    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        try {
            InputStream inputStream = getClass().getResourceAsStream("/res/fonts/Purisa Bold.ttf");
            assert inputStream != null;
            Purisa = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            inputStream = getClass().getResourceAsStream("/res/fonts/Pixel Regular.otf");
            assert inputStream != null;
            Pixel = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            inputStream = getClass().getResourceAsStream("/res/fonts/x12y16pxMaruMonica.ttf");
            assert inputStream != null;
            Monica = Font.createFont(Font.TRUETYPE_FONT, inputStream);

        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        Entity heart = new Heart(gamePanel);
        heart_e = heart.image;
        heart_h = heart.image1;
        heart_f = heart.image2;
        Entity crystal = new ManaCrystal(gamePanel);
        crystal_f = crystal.image;
        crystal_e = crystal.image1;
        Entity coinBronze = new CoinBronze(gamePanel);
        coin = coinBronze.down1;

    }

    public void addMsg(String text) {

        message.add(text);
        counter.add(0);
    }

    public void msgDelay(int delay) {
        count++;
        if (count >= delay) {
            count = 0;
            msgOn = false;
        }
    }

    public void drawing(Graphics2D graphics2D) throws IOException {
        this.graphics2D = graphics2D;

        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (gamePanel.gameBehavior == GamePanel.titleBehavior) {
            drawTitleScreen();
        }

        if (gamePanel.gameBehavior == GamePanel.playBehavior) {
            drawPlayerLife();
            drawMessage();
        }
        if (gamePanel.gameBehavior == GamePanel.pauseBehavior) {
            drawPlayerLife();
            drawPauseScreenShadow();
            drawPauseScreenYellow();

        }
        if (gamePanel.gameBehavior == GamePanel.dialogBehavior) {
            drawDialogScreen();
        }
        if (gamePanel.gameBehavior == GamePanel.characterBehavior) {
            drawCharacterScreen();
            drawInventory(gamePanel.player, true);
        }
        if (gamePanel.gameBehavior == GamePanel.optionsBehavior) {
            drawOptionScreen();
        }
        if (gamePanel.gameBehavior == GamePanel.gameOverBehavior) {
            drawGameOverScreen();
        }
        if (gamePanel.gameBehavior == GamePanel.transitionBehavior) {
            drawTransition();
        }
        if (gamePanel.gameBehavior == GamePanel.tradeBehavior) {
            drawTradeScreen();
        }
        if (gamePanel.gameBehavior == GamePanel.sleepBehavior) {
            drawSleepScreen();
        }
    }

    private void drawSleepScreen() {
        count++;
        if (count < 120) {
            gamePanel.environmentManager.lighting.filterAlpha += 0.01f;
            if (gamePanel.environmentManager.lighting.filterAlpha > 1f) {
                gamePanel.environmentManager.lighting.filterAlpha = 1f;
            }
        }
        if (count >= 120) {
            gamePanel.environmentManager.lighting.filterAlpha -= 0.01f;
            if (gamePanel.environmentManager.lighting.filterAlpha <= 0f) {
                gamePanel.environmentManager.lighting.filterAlpha = 0f;
                count = 0;
                gamePanel.environmentManager.lighting.dayState = gamePanel.environmentManager.lighting.day;
                gamePanel.environmentManager.lighting.dayCounter = 0;
                gamePanel.gameBehavior = GamePanel.playBehavior;
                gamePanel.player.getImg();
            }
        }
    }

    private void drawTradeScreen() {
        switch (subBehavior) {
            case 0:
                trade_select();
                break;
            case 1:
                trade_buy();
                break;
            case 2:
                trade_sell();
                break;
        }
        gamePanel.keyHandler.enterPressed = false;
    }

    public void trade_select() {
        drawDialogScreen();

        int x = gamePanel.tileSize * 15;
        int y = (int) (gamePanel.tileSize * 4.5);
        int width = gamePanel.tileSize * 3;
        int height = (int) (gamePanel.tileSize * 3.5);
        drawSubWindow(x, y, width, height);

        x += gamePanel.tileSize;
        y += gamePanel.tileSize;
        graphics2D.drawString("Buy", x, y);
        if (commandNum == 0) {
            graphics2D.drawString(">", x - 25, y);
            if (gamePanel.keyHandler.enterPressed) {
                subBehavior = 1;
            }
        }
        y += gamePanel.tileSize;
        graphics2D.drawString("Sell", x, y);
        if (commandNum == 1) {
            graphics2D.drawString(">", x - 25, y);
            if (gamePanel.keyHandler.enterPressed) {
                subBehavior = 2;
            }
        }
        y += gamePanel.tileSize;
        graphics2D.drawString("Leave", x, y);
        if (commandNum == 2) {
            graphics2D.drawString(">", x - 25, y);
            if (gamePanel.keyHandler.enterPressed) {
                commandNum = 0;
                gamePanel.gameBehavior = GamePanel.dialogBehavior;
                dialogue = "Come again, motherfucker!";
            }
        }

    }

    public void trade_buy() {
        drawInventory(gamePanel.player, false);
        drawInventory(npc, true);

        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize * 9;
        int height = gamePanel.tileSize * 2;
        int width = gamePanel.tileSize * 6;
        drawSubWindow(x, y, width, height);
        graphics2D.drawString("[ESC] Back", x + 24, y + 60);

        x = gamePanel.tileSize * 12;
        y = gamePanel.tileSize * 9;
        height = gamePanel.tileSize * 2;
        width = gamePanel.tileSize * 6;
        drawSubWindow(x, y, width, height);
        graphics2D.drawString("Your coin: " + gamePanel.player.coin, x + 24, y + 60);

        int itemIndex = getItemIndex(npcSlotCol, npcSlotRow);
        if (itemIndex < npc.inventory.size()) {
            x = (int) (gamePanel.tileSize * 5.5);
            y = (int) (gamePanel.tileSize * 5.5);
            height = gamePanel.tileSize;
            width = (int) (gamePanel.tileSize * 2.5);
            drawSubWindow(x, y, width, height);
            graphics2D.drawImage(coin, x + 10, y + 8, 32, 32, null);

            int price = npc.inventory.get(itemIndex).price;
            String txt = " " + price;
            x = getX_Value(txt, gamePanel.tileSize * 8 - 15);
            graphics2D.drawString(txt, x, y + 32);

            if (gamePanel.keyHandler.enterPressed) {
                if (npc.inventory.get(itemIndex).price > gamePanel.player.coin) {
                    subBehavior = 0;
                    gamePanel.gameBehavior = GamePanel.dialogBehavior;
                    dialogue = "You need more coin to buy that!";
                    drawDialogScreen();
                } else {
                    if (gamePanel.player.canObtainItem(npc.inventory.get(itemIndex))) {
                        gamePanel.player.coin -= npc.inventory.get(itemIndex).price;
                    } else {
                        subBehavior = 0;
                        gamePanel.gameBehavior = GamePanel.dialogBehavior;
                        dialogue = "You cannot carry anymore!";
                    }
                }
            }
        }
    }

    public void trade_sell() {
        drawInventory(gamePanel.player, true);
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize * 9;
        int height = gamePanel.tileSize * 2;
        int width = gamePanel.tileSize * 6;
        drawSubWindow(x, y, width, height);
        graphics2D.drawString("[ESC] Back", x + 24, y + 60);

        x = gamePanel.tileSize * 12;
        y = gamePanel.tileSize * 9;
        height = gamePanel.tileSize * 2;
        width = gamePanel.tileSize * 6;
        drawSubWindow(x, y, width, height);
        graphics2D.drawString("Your coin: " + gamePanel.player.coin, x + 24, y + 60);

        int itemIndex = getItemIndex(playerSlotCol, playerSlotRow);
        if (itemIndex < gamePanel.player.inventory.size()) {
            x = (int) (gamePanel.tileSize * 15.5);
            y = (int) (gamePanel.tileSize * 5.5);
            height = gamePanel.tileSize;
            width = (int) (gamePanel.tileSize * 2.5);
            drawSubWindow(x, y, width, height);
            graphics2D.drawImage(coin, x + 10, y + 8, 32, 32, null);

            int price = gamePanel.player.inventory.get(itemIndex).price / 2;
            String txt = " " + price;
            x = getX_Value(txt, gamePanel.tileSize * 18 - 15);
            graphics2D.drawString(txt, x, y + 32);

            if (gamePanel.keyHandler.enterPressed) {
                if (gamePanel.player.inventory.get(itemIndex) == gamePanel.player.currentWeapon ||
                        gamePanel.player.inventory.get(itemIndex) == gamePanel.player.currentShield) {
                    commandNum = 0;
                    subBehavior = 0;
                    gamePanel.gameBehavior = GamePanel.dialogBehavior;
                    dialogue = "You cannot sell an equipped item!";
                } else {
                    if (gamePanel.player.inventory.get(itemIndex).amount > 1) {
                        gamePanel.player.inventory.get(itemIndex).amount--;
                    } else {
                        gamePanel.player.inventory.remove(itemIndex);
                    }
                    gamePanel.player.coin += price;
                }
            }
        }
    }

    private void drawTransition() {
        temp++;

        if (temp >= 0 && temp < 50) {
            if (temp == 1) {
                gamePanel.playSFX(13);
            }
            graphics2D.setColor(new Color(0, 0, 0, temp * 5));
            graphics2D.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        } else if (temp == 50) {
            gamePanel.currentMap = gamePanel.eventHandler.tmpMap;
            gamePanel.player.worldX = gamePanel.tileSize * gamePanel.eventHandler.tmpCol;
            gamePanel.player.worldY = gamePanel.tileSize * gamePanel.eventHandler.tmpRow;
            gamePanel.eventHandler.previousEventX = gamePanel.player.worldX;
            gamePanel.eventHandler.previousEventY = gamePanel.player.worldY;
            graphics2D.setColor(new Color(0, 0, 0, temp * 5));
            graphics2D.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        } else if (temp > 50 && temp < 100) {

            graphics2D.setColor(new Color(0, 0, 0, 250 - (temp - 50) * 5));
            graphics2D.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        } else if (temp == 100) {
            gamePanel.gameBehavior = GamePanel.playBehavior;
            temp = 0;
        }
    }

    private void drawGameOverScreen() {

        graphics2D.setFont(Monica.deriveFont(Font.BOLD, 70F));

        graphics2D.setColor(new Color(12, 23, 30));
        graphics2D.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        int textX;
        int textY;

        graphics2D.setColor(new Color(6, 6, 17));
        String txt = "Game Over";
        textX = getX_Text(txt);
        textY = gamePanel.tileSize * 3;
        graphics2D.drawString(txt, textX, textY);

        graphics2D.setColor(new Color(229, 152, 9));
        textX = getX_Text(txt) - 4;
        textY = (gamePanel.tileSize * 3) - 4;
        graphics2D.drawString(txt, textX, textY);

        graphics2D.setFont(Monica.deriveFont(Font.BOLD, 45F));
        txt = "Retry";
        textX = getX_Text(txt);
        textY = gamePanel.tileSize * 7;
        graphics2D.drawString(txt, textX, textY);
        if (commandNum == 0 || commandNum == -1) {
            graphics2D.drawString(">", textX - 25, textY);
        }

        txt = "Quit";
        textX = getX_Text(txt);
        textY = (int) (gamePanel.tileSize * 8.25);
        graphics2D.drawString(txt, textX, textY);
        if (commandNum == 1) {
            graphics2D.drawString(">", textX - 25, textY);
        }
    }

    private void drawOptionScreen() throws IOException {
        graphics2D.setColor(new Color(229, 152, 9));
        graphics2D.setFont(Monica.deriveFont(Font.PLAIN, 25F));

        int x = gamePanel.tileSize * 6;
        int y = gamePanel.tileSize;
        int width = gamePanel.tileSize * 8;
        int height = gamePanel.tileSize * 10;
        drawSubWindow(x, y, width, height);

        switch (subBehavior) {
            case 0:
                top(x, y);
                break;
            case 1:
                fullScreenNotification(x, y);
                break;
            case 2:
                control(x, y);
                break;
            case 3:
                endGame(x, y);
                break;
        }
        gamePanel.keyHandler.enterPressed = false;

    }

    private void endGame(int x, int y) {
        int textX = x + gamePanel.tileSize;
        int textY = y + gamePanel.tileSize * 3;

        dialogue = "Quit the game and \nreturn to the title screen?";

        for (String line : dialogue.split("\n")) {
            graphics2D.drawString(line, textX, textY);
            textY += 40;
        }
        String txt = "Yes";
        textX = getX_Text(txt);
        textY += gamePanel.tileSize * 3;
        graphics2D.drawString(txt, textX, textY);
        if (commandNum == 0) {
            graphics2D.drawString(">", textX - 25, textY);
            if (gamePanel.keyHandler.enterPressed) {
                subBehavior = 0;
                gamePanel.gameBehavior = GamePanel.titleBehavior;
                gamePanel.resetGame(true);
            }
        }
        txt = "No";
        textX = getX_Text(txt);
        textY += gamePanel.tileSize;
        graphics2D.drawString(txt, textX, textY);
        if (commandNum == 1) {
            graphics2D.drawString(">", textX - 25, textY);
            if (gamePanel.keyHandler.enterPressed) {
                subBehavior = 0;
                commandNum = 4;
            }
        }
    }

    private void top(int x, int y) throws IOException {
        int textX;
        int textY;

        String txt = "Options";
        textX = getX_Text(txt);
        textY = y + gamePanel.tileSize;
        graphics2D.drawString(txt, textX, textY);

        txt = "Full Screen";
        textX = x + gamePanel.tileSize;
        textY += gamePanel.tileSize;
        graphics2D.drawString(txt, textX, textY);
        if (commandNum == 0) {
            graphics2D.drawString(">", textX - 25, textY);
            if (gamePanel.keyHandler.enterPressed) {
                gamePanel.fullScreenOn = !gamePanel.fullScreenOn;
                subBehavior = 1;
            }
        }

        txt = "Music";
        textY += gamePanel.tileSize;
        graphics2D.drawString(txt, textX, textY);
        if (commandNum == 1) {
            graphics2D.drawString(">", textX - 25, textY);
        }

        txt = "SE";
        textY += gamePanel.tileSize;
        graphics2D.drawString(txt, textX, textY);
        if (commandNum == 2) {
            graphics2D.drawString(">", textX - 25, textY);
        }

        txt = "Control";
        textY += gamePanel.tileSize;
        graphics2D.drawString(txt, textX, textY);
        if (commandNum == 3) {
            graphics2D.drawString(">", textX - 25, textY);
            if (gamePanel.keyHandler.enterPressed) {
                subBehavior = 2;
                commandNum = 0;
            }
        }

        txt = "End Game";
        textY += gamePanel.tileSize;
        graphics2D.drawString(txt, textX, textY);
        if (commandNum == 4) {
            graphics2D.drawString(">", textX - 25, textY);
            if (gamePanel.keyHandler.enterPressed) {
                subBehavior = 3;
                commandNum = 0;
            }
        }

        txt = "Back";
        textX = getX_Text(txt);
        textY += gamePanel.tileSize * 2;
        graphics2D.drawString(txt, textX, textY);
        if (commandNum == 5) {
            graphics2D.drawString(">", textX - 25, textY);
            if (gamePanel.keyHandler.enterPressed) {
                gamePanel.gameBehavior = GamePanel.playBehavior;
                commandNum = 0;
            }
        }
        textX = (int) (x + gamePanel.tileSize * 4.5);
        textY = gamePanel.tileSize * 2 + gamePanel.tileSize / 2;
        graphics2D.setStroke(new BasicStroke(2));
        graphics2D.drawRect(textX, textY, gamePanel.tileSize / 2, gamePanel.tileSize / 2);
        if (gamePanel.fullScreenOn) {
            graphics2D.fillRect(textX, textY, gamePanel.tileSize / 2, gamePanel.tileSize / 2);
        }

        textY += gamePanel.tileSize;
        graphics2D.drawRect(textX, textY, (gamePanel.tileSize / 2) * 5, gamePanel.tileSize / 2);
        int volumeWidth = gamePanel.tileSize / 2 * gamePanel.sound.volumeScale;
        graphics2D.fillRect(textX, textY, volumeWidth, gamePanel.tileSize / 2);

        textY += gamePanel.tileSize;
        graphics2D.drawRect(textX, textY, (gamePanel.tileSize / 2) * 5, gamePanel.tileSize / 2);
        volumeWidth = gamePanel.tileSize / 2 * gamePanel.SFX.volumeScale;
        graphics2D.fillRect(textX, textY, volumeWidth, gamePanel.tileSize / 2);
        gamePanel.config.saveConfig();

    }

    private void control(int x, int y) {
        int textX;
        int textY;

        String txt = "Control";
        textX = getX_Text(txt);
        textY = y + gamePanel.tileSize;
        graphics2D.drawString(txt, textX, textY);

        textX = x + gamePanel.tileSize;
        textY += gamePanel.tileSize;
        graphics2D.drawString("Move", textX, textY);
        textY += (int) (gamePanel.tileSize * 0.85);
        graphics2D.drawString("Confirm/Attack", textX, textY);
        textY += (int) (gamePanel.tileSize * 0.85);
        graphics2D.drawString("Shoot/Cast", textX, textY);
        textY += (int) (gamePanel.tileSize * 0.85);
        graphics2D.drawString("Character Screen", textX, textY);
        textY += (int) (gamePanel.tileSize * 0.85);
        graphics2D.drawString("Pause", textX, textY);
        textY += (int) (gamePanel.tileSize * 0.85);
        graphics2D.drawString("Mute", textX, textY);
        textY += (int) (gamePanel.tileSize * 0.85);
        graphics2D.drawString("Options", textX, textY);


        textX = x + gamePanel.tileSize * 6;
        textY = y + gamePanel.tileSize * 2;
        graphics2D.drawString("WASD", textX, textY);
        textY += (int) (gamePanel.tileSize * 0.85);
        graphics2D.drawString("Enter", textX, textY);
        textY += (int) (gamePanel.tileSize * 0.85);
        graphics2D.drawString("F", textX, textY);
        textY += (int) (gamePanel.tileSize * 0.85);
        graphics2D.drawString("C", textX, textY);
        textY += (int) (gamePanel.tileSize * 0.85);
        graphics2D.drawString("P", textX, textY);
        textY += (int) (gamePanel.tileSize * 0.85);
        graphics2D.drawString("M", textX, textY);
        textY += (int) (gamePanel.tileSize * 0.85);
        graphics2D.drawString("Esc", textX, textY);


        txt = "Back";
        textX = getX_Text(txt);
        textY = y + gamePanel.tileSize * 9;
        graphics2D.drawString(txt, textX, textY);
        if (commandNum == 0) {
            graphics2D.drawString(">", textX - 25, textY);
            if (gamePanel.keyHandler.enterPressed) {
                subBehavior = 0;
                commandNum = 3;
            }
        }


    }

    private void fullScreenNotification(int x, int y) {
        int textX = x + gamePanel.tileSize;
        int textY = y + gamePanel.tileSize * 3;

        dialogue = "The change will take \neffect after restarting \nthe game";
        for (String line : dialogue.split("\n")) {
            graphics2D.drawString(line, textX, textY);

            textY += 40;
        }
        textY = y + gamePanel.tileSize * 9;
        graphics2D.drawString("Back", textX, textY);
        if (commandNum == 0) {
            graphics2D.drawString(">", textX - 25, textY);
            if (gamePanel.keyHandler.enterPressed) {
                subBehavior = 0;
            }
        }
    }

    private void drawInventory(Entity entity, boolean cursor) {
        int x;
        int y;
        int height;
        int width;
        int slotCol;
        int slotRow;
        if (entity instanceof Player) {
            x = gamePanel.tileSize * 12;
            y = gamePanel.tileSize;
            height = gamePanel.tileSize * 5;
            width = gamePanel.tileSize * 6;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        } else {
            x = gamePanel.tileSize * 2;
            y = gamePanel.tileSize;
            height = gamePanel.tileSize * 5;
            width = gamePanel.tileSize * 6;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        drawSubWindow(x, y, width, height);
        final int slotXStart = x + gamePanel.tileSize / 2;
        final int slotYStart = y + gamePanel.tileSize / 2;
        int slotX = slotXStart;
        int slotY = slotYStart;

        for (int i = 0; i < entity.inventory.size(); i++) {

            if (entity.inventory.get(i) == entity.currentWeapon ||
                    entity.inventory.get(i) == entity.currentShield ||
                    entity.inventory.get(i) == entity.currentLight) {
                graphics2D.setColor(new Color(240, 190, 90));
                graphics2D.fillRoundRect(slotX, slotY, gamePanel.tileSize, gamePanel.tileSize, 10, 10);
            }
            graphics2D.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);

            if (entity == gamePanel.player && entity.inventory.get(i).amount > 1) {
                graphics2D.setFont(Monica.deriveFont(Font.PLAIN, 20F));
                int amountX;
                int amountY;
                String str = "" + entity.inventory.get(i).amount;
                amountX = getX_Value(str, slotX + 44);
                amountY = slotY + gamePanel.tileSize;
                graphics2D.setColor(new Color(12, 8, 1));
                graphics2D.drawString(str, amountX, amountY);
                graphics2D.setColor(new Color(229, 152, 9));
                graphics2D.drawString(str, amountX - 2, amountY - 2);

            }
            slotX += gamePanel.tileSize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += gamePanel.tileSize;
            }
        }

        if (cursor) {
            int curX = slotXStart + (gamePanel.tileSize * slotCol);
            int curY = slotYStart + (gamePanel.tileSize * slotRow);
            int curWidth = gamePanel.tileSize;
            int curHeight = gamePanel.tileSize;
            graphics2D.setColor(new Color(229, 152, 9));
            graphics2D.setStroke(new BasicStroke(3));
            graphics2D.drawRoundRect(curX, curY, curWidth, curHeight, 10, 10);

            int descriptionFrameY = y + height;
            int descriptionFrameHeight = gamePanel.tileSize * 3;


            int txtX = x + 20;
            int txtY = descriptionFrameY + gamePanel.tileSize;
            graphics2D.setFont(Monica.deriveFont(Font.PLAIN, 25F));
            int itemIndex = getItemIndex(slotCol, slotRow);
            if (itemIndex < entity.inventory.size()) {
                drawSubWindow(x, descriptionFrameY, width, descriptionFrameHeight);

                for (String line : entity.inventory.get(itemIndex).description.split("\n")) {
                    graphics2D.drawString(line, txtX, txtY);
                    txtY += 32;
                }
            }
        }
    }

    public int getItemIndex(int slotCol, int slotRow) {
        return slotCol + (slotRow * 5);
    }

    private void drawCharacterScreen() {
        final int frameX = gamePanel.tileSize * 2;
        final int frameY = gamePanel.tileSize;
        final int frameWidth = gamePanel.tileSize * 5;
        final int frameHeight = gamePanel.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);


        graphics2D.setColor(new Color(229, 152, 9));
        graphics2D.setFont(Monica.deriveFont(Font.PLAIN, 25F));

        int textX = frameX + 20;
        int textY = frameY + gamePanel.tileSize;
        final int lineHeight = (int) (gamePanel.tileSize * 0.65);

        graphics2D.drawString("Level", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Life", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Mana", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Strength ", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Attack", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Defence", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Exp", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Next Level", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Coin", textX, textY);
        textY += lineHeight * 2;
        graphics2D.drawString("Weapon", textX, textY);
        textY += lineHeight + 5;
        graphics2D.drawString("Shield", textX, textY);


        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + gamePanel.tileSize;
        String value;

        value = String.valueOf(gamePanel.player.level);
        textX = getX_Value(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = gamePanel.player.life + "/" + gamePanel.player.maxLife;
        textX = getX_Value(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = gamePanel.player.mana + "/" + gamePanel.player.maxMana;
        textX = getX_Value(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.strength);
        textX = getX_Value(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.dexterity);
        textX = getX_Value(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.attack);
        textX = getX_Value(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.defence);
        textX = getX_Value(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.exp);
        textX = getX_Value(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.nextLevelExp);
        textX = getX_Value(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.coin);
        textX = getX_Value(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight + 7;

        BufferedImage img = UtilityTool.scaleImage(gamePanel.player.currentWeapon.down1, 30, 30);
        BufferedImage img2 = UtilityTool.scaleImage(gamePanel.player.currentShield.down1, 30, 30);

        graphics2D.drawImage(img, tailX - 32, textY, null);
        textY += lineHeight + 7;
        graphics2D.drawImage(img2, tailX - 32, textY, null);
    }

    private void drawTitleScreen() {
        if (titleScreenBehavior == 0) {

            graphics2D.setFont(Pixel.deriveFont(Font.BOLD, 37F));
            String text = "My hero adventure";
            int x = getX_Text(text.toUpperCase());
            int y = (int) (gamePanel.tileSize * 2.5);
            graphics2D.setColor(new Color(0, 0, 7));
            graphics2D.drawString(text.toUpperCase(), x + 4, y + 4);
            graphics2D.setColor(new Color(229, 152, 9));
            graphics2D.drawString(text.toUpperCase(), x, y);

            x = gamePanel.screenWidth / 2;
            y += (int) (gamePanel.tileSize * 1.5);
            graphics2D.drawImage(gamePanel.player.down1, x - gamePanel.tileSize, y, gamePanel.tileSize * 2, gamePanel.tileSize * 2, null);
            graphics2D.setFont(Pixel.deriveFont(Font.PLAIN, 25F));

            text = "New game";
            x = getX_Text(text);
            y += gamePanel.tileSize * 4;
            graphics2D.drawString(text.toUpperCase(), x, y);
            if (commandNum == 0) {
                graphics2D.drawString(">", x - gamePanel.tileSize, y);
            }

            text = "Load game";
            x = getX_Text(text);
            y += gamePanel.tileSize;
            graphics2D.drawString(text.toUpperCase(), x, y);
            if (commandNum == 1) {
                graphics2D.drawString(">", x - gamePanel.tileSize, y);
            }


            text = "Quit";
            x = getX_Text(text);
            y += gamePanel.tileSize;
            graphics2D.drawString(text.toUpperCase(), x, y);
            if (commandNum == 2) {
                graphics2D.drawString(">", x - gamePanel.tileSize, y);
            }

        } else if (titleScreenBehavior == 1) {
            graphics2D.setColor(new Color(229, 152, 9));
            graphics2D.setFont(Pixel.deriveFont(Font.BOLD, 30F));
            String text = "Select your class:";
            int x = getX_Text(text.toUpperCase());
            int y = (int) (gamePanel.tileSize * 2.5);
            graphics2D.drawString(text.toUpperCase(), x, y);

            graphics2D.setFont(Pixel.deriveFont(Font.PLAIN, 25F));

            text = "Fighter";
            x = getX_Text(text);
            y += (gamePanel.tileSize * 2);
            graphics2D.drawString(text.toUpperCase(), x, y);
            if (commandNum == 0) {
                graphics2D.drawString(">", x - gamePanel.tileSize, y);
            }

            text = "Thief";
            x = getX_Text(text);
            y += (gamePanel.tileSize);
            graphics2D.drawString(text.toUpperCase(), x, y);
            if (commandNum == 1) {
                graphics2D.drawString(">", x - gamePanel.tileSize, y);
            }

            text = "Sorcerer";
            x = getX_Text(text);
            y += (gamePanel.tileSize);
            graphics2D.drawString(text.toUpperCase(), x, y);
            if (commandNum == 2) {
                graphics2D.drawString(">", x - gamePanel.tileSize, y);
            }
            text = "Back";
            x = getX_Text(text);
            y += (gamePanel.tileSize * 3);
            graphics2D.drawString(text.toUpperCase(), x, y);
            if (commandNum == 3) {
                graphics2D.drawString(">", x - gamePanel.tileSize, y);

            }
        }
    }

    public void drawPlayerLife() {

        int x = gamePanel.tileSize / 4;
        int y = gamePanel.tileSize / 4;

        for (int i = 0; i < gamePanel.player.maxLife / 2; i++) {
            graphics2D.drawImage(heart_e, x, y, null);
            x += gamePanel.tileSize;
        }
        x = gamePanel.tileSize / 4;

        for (int i = 0; i < gamePanel.player.life; i++) {
            graphics2D.drawImage(heart_h, x, y, null);
            i++;
            if (i < gamePanel.player.life) {
                graphics2D.drawImage(heart_f, x, y, null);
            }
            x += gamePanel.tileSize;
        }
        x = gamePanel.tileSize / 4;
        y += gamePanel.tileSize + 5;
        for (int i = 0; i < gamePanel.player.maxMana; i++) {
            graphics2D.drawImage(crystal_e, x, y, null);
            x += (int) (gamePanel.tileSize / 1.3);
        }
        x = gamePanel.tileSize / 4;

        for (int i = 0; i < gamePanel.player.mana; i++) {
            graphics2D.drawImage(crystal_f, x, y, null);
            x += (int) (gamePanel.tileSize / 1.3);
        }
    }

    private void drawMessage() {
        int msgX = gamePanel.tileSize;
        int msgY = gamePanel.tileSize * 3;


        graphics2D.setFont(Monica.deriveFont(Font.PLAIN, 21F));

        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {

                graphics2D.setColor(new Color(12, 5, 1, 224));
                graphics2D.drawString(message.get(i), msgX + 2, msgY + 2);
                graphics2D.setColor(new Color(213, 195, 194));
                graphics2D.drawString(message.get(i), msgX, msgY);


                int count = counter.get(i) + 1;
                counter.set(i, count);
                msgY += 30;
                if (counter.get(i) > 180) {
                    message.remove(i);
                    counter.remove(i);
                }
            }
        }
    }

    private void drawPauseScreenYellow() {
        graphics2D.setColor(new Color(229, 152, 9));
        graphics2D.setFont(Pixel.deriveFont(Font.PLAIN, 50F));
        String txt = "Paused!";
        int x = getX_Text(txt);
        int y = gamePanel.screenHeight / 2;
        graphics2D.drawString(txt.toUpperCase(), x, y);

    }

    private void drawPauseScreenShadow() {
        graphics2D.setColor(new Color(16, 7, 1));
        graphics2D.setFont(Pixel.deriveFont(Font.PLAIN, 50F));
        String txt = "Paused!";
        int x = getX_Text(txt) + 4;
        int y = gamePanel.screenHeight / 2 + 4;
        graphics2D.drawString(txt.toUpperCase(), x, y);

    }

    private void drawDialogScreen() {
        int x = gamePanel.tileSize * 3;
        int y = (int) (gamePanel.tileSize * 1.5);
        int width = gamePanel.screenWidth - (gamePanel.tileSize * 6);
        int height = gamePanel.tileSize * 4;
        drawSubWindow(x, y, width, height);
        graphics2D.setFont(Monica.deriveFont(Font.PLAIN, 21F));

        if (npc.dialogues[npc.dialogSet][npc.dialogCount] != null) {
            dialogue = npc.dialogues[npc.dialogSet][npc.dialogCount];
            if (gamePanel.keyHandler.enterPressed) {
                if (gamePanel.gameBehavior == GamePanel.dialogBehavior) {
                    npc.dialogCount++;
                    gamePanel.keyHandler.enterPressed = false;
                }
            }
        } else {
            npc.dialogCount = 0;
            if (gamePanel.gameBehavior == GamePanel.dialogBehavior) {
                gamePanel.gameBehavior = GamePanel.playBehavior;
            }
        }

        for (String line : dialogue.split("\n")) {
            drawDialogText(line, x, y);
            y += 30;
        }
    }

    private void drawDialogText(String str, int x, int y) {
        x += gamePanel.tileSize;
        y += gamePanel.tileSize;
        graphics2D.drawString(str, x, y);
    }

    private void drawSubWindow(int x, int y, int width, int height) {
        graphics2D.setColor(new Color(1, 4, 14, 220));
        graphics2D.fillRoundRect(x, y, width, height, 35, 35);

        graphics2D.setColor((new Color(229, 152, 9)));
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }


    private int getX_Text(String txt) {
        int txtLength = (int) graphics2D.getFontMetrics().getStringBounds(txt, graphics2D).getWidth();
        return gamePanel.screenWidth / 2 - txtLength / 2;
    }

    private int getX_Value(String txt, int tailX) {
        int txtLength = (int) graphics2D.getFontMetrics().getStringBounds(txt, graphics2D).getWidth();
        return tailX - txtLength;
    }
}
