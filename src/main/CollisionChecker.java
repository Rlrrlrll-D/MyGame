package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direct) {

            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":

                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":

                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

//            default:
//                throw new IllegalStateException("Unexpected value: " + entity.direct);
        }
    }

    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gamePanel.motherObject.length; i++) {

            if (gamePanel.motherObject[i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gamePanel.motherObject[i].solidArea.x = gamePanel.motherObject[i].worldX + gamePanel.motherObject[i].solidArea.x;
                gamePanel.motherObject[i].solidArea.y = gamePanel.motherObject[i].worldY + gamePanel.motherObject[i].solidArea.y;

                switch (entity.direct) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gamePanel.motherObject[i].solidArea)) {
                            index = getIndex(entity, player, i, index);
                        }
                        break;

                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gamePanel.motherObject[i].solidArea)) {
                            index = getIndex(entity, player, i, index);
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gamePanel.motherObject[i].solidArea)) {
                            index = getIndex(entity, player, i, index);
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gamePanel.motherObject[i].solidArea)) {
                            index = getIndex(entity, player, i, index);
                        }
                        break;
//                    default:
//                        throw new IllegalStateException("Unexpected value: " + entity.direct);
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                assert gamePanel.motherObject[i] != null;
                gamePanel.motherObject[i].solidArea.x = gamePanel.motherObject[i].solidAreaDefaultX;
                gamePanel.motherObject[i].solidArea.y = gamePanel.motherObject[i].solidAreaDefaultY;
            }


        }
        return index;
    }

    private int getIndex(Entity entity, boolean player, int i, int index) {
        if (entity.solidArea.intersects(gamePanel.motherObject[i].solidArea)) {
            if (gamePanel.motherObject[i].collision) {
                entity.collisionOn = true;
            }
            if (player) {
                index = i;
            }
        }
        return index;
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {

            if (target[i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direct) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;

                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }

                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
//                    default:
//                        throw new IllegalStateException("Unexpected value: " + entity.direct);
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                assert target[i] != null;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;

    }

    public void checkPlayer(Entity entity) {

        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;

        switch (entity.direct) {
            case "up":
                entity.solidArea.y -= entity.speed;
                if (entity.solidArea.intersects(gamePanel.player.solidArea)) {
                    entity.collisionOn = true;

                }
                break;

            case "down":
                entity.solidArea.y += entity.speed;
                if (entity.solidArea.intersects(gamePanel.player.solidArea)) {
                    entity.collisionOn = true;

                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(gamePanel.player.solidArea)) {
                    entity.collisionOn = true;

                }

                break;
            case "right":
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(gamePanel.player.solidArea)) {
                    entity.collisionOn = true;

                }
                break;
//                    default:
//                        throw new IllegalStateException("Unexpected value: " + entity.direct);
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        assert gamePanel.player != null;
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
    }

}