package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultVal();
        getPlayerImg();
    }

    public void setDefaultVal() {
        x = gamePanel.dfl_X;
        y = gamePanel.dfl_Y;
        speed = 3;
        direct = "down";
    }

    public void getPlayerImg() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/me_up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/me_up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/me_down1.png.")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/me_down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/me_left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/me_left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/me_right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/me_right2.png")));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyHandler.upPressed) {
            direct = "up";
            y -= speed;
        } else if (keyHandler.downPressed) {
            direct = "down";
            y += speed;
        } else if (keyHandler.leftPressed) {
            direct = "left";
            x -= speed;
        } else if (keyHandler.rightPressed) {
            direct = "right";
            x += speed;
        }

    }

    public void drawing(Graphics2D graphics2D) {

     graphics2D.setColor(new Color(119, 52, 6));


        BufferedImage image = switch (direct) {
            case "up" -> up1;
            case "down" -> down1;
            case "left" -> left1;
            case "right" -> right1;
            default -> null;
        };

        graphics2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);


    }
}
