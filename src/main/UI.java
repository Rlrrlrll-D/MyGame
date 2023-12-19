package main;

import entity.Entity;
import objects.Heart;
import objects.ManaCrystal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {

    GamePanel gamePanel;
    public boolean finished;
    public String dialogue;
    BufferedImage heart_f, heart_h, heart_e, crystal_f, crystal_e;

    public boolean msgOn;

    public int count;
    Graphics2D graphics2D;
    Font Purisa, Pixel, Monica;
    ArrayList<String> message = new ArrayList<>();
    public int slotCol = 0;
    public int commandNum = 0;
    public int titleScreenBehavior = 0;
    public int slotRow = 0;
    int subBehavior;
    ArrayList<Integer> counter = new ArrayList<>();


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


    }

    public void addMsg(String text) {
//        msg = text;
//        msgOn = true;

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

    public void drawing(Graphics2D graphics2D) {
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
            drawPlayerLife();
            drawDialogScreen();
        }
        if (gamePanel.gameBehavior == GamePanel.characterBehavior) {
            drawCharacterScreen();
            drawInventory();
        }
        if (gamePanel.gameBehavior == GamePanel.optionsBehavior) {
            drawOptionScreen();

        }


//        if (finished){
//
//            graphics2D.setFont(Unispace_Bold);
//            graphics2D.setColor(new Color(5, 18, 91));
//
//            String txt;
//            int txtLength;
//            int x;
//            int y;
//            txt = "You found the treasure!";
//            txtLength = (int)graphics2D.getFontMetrics().getStringBounds(txt,graphics2D).getWidth();
//            x = gamePanel.screenWidth/2 - txtLength/2;
//            y = gamePanel.screenHeight/2-(gamePanel.tileSize*3);
//            graphics2D.drawString(txt,x,y);
//
//            txt = "You Time is: "+ decimalFormat.format(playTime)+"!";
//            txtLength = (int)graphics2D.getFontMetrics().getStringBounds(txt,graphics2D).getWidth();
//            x = gamePanel.screenWidth/2 - txtLength/2;
//            y = gamePanel.screenHeight/2-(gamePanel.tileSize);
//            graphics2D.drawString(txt,x,y);
//
//            graphics2D.setFont(Unispace_Bold2);
//            graphics2D.setColor(new Color(229, 152, 9));
//            txt = "CONGRATULATIONS!";
//            txtLength = (int)graphics2D.getFontMetrics().getStringBounds(txt,graphics2D).getWidth();
//            x = gamePanel.screenWidth/2 - txtLength/2;
//            y = gamePanel.screenHeight/2-(gamePanel.tileSize*2);
//            graphics2D.drawString(txt,x,y);
//
//            gamePanel.gameThread = null;
//
//        }else {
//            graphics2D.setFont(Unispace_Bold);
//            graphics2D.setColor(new Color(6, 18, 94));
//            graphics2D.drawImage(key, gamePanel.tileSize / 2, gamePanel.tileSize / 2, gamePanel.tileSize / 2 + 10, gamePanel.tileSize / 2 + 10, null);
//            graphics2D.drawString("x " + gamePanel.res.player.keys, 57, gamePanel.tileSize);
//
//            playTime+=(double) 1/60;
//            graphics2D.drawString("Time: "+ decimalFormat.format(playTime), gamePanel.screenWidth- gamePanel.tileSize*3, gamePanel.tileSize);
//            if (msgOn) {
//                graphics2D.drawString(msg, (gamePanel.tileSize / 2) * 5, gamePanel.tileSize);
//                msgDelay(180);
//            }
//        }
    }

    private void drawOptionScreen() {
        graphics2D.setColor(new Color(229, 152, 9));
        graphics2D.setFont(Monica.deriveFont(Font.PLAIN, 25F));

        int x = gamePanel.tileSize * 6;
        int y = gamePanel.tileSize;
        int width = gamePanel.tileSize * 8;
        int height = gamePanel.tileSize * 10;
        drawSubWindow(x, y, width, height);

        switch (subBehavior) {
            case 0:
                option_top(x, y);
                break;
            case 1:
                break;
            case 2:
                break;
        }

    }

    public void option_top(int x, int y) {
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

        txt = "Music";
        textY += gamePanel.tileSize;
        graphics2D.drawString(txt, textX, textY);

        txt = "SE";
        textY += gamePanel.tileSize;
        graphics2D.drawString(txt, textX, textY);

        txt = "Control";
        textY += gamePanel.tileSize;
        graphics2D.drawString(txt, textX, textY);

        txt = "End Game";
        textY += gamePanel.tileSize;
        graphics2D.drawString(txt, textX, textY);

        txt = "Back";
        textX = getX_Text(txt);
        textY += gamePanel.tileSize * 2;
        graphics2D.drawString(txt, textX, textY);

    }

    private void drawInventory() {
        int x = gamePanel.tileSize * 12;
        int y = gamePanel.tileSize;
        int height = gamePanel.tileSize * 5;
        int width = gamePanel.tileSize * 6;

        drawSubWindow(x, y, width, height);
        final int slotXstart = x + gamePanel.tileSize / 2;
        final int slotYstart = y + gamePanel.tileSize / 2;
        int slotX = slotXstart;
        int slotY = slotYstart;

        for (int i = 0; i < gamePanel.player.inventory.size(); i++) {

            if (gamePanel.player.inventory.get(i) == gamePanel.player.currentWeapon || gamePanel.player.inventory.get(i) == gamePanel.player.currentShield) {
                graphics2D.setColor(new Color(240, 190, 90));
                graphics2D.fillRoundRect(slotX, slotY, gamePanel.tileSize, gamePanel.tileSize, 10, 10);
            }
            graphics2D.drawImage(gamePanel.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += gamePanel.tileSize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += gamePanel.tileSize;
            }

        }

        int curX = slotXstart + (gamePanel.tileSize * slotCol);
        int curY = slotYstart + (gamePanel.tileSize * slotRow);
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
        int itemIndex = getItemIndex();
        if (itemIndex < gamePanel.player.inventory.size()) {
            drawSubWindow(x, descriptionFrameY, width, descriptionFrameHeight);

            for (String line : gamePanel.player.inventory.get(itemIndex).description.split("\n")) {
                graphics2D.drawString(line, txtX, txtY);
                txtY += 32;
            }
        }
    }

    public int getItemIndex() {
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
        final int lineHeight = 27;

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
        textY += lineHeight;

        BufferedImage img = UtilityTool.scaleImage(gamePanel.player.currentWeapon.down1, 30, 30);
        BufferedImage img2 = UtilityTool.scaleImage(gamePanel.player.currentShield.down1, 30, 30);

        graphics2D.drawImage(img, tailX - 32, textY, null);
        textY += lineHeight + 5;
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

    public void drawMessage() {
        int msgX = gamePanel.tileSize;
        int msgY = gamePanel.tileSize * 3;


        graphics2D.setFont(Monica.deriveFont(Font.PLAIN, 30F));

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

    public void drawPauseScreenYellow() {
        graphics2D.setColor(new Color(229, 152, 9));
        graphics2D.setFont(Pixel.deriveFont(Font.PLAIN, 50F));
        String txt = "Paused!";
        int x = getX_Text(txt);
        int y = gamePanel.screenHeight / 2;
        graphics2D.drawString(txt.toUpperCase(), x, y);

    }

    public void drawPauseScreenShadow() {
        graphics2D.setColor(new Color(16, 7, 1));
        graphics2D.setFont(Pixel.deriveFont(Font.PLAIN, 50F));
        String txt = "Paused!";
        int x = getX_Text(txt) + 4;
        int y = gamePanel.screenHeight / 2 + 4;
        graphics2D.drawString(txt.toUpperCase(), x, y);

    }

    public void drawDialogScreen() {
        int x = gamePanel.tileSize * 2;
        int y = (int) (gamePanel.tileSize * 1.5);
        int width = gamePanel.screenWidth - (gamePanel.tileSize * 4);
        int height = gamePanel.tileSize * 4;
        drawSubWindow(x, y, width, height);
        graphics2D.setFont(Monica.deriveFont(Font.PLAIN, 21F));
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

    public void drawSubWindow(int x, int y, int width, int height) {
        graphics2D.setColor(new Color(1, 4, 14, 220));
        graphics2D.fillRoundRect(x, y, width, height, 35, 35);

        graphics2D.setColor((new Color(229, 152, 9)));
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }


    public int getX_Text(String txt) {
        int txtLength = (int) graphics2D.getFontMetrics().getStringBounds(txt, graphics2D).getWidth();
        return gamePanel.screenWidth / 2 - txtLength / 2;
    }

    public int getX_Value(String txt, int tailX) {
        int txtLength = (int) graphics2D.getFontMetrics().getStringBounds(txt, graphics2D).getWidth();
        return tailX - txtLength;
    }
}
