package objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import main.GamePanel;

public class MotherObject {

    public BufferedImage image;
    public String name;

    public boolean collision;

    public int worldX, worldY;

    public void drawing(Graphics2D graphics2D,GamePanel gamePanel){
        int scrX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int scrY = worldY- gamePanel.player.worldY + gamePanel.player.screenY;
        if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
            graphics2D.drawImage(image, scrX,
                    scrY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }

}
