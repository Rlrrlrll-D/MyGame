package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage stay1, stay2, stay3, stay_up1, stay_up2, stay_up3,
            stay_left1, stay_left2, stay_left3, stay_right1, stay_right2, stay_right3, up1, up2, down1, down2, left1, left2, right1, right2;
    public String direct, stayDirect;

    public int spriteNum = 1, counter = 0;

    public Rectangle solidArea;
    public  int solidAreaDfltX, solidAreaDfltY;
    public boolean collisionOn;



}
