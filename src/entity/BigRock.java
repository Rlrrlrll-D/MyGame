package entity;

import main.GamePanel;
import objects.DoorIron;
import tile.interactive.InteractiveTile;
import tile.interactive.MetalPlate;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        stay1 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
        stay2 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
        stay3 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
        stay_up1 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
        stay_up2 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
        stay_up3 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
        stay_left1 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
        stay_left2 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
        stay_left3 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
        stay_right1 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
        stay_right2 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
        stay_right3 = setup("/res/npc/bigrock", gamePanel.tileSize, gamePanel.tileSize);
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
                case "up", "stay_up":
                    worldY -= speed;
                    break;
                case "down", "stay":
                    worldY += speed;
                    break;
                case "left", "stay_left":
                    worldX -= speed;
                    break;
                case "right", "stay_right":
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
        ArrayList<InteractiveTile> plateList;
        ArrayList<Entity> rockList;

        plateList = IntStream.range(0, gamePanel.interactiveTile[1].length).filter(i -> gamePanel.interactiveTile[gamePanel.currentMap][i] != null
                && gamePanel.interactiveTile[gamePanel.currentMap][i].name != null
                && gamePanel.interactiveTile[gamePanel.currentMap][i].name.equals(MetalPlate.itName)).mapToObj(i -> gamePanel.interactiveTile[gamePanel.currentMap][i]).collect(Collectors.toCollection(ArrayList::new));
        rockList = IntStream.range(0, gamePanel.npc[1].length).filter(i -> gamePanel.npc[gamePanel.currentMap][i] != null
                && gamePanel.npc[gamePanel.currentMap][i].name.equals(BigRock.npcName)).mapToObj(i -> gamePanel.npc[gamePanel.currentMap][i]).collect(Collectors.toCollection(ArrayList::new));

        int count;

        plateList.forEach(interactiveTile -> {
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
        });
        count = (int) rockList.stream().filter(entity -> entity.linkedEntity != null).count();
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

