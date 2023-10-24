package main;

import objects.Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gamePanel;
    Font Unispace_Bold;
    BufferedImage key;
    public boolean msgOn;
    public String msg = "";

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        Unispace_Bold = new Font("Unispace-Bold", Font.PLAIN, 17);
        Key key = new Key();
        this.key = key.image;
    }

    public void drawing(Graphics2D graphics2D) {
        graphics2D.setFont(Unispace_Bold);
        graphics2D.setColor(new Color(9, 3, 1));
        graphics2D.drawImage(key, gamePanel.tileSize / 2, gamePanel.tileSize / 2, gamePanel.tileSize/2+10, gamePanel.tileSize/2+10, null);
        graphics2D.drawString("x " + gamePanel.player.keys, 57, 50);
    }
}
