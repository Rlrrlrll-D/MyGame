package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultVal();
    }
    public void  setDefaultVal(){
        x = gamePanel.dflX;
        y = gamePanel.dflY;
        speed = 3;
    }
    public void update(){

        if (keyHandler.upPressed){
            y-=speed;
        } else if (keyHandler.downPressed) {
           y +=speed;
        } else if (keyHandler.leftPressed) {
            x-=speed;
        } else if (keyHandler.rightPressed) {
            x+=speed;
        }

    }
    public void drawing(Graphics2D graphics2D){

        graphics2D.setColor(new Color(119, 52, 6));
        graphics2D.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);

    }
}
