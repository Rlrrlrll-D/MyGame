package main;

import entity.Entity;
import objects.Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    GamePanel gamePanel;
    BufferedImage heart_f, heart_h, heart_e;

    public boolean msgOn;
    public int commandNum = 0;
    public int titleScreenBehavior = 0;
    Graphics2D graphics2D;
    public String msg = "";
    public int counter;
    Font Purisa, Pixel, Monica;

    public boolean finished;
    public String dialogue;


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


    }

    public void showMsg(String text) {
        msg = text;
        msgOn = true;
    }

    public void msgDelay(int delay) {
        counter++;

        if (counter >= delay) {
            counter = 0;
            msgOn = false;


        }
    }


    public void drawing(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;

        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (gamePanel.gameBehavior == gamePanel.titleBehavior) {
            drawTitleScreen();
        }

        if (gamePanel.gameBehavior == gamePanel.playBehavior) {
            drawPlayerLife();
        }
        if (gamePanel.gameBehavior == gamePanel.pauseBehavior) {
            drawPlayerLife();
            drawPauseScreenShadow();
            drawPauseScreenYellow();

        }
        if (gamePanel.gameBehavior == gamePanel.dialogBehavior) {
            drawPlayerLife();
            drawDialogScreen();
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
            int y = (gamePanel.tileSize * 4);
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

    public void drawPauseScreenYellow() {
        graphics2D.setColor(new Color(229, 152, 9));
        graphics2D.setFont(Pixel.deriveFont(Font.PLAIN, 50F));
        String txt = "Paused!";
        int x = getX_Text(txt);
        int y = gamePanel.screenHeight / 2;
        graphics2D.drawString(txt.toUpperCase(), x, y);

    }

    public void drawPauseScreenShadow() {
        graphics2D.setColor(new Color(12, 6, 2, 100));
        graphics2D.setFont(Pixel.deriveFont(Font.PLAIN, 50F));
        String txt = "Paused!";
        int x = getX_Text(txt) + 2;
        int y = gamePanel.screenHeight / 2 + 2;
        graphics2D.drawString(txt.toUpperCase(), x, y);

    }

    public void drawDialogScreen() {
        int x = gamePanel.tileSize * 2;
        int y = (int) (gamePanel.tileSize*1.5);
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
}
