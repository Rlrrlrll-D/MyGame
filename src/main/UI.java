package main;

import java.awt.*;
import java.text.DecimalFormat;

public class UI {

    GamePanel gamePanel;
    //BufferedImage key;
    public boolean msgOn;
    Font Unispace_Bold, Unispace_Bold2;
    Graphics2D graphics2D;
    public String msg = "";
    public int counter;

    public boolean finished;

    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.0");

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        Unispace_Bold = new Font("Unispace-Bold", Font.PLAIN, 17);
        Unispace_Bold2 = new Font("Unispace-Bold", Font.BOLD, 50);
        // Key key = new Key(gamePanel);
        //this.key = key.image;
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
        graphics2D.setFont(Unispace_Bold2);
        graphics2D.setColor(new Color(229, 152, 9));

        if (gamePanel.gameBehavior == gamePanel.playBehavior) {

        }
        if (gamePanel.gameBehavior == gamePanel.pauseBehavior) {
            drawPauseScreen();
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
//            graphics2D.drawString("x " + gamePanel.player.keys, 57, gamePanel.tileSize);
//
//            playTime+=(double) 1/60;
//            graphics2D.drawString("Time: "+ decimalFormat.format(playTime), gamePanel.screenWidth- gamePanel.tileSize*3, gamePanel.tileSize);
//            if (msgOn) {
//                graphics2D.drawString(msg, (gamePanel.tileSize / 2) * 5, gamePanel.tileSize);
//                msgDelay(180);
//            }
//        }

    }

    public void drawPauseScreen() {
        String txt = "PAUSED!";

        int x = getX_Text(txt);
        int y = gamePanel.screenHeight / 2;
        graphics2D.drawString(txt, x, y);

    }

    public int getX_Text(String txt) {
        int txtLength = (int) graphics2D.getFontMetrics().getStringBounds(txt, graphics2D).getWidth();
        return gamePanel.screenWidth / 2 - txtLength / 2;
    }
}
