package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    public void checkTile(Entity entity) throws IllegalStateException {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        String direction = entity.direct;
        if (entity.escapeKnock) {
            direction = entity.knockBackDirect;
        }

        switch (direction) {

            case "up", "stay_up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightCol][entityTopRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down", "stay":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left", "stay_left":

                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right", "stay_right":

                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + entity.direct + " " + gamePanel.keyHandler.enterPressed);
        }
    }

    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        String direction = entity.direct;
        if (entity.escapeKnock) {
            direction = entity.knockBackDirect;
        }

        for (int i = 0; i < gamePanel.objects[1].length; i++) {

            if (gamePanel.objects[gamePanel.currentMap][i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gamePanel.objects[gamePanel.currentMap][i].solidArea.x = gamePanel.objects[gamePanel.currentMap][i].worldX + gamePanel.objects[gamePanel.currentMap][i].solidArea.x;
                gamePanel.objects[gamePanel.currentMap][i].solidArea.y = gamePanel.objects[gamePanel.currentMap][i].worldY + gamePanel.objects[gamePanel.currentMap][i].solidArea.y;

                switch (direction) {
                    case "up", "stay_up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down", "stay":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left", "stay_left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right", "stay_right":
                        entity.solidArea.x += entity.speed;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + entity.direct);
                }
                if (entity.solidArea.intersects(gamePanel.objects[gamePanel.currentMap][i].solidArea)) {
                    index = getIndex(entity, player, i, index);
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                assert gamePanel.objects[i] != null;
                gamePanel.objects[gamePanel.currentMap][i].solidArea.x = gamePanel.objects[gamePanel.currentMap][i].solidAreaDefaultX;
                gamePanel.objects[gamePanel.currentMap][i].solidArea.y = gamePanel.objects[gamePanel.currentMap][i].solidAreaDefaultY;
            }


        }
        return index;
    }

    private int getIndex(Entity entity, boolean player, int i, int index) {
        if (entity.solidArea.intersects(gamePanel.objects[gamePanel.currentMap][i].solidArea)) {
            if (gamePanel.objects[gamePanel.currentMap][i].collision) {
                entity.collisionOn = true;
            }
            if (player) {
                index = i;
            }
        }
        return index;
    }

    public int checkEntity(Entity entity, Entity[][] target) {
        int index = 999;
        String direction = entity.direct;
        if (entity.escapeKnock) {
            direction = entity.knockBackDirect;
        }

        for (int i = 0; i < target[1].length; i++) {

            if (target[gamePanel.currentMap][i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                target[gamePanel.currentMap][i].solidArea.x = target[gamePanel.currentMap][i].worldX + target[gamePanel.currentMap][i].solidArea.x;
                target[gamePanel.currentMap][i].solidArea.y = target[gamePanel.currentMap][i].worldY + target[gamePanel.currentMap][i].solidArea.y;

                switch (direction) {
                    case "up", "stay_up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down", "stay":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left", "stay_left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right", "stay_right":
                        entity.solidArea.x += entity.speed;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + entity.direct);
                }
                if (entity.solidArea.intersects(target[gamePanel.currentMap][i].solidArea)) {
                    if (target[gamePanel.currentMap][i] != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                assert target[i] != null;
                target[gamePanel.currentMap][i].solidArea.x = target[gamePanel.currentMap][i].solidAreaDefaultX;
                target[gamePanel.currentMap][i].solidArea.y = target[gamePanel.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity) {

        boolean touchPlayer = false;

        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;

        switch (entity.direct) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + entity.direct);
        }
        if (entity.solidArea.intersects(gamePanel.player.solidArea)) {
            entity.collisionOn = true;
            touchPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        assert gamePanel.player != null;
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;

        return touchPlayer;
    }

}