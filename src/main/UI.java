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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class UI {
    private static final Color DARK_COLOR = new Color(0, 0, 7);
    private static final Color LIGHT_COLOR = new Color(229, 152, 9);
    private static final int X_OFFSET = 2;
    private static final int Y_OFFSET = 2;
    public String dialogue;
    public int count;
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    public int commandNum = 0;
    public int charIndex;
    public Font Purisa, Pixel, Monica, Abaddon1, Abaddon2, Dungeon;
    public Entity npc;
    GamePanel gamePanel;
    BufferedImage heart_f, heart_h, heart_e, crystal_f, crystal_e, coin;
    Graphics2D graphics2D;
    ArrayList<String> message = new ArrayList<>();
    int subBehavior;
    int temp;
    ArrayList<Integer> counter = new ArrayList<>();
    StringBuilder combinedText = new StringBuilder();


    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        try {
            InputStream inputStream = getClass().getResourceAsStream("/res/fonts/Purisa Bold.ttf");
            assert inputStream != null;
            Purisa = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            inputStream = getClass().getResourceAsStream("/res/fonts/Pixel Regular.otf");
            assert inputStream != null;
            Pixel = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            inputStream = getClass().getResourceAsStream("/res/fonts/MaruMonica.ttf");
            assert inputStream != null;
            Monica = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            inputStream = getClass().getResourceAsStream("/res/fonts/Abaddon_Bold.ttf");
            assert inputStream != null;
            Abaddon1 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            inputStream = getClass().getResourceAsStream("/res/fonts/Abaddon_Light.ttf");
            assert inputStream != null;
            Abaddon2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            inputStream = getClass().getResourceAsStream("/res/fonts/DungeonFont.ttf");
            assert inputStream != null;
            Dungeon = Font.createFont(Font.TRUETYPE_FONT, inputStream);

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

    public void drawing(Graphics2D graphics2D) throws IOException {
        this.graphics2D = graphics2D;

        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (gamePanel.gameBehavior == GamePanel.titleBehavior) {
            drawTitleScreen();
        }

        if (gamePanel.gameBehavior == GamePanel.playBehavior) {
            drawPlayerStats();
            drawMonsterLife();
            drawMessage();
        }
        if (gamePanel.gameBehavior == GamePanel.pauseBehavior) {
            drawPlayerStats();
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

    private void drawMonsterLife() {
        Entity[] monsters = gamePanel.mon[gamePanel.currentMap];
        Color color1 = new Color(0x090202);
        Color color2 = new Color(0x861515);
        Color color3 = new Color(229, 152, 9);
        Color color4 = new Color(187, 53, 29);

        for (Entity monster : monsters) {
            if (monster != null && monster.inFocus()) {
                double oneScale = (double) gamePanel.tileSize / monster.maxLife;
                double hpBarValue = oneScale * monster.life;
                int x = monster.getScrX();
                int y = monster.getScrY() - 15;

                if (monster.hpBarOn && !monster.boss) {
                    graphics2D.setColor(color1);
                    graphics2D.fillRect(x - 1, y, gamePanel.tileSize + 2, 12);

                    graphics2D.setColor(color2);
                    graphics2D.fillRect(x, y + 1, (int) hpBarValue, 10);
                    monster.hpBarCounter++;

                    if (monster.hpBarCounter > 600) {
                        monster.hpBarCounter = 0;
                        monster.hpBarOn = false;
                    }
                } else if (monster.boss) {
                    oneScale = (double) gamePanel.tileSize * 8 / monster.maxLife;
                    hpBarValue = oneScale * monster.life;
                    x = gamePanel.screenWidth / 2 - gamePanel.tileSize * 4;
                    y = (int) (gamePanel.tileSize / 1.7);

                    graphics2D.setColor(color2);
                    graphics2D.fillRect(x, y, (int) hpBarValue, 20);

                    graphics2D.setColor(color4);
                    graphics2D.drawRect(x - 1, y - 1, gamePanel.tileSize * 8 + 2, 22);

                    graphics2D.setFont(Monica.deriveFont(Font.BOLD, 20F));
                    graphics2D.setColor(color3);
                    graphics2D.drawString(monster.name, x + gamePanel.tileSize * 4 - monster.name.length() * 5, y - 5);
                }
            }
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
            case 0 -> trade_select();
            case 1 -> trade_buy();
            case 2 -> trade_sell();
        }
        gamePanel.keyHandler.enterPressed = false;
    }

    private void trade_select() {
        npc.dialogSet = 0;
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
                npc.startDialog(npc, 1);
            }
        }
    }

    private void trade_buy() {
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
                    npc.startDialog(npc, 2);
                } else {
                    if (gamePanel.player.canObtainItem(npc.inventory.get(itemIndex))) {
                        gamePanel.player.coin -= npc.inventory.get(itemIndex).price;
                    } else {
                        subBehavior = 0;
                        npc.startDialog(npc, 3);
                    }
                }
            }
        }
    }

    private void trade_sell() {
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
                    npc.startDialog(npc, 4);
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
            gamePanel.changeArea();
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
        String txt = "Control";
        int textX = getX_Text(txt);
        int textY = y + gamePanel.tileSize;
        graphics2D.drawString(txt, textX, textY);

        Map<String, String> controls = new LinkedHashMap<>() {{
            put("Move", "WASD");
            put("Confirm/Attack", "Enter");
            put("Shoot/Cast", "F");
            put("Character Screen", "C");
            put("Pause", "P");
            put("Mute", "M");
            put("Options", "Esc");
            put("Back", "");
        }};

        textX = x + gamePanel.tileSize;
        textY += gamePanel.tileSize;
        int keyTextX = x + gamePanel.tileSize * 6;

        for (Map.Entry<String, String> control : controls.entrySet()) {
            graphics2D.drawString(control.getKey(), textX, textY);
            graphics2D.drawString(control.getValue(), keyTextX, textY);
            if (commandNum == 0 && control.getKey().equals("Back")) {
                graphics2D.drawString(">", textX - 25, textY);
                if (gamePanel.keyHandler.enterPressed) {
                    subBehavior = 0;
                    commandNum = 3;
                }
            }
            textY += (int) (gamePanel.tileSize * 0.85);
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
        int x = entity instanceof Player ? gamePanel.tileSize * 12 : gamePanel.tileSize * 2;
        int y = gamePanel.tileSize;
        int height = gamePanel.tileSize * 5;
        int width = gamePanel.tileSize * 6;
        int slotCol = entity instanceof Player ? playerSlotCol : npcSlotCol;
        int slotRow = entity instanceof Player ? playerSlotRow : npcSlotRow;

        drawSubWindow(x, y, width, height);
        final int slotXStart = x + gamePanel.tileSize / 2;
        final int slotYStart = y + gamePanel.tileSize / 2;
        int slotX = slotXStart;
        int slotY = slotYStart;

        for (Entity item : entity.inventory) {
            if (item == entity.currentWeapon || item == entity.currentShield || item == entity.currentLight) {
                graphics2D.setColor(new Color(240, 190, 90));
                graphics2D.fillRoundRect(slotX, slotY, gamePanel.tileSize, gamePanel.tileSize, 10, 10);
            }
            graphics2D.drawImage(item.down1, slotX, slotY, null);

            if (entity == gamePanel.player && item.amount > 1) {
                graphics2D.setFont(Monica.deriveFont(Font.PLAIN, 20F));
                int amountX = getX_Value("" + item.amount, slotX + 44);
                int amountY = slotY + gamePanel.tileSize;
                graphics2D.setColor(new Color(12, 8, 1));
                graphics2D.drawString("" + item.amount, amountX, amountY);
                graphics2D.setColor(new Color(229, 152, 9));
                graphics2D.drawString("" + item.amount, amountX - 2, amountY - 2);
            }
            slotX += gamePanel.tileSize;
            if (entity.inventory.indexOf(item) == 4 || entity.inventory.indexOf(item) == 9 || entity.inventory.indexOf(item) == 14) {
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
        graphics2D.setFont(Monica.deriveFont(Font.BOLD, 57F));
        String text = "My hero adventure";
        int x = getX_Text(text.toUpperCase());
        int y = (int) (gamePanel.tileSize * 2.5);
        graphics2D.setColor(new Color(0, 0, 7));
        graphics2D.drawString(text.toUpperCase(), x + 4, y + 4);
        graphics2D.setColor(new Color(229, 152, 9));
        graphics2D.drawString(text.toUpperCase(), x, y);
        drawTitleOptions(new String[]{"New game", "Load game", "Quit"}, y);
    }

    private void drawTitleOptions(String[] options, int y) {
        graphics2D.setFont(Monica.deriveFont(Font.BOLD, 40F));
        int i = 0;
        for (String text : options) {
            int x = getX_Text(text);
            y += gamePanel.tileSize * (i == 0 ? 4 : 1);
            graphics2D.setColor(DARK_COLOR);
            graphics2D.drawString(text.toUpperCase(), x + X_OFFSET, y + Y_OFFSET);
            graphics2D.setColor(LIGHT_COLOR);
            graphics2D.drawString(text.toUpperCase(), x, y);
            if (commandNum == i) {
                graphics2D.setColor(DARK_COLOR);
                graphics2D.drawString(">", x - gamePanel.tileSize + X_OFFSET, y + Y_OFFSET);
                graphics2D.setColor(LIGHT_COLOR);
                graphics2D.drawString(">", x - gamePanel.tileSize, y);
            }
            i++;
        }
    }

    public void drawPlayerStats() {

        var x = gamePanel.tileSize / 4;
        var y = gamePanel.tileSize / 4;
        var step = 1;
        for (int i = 0; i < gamePanel.player.maxLife / 2; i++) {

            if (i % 5 == 0 && i != 0) {
                x = gamePanel.tileSize / 4;
                y += gamePanel.tileSize - 8;
                step++;
            }
            graphics2D.drawImage(heart_e, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            x += gamePanel.tileSize - 8;
        }
        x = gamePanel.tileSize / 4;
        y = gamePanel.tileSize / 4;

        for (int i = 0; i < gamePanel.player.life; i++) {
            if (i % 5 == 0 && i != 0) {
                x = gamePanel.tileSize / 4;
                y += gamePanel.tileSize - 8;
            }
            graphics2D.drawImage(heart_h, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            i++;
            if (i < gamePanel.player.life) {
                graphics2D.drawImage(heart_f, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            }
            x += gamePanel.tileSize - 8;
        }
        x = 0;
        y = (int) ((gamePanel.tileSize - 8) * step + gamePanel.tileSize / 2.6);

        for (int i = 0; i < gamePanel.player.maxMana; i++) {
            if (i % 5 == 0 && i != 0) {
                x = 0;
                y += gamePanel.tileSize;
            }
            graphics2D.drawImage(crystal_e, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            x += (int) (gamePanel.tileSize - gamePanel.tileSize / 2.2);
        }
        x = 0;
        y = (int) ((gamePanel.tileSize - 8) * step + gamePanel.tileSize / 2.6);
        for (int i = 0; i < gamePanel.player.mana; i++) {
            if (i % 5 == 0 && i != 0) {
                x = 0;
                y += gamePanel.tileSize;
            }
            graphics2D.drawImage(crystal_f, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            x += (int) (gamePanel.tileSize - gamePanel.tileSize / 2.2);
        }
//        y = (int) (gamePanel.tileSize * .4) + gamePanel.tileSize / 4;
//        x = gamePanel.screenWidth - gamePanel.tileSize * 2;
        //drawGodMode(y, x);
    }

    private void drawGodMode(int y, int x) {
        String txt = "God mode";
        graphics2D.setFont(Pixel.deriveFont(Font.BOLD, 10F));
        graphics2D.setColor(new Color(12, 5, 1, 225));
        graphics2D.drawString(txt.toUpperCase(), x + 1, y + 1);
        graphics2D.setFont(Pixel.deriveFont(Font.BOLD, 10F));
        graphics2D.setColor(gamePanel.keyHandler.modeOfGod ? new Color(84, 161, 18) : new Color(241, 42, 23, 255));
        graphics2D.drawString(txt.toUpperCase(), x, y);
    }

    private void drawMessage() {
        int msgX = gamePanel.tileSize / 4;
        int msgYStart = gamePanel.tileSize * 6;
        int lineHeight = 11;

        graphics2D.setFont(Pixel.deriveFont(Font.BOLD, 8F));

        Iterator<String> messageIterator = message.iterator();
        Iterator<Integer> counterIterator = counter.iterator();

        int i = 0;
        while (messageIterator.hasNext() && counterIterator.hasNext()) {
            String msg = messageIterator.next().toUpperCase();
            int count = counterIterator.next();

            int msgY = msgYStart + i * lineHeight;

            graphics2D.setColor(new Color(12, 5, 1));
            graphics2D.drawString(msg, msgX + 1, msgY + 1);
            graphics2D.setColor(new Color(229, 152, 9));
            graphics2D.drawString(msg, msgX, msgY);

            count++;
            counter.set(i, count);

            if (count > 180) {
                messageIterator.remove();
                counterIterator.remove();
                continue;
            }
            i++;
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

    protected void drawDialogScreen() {
        int x = gamePanel.tileSize * 3;
        int y = (int) (gamePanel.tileSize * 1.5);
        int width = gamePanel.screenWidth - (gamePanel.tileSize * 6);
        int height = gamePanel.tileSize * 4;
        drawSubWindow(x, y, width, height);
        graphics2D.setFont(Monica.deriveFont(Font.PLAIN, 21F));

        if (npc.dialogues[npc.dialogSet][npc.dialogCount] != null) {
            dialogue = npc.dialogues[npc.dialogSet][npc.dialogCount];
            //soundTaping();
            if (gamePanel.keyHandler.enterPressed) {
                gamePanel.playSFX(12);
                charIndex = 0;
                combinedText = new StringBuilder();
                if (gamePanel.gameBehavior == GamePanel.dialogBehavior || gamePanel.gameBehavior == GamePanel.cutSceneBehavior) {
                    npc.dialogCount++;
                    gamePanel.keyHandler.enterPressed = false;
                }
            }
        } else {
            npc.dialogCount = 0;
            if (gamePanel.gameBehavior == GamePanel.dialogBehavior) {
                gamePanel.gameBehavior = GamePanel.playBehavior;
            }
            if (gamePanel.gameBehavior == GamePanel.cutSceneBehavior) {
                gamePanel.cutSceneManager.scenePhase++;
            }
        }

        for (String line : dialogue.split("\n")) {
            drawDialogText(line, x, y);
            y += 30;
        }
    }

    private void soundTaping() {
        char[] characters = npc.dialogues[npc.dialogSet][npc.dialogCount].toCharArray();
        if (charIndex < characters.length) {
            gamePanel.playSFX(20);
            combinedText.append(characters[charIndex]);
            dialogue = combinedText.toString();
            charIndex++;
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
