package entity;

import main.GamePanel;

import java.awt.*;

public class BigRock extends Entity {
    public static final String npcName = "BigRock";
    public BigRock(GamePanel gamePanel) {
        super(gamePanel);
        name = npcName;
        direct = "down";
        speed = 4;
        solidArea = new Rectangle();
        solidArea.x = 2;
        solidArea.y = 6;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 44;
        solidArea.height = 40;

        dialogSet = -1;

        getImg();
        setDialogue();
    }

    private void getImg() {

        up1 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
    }

    private void setDialogue() {
        dialogues[0][0] = "It's a giant rock";
    }

    public void setAction() {
        super.setAction();
    }

    public void move(String direct) {
        this.direct = direct;
        checkCollision();
        if (!collisionOn) {
            switch (direct) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + direct);
            }
        }
    }

    public void update() {
    }

    public void speak() {
        facePlayer();
        startDialog(this, dialogSet);
        dialogSet++;
        if (dialogues[dialogSet][0] == null) {
            dialogSet--;
        }
    }
}
