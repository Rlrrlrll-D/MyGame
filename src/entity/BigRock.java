package entity;

import main.GamePanel;
import objects.DoorIron;
import tile.interactive.InteractiveTile;
import tile.interactive.MetalPlate;

import java.awt.*;
import java.util.ArrayList;

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
        detectPlate();
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

    public void detectPlate() {
        ArrayList<InteractiveTile> plateList = new ArrayList<>();
        ArrayList<Entity> rockList = new ArrayList<>();

        for (int i = 0; i < gamePanel.interactiveTile[1].length; i++) {
            if (gamePanel.interactiveTile[gamePanel.currentMap][i] != null
                    && gamePanel.interactiveTile[gamePanel.currentMap][i].name != null
                    && gamePanel.interactiveTile[gamePanel.currentMap][i].name.equals(MetalPlate.itName)) {
                plateList.add(gamePanel.interactiveTile[gamePanel.currentMap][i]);
            }
        }
        for (int i = 0; i < gamePanel.npc[1].length; i++) {
            if (gamePanel.npc[gamePanel.currentMap][i] != null
                    && gamePanel.npc[gamePanel.currentMap][i].name.equals(BigRock.npcName)) {
                rockList.add(gamePanel.npc[gamePanel.currentMap][i]);
            }
        }

        int count = 0;

        for (InteractiveTile interactiveTile : plateList) {
            int xDistance = Math.abs(worldX - interactiveTile.worldX);
            int yDistance = Math.abs(worldY - interactiveTile.worldY);
            int distance = Math.max(xDistance, yDistance);

            if (distance < 8) {
                if (linkedEntity == null) {
                    linkedEntity = interactiveTile;
                    gamePanel.playSFX(2);
                }
            } else {
                if (linkedEntity == interactiveTile) {
                    linkedEntity = null;
                }
            }
        }
        for (Entity entity : rockList) {
            if (entity.linkedEntity != null) {
                count++;
            }
        }
        if (count == rockList.size()) {
            for (int i = 0; i < gamePanel.objects[1].length; i++) {
                if (gamePanel.objects[gamePanel.currentMap][i] != null && gamePanel.objects[gamePanel.currentMap][i].name.equals(DoorIron.objName)) {
                    gamePanel.objects[gamePanel.currentMap][i] = null;
                    gamePanel.playSFX(24);
                }
            }
        }
    }
}

