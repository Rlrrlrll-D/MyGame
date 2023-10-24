package main;

import objects.Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gamePanel;
    Font Unispace_Bold, Unispace_Bold2;
    BufferedImage key;
    public boolean msgOn;
    public String msg = "";
    public int counter;

    public boolean finished;
    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        Unispace_Bold = new Font("Unispace-Bold", Font.PLAIN, 17);
        Unispace_Bold2 = new Font("Unispace-Bold", Font.BOLD, 30);
        Key key = new Key();
        this.key = key.image;
    }

    public void showMsg(String text){
        msg = text;
        msgOn = true;
    }
    public void msgDelay(int delay) {
        counter++;

        if (counter >=delay) {
            counter = 0;
            msgOn= false;


        }
    }



    public void drawing(Graphics2D graphics2D) {
        if (finished){

            graphics2D.setFont(Unispace_Bold);
            graphics2D.setColor(new Color(9, 25, 126));

            String txt;
            int txtLength;
            int x;
            int y;
            txt = "You found the treasure!";
            txtLength = (int)graphics2D.getFontMetrics().getStringBounds(txt,graphics2D).getWidth();
            x = gamePanel.screenWidth/2 - txtLength/2;
            y = gamePanel.screenHeight/2-(gamePanel.tileSize*3);
            graphics2D.drawString(txt,x,y);

            graphics2D.setFont(Unispace_Bold2);
            graphics2D.setColor(new Color(229, 152, 9));
            txt = "CONGRATULATIONS!";
            txtLength = (int)graphics2D.getFontMetrics().getStringBounds(txt,graphics2D).getWidth();
            x = gamePanel.screenWidth/2 - txtLength/2;
            y = gamePanel.screenHeight/2-(gamePanel.tileSize*2);
            graphics2D.drawString(txt,x,y);

            gamePanel.gameThread = null;

        }else {
            graphics2D.setFont(Unispace_Bold);
            graphics2D.setColor(new Color(6, 18, 94));
            graphics2D.drawImage(key, gamePanel.tileSize / 2, gamePanel.tileSize / 2, gamePanel.tileSize / 2 + 10, gamePanel.tileSize / 2 + 10, null);
            graphics2D.drawString("x " + gamePanel.player.keys, 57, 50);
            if (msgOn) {
                graphics2D.drawString(msg, (gamePanel.tileSize / 2) * 6, gamePanel.tileSize);
                msgDelay(180);
            }
        }

    }
}
