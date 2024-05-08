package main;

import entity.Entity;
import entity.Projectile;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    public boolean checkProjectileCollision(Projectile projectile1, Projectile projectile2) {
        // Calculate the positions of the projectiles
        projectile1.solidArea.x = projectile1.worldX + projectile1.solidArea.x;
        projectile1.solidArea.y = projectile1.worldY + projectile1.solidArea.y;

        projectile2.solidArea.x = projectile2.worldX + projectile2.solidArea.x;
        projectile2.solidArea.y = projectile2.worldY + projectile2.solidArea.y;

        // Check if the solid areas of the two projectiles intersect
        boolean collision = projectile1.solidArea.intersects(projectile2.solidArea);

        // Reset the solid area positions
        projectile1.solidArea.x = projectile1.solidAreaDefaultX;
        projectile1.solidArea.y = projectile1.solidAreaDefaultY;

        projectile2.solidArea.x = projectile2.solidAreaDefaultX;
        projectile2.solidArea.y = projectile2.solidAreaDefaultY;

        // Return the result of the collision check
        return collision;
    }

    public Entity getCollidingEntity(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        var direction = entity.direct;
        if (entity.escapeKnock) {
            direction = entity.knockBackDirect;
        }
        switch (direction) {
            case "up", "stay_up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightCol][entityTopRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                    entity.worldX = entity.worldX - gamePanel.tileSize / 4;
                    entity.worldY = entity.worldY - gamePanel.tileSize;
                    return entity;
                }
            }
            case "down", "stay" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                    //entity.worldX = entity.worldX + gamePanel.tileSize;
                    entity.worldY = entity.worldY + gamePanel.tileSize / 2;
                    return entity;
                }
            }
            case "left", "stay_left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                    entity.worldX = entity.worldX - gamePanel.tileSize;
                    //entity.worldY = entity.worldY + gamePanel.tileSize;
                    return entity;
                }
            }
            case "right", "stay_right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                    entity.worldX = entity.worldX + gamePanel.tileSize / 2;
                    //entity.worldY = entity.worldY + gamePanel.tileSize;
                    return entity;
                }
            }
            default ->
                    throw new IllegalStateException("Unexpected value: " + entity.direct + " " + gamePanel.keyHandler.enterPressed);
        }
        return null;
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

        var direction = entity.direct;
        if (entity.escapeKnock) {
            direction = entity.knockBackDirect;
        }
        switch (direction) {
            case "up", "stay_up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightCol][entityTopRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "down", "stay" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "left", "stay_left" -> {

                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "right", "stay_right" -> {

                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            default ->
                    throw new IllegalStateException("Unexpected value: " + entity.direct + " " + gamePanel.keyHandler.enterPressed);
        }
    }

    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        var direction = entity.direct;
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
                    case "up", "stay_up" -> entity.solidArea.y -= entity.speed;
                    case "down", "stay" -> entity.solidArea.y += entity.speed;
                    case "left", "stay_left" -> entity.solidArea.x -= entity.speed;
                    case "right", "stay_right" -> entity.solidArea.x += entity.speed;
                    default -> throw new IllegalStateException("Unexpected value: " + entity.direct);
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
        var direction = entity.direct;
        if (entity.escapeKnock) {
            direction = entity.knockBackDirect;
        }

        for (int i = 0; i < target[1].length; i++)
            if (target[gamePanel.currentMap][i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                target[gamePanel.currentMap][i].solidArea.x = target[gamePanel.currentMap][i].worldX + target[gamePanel.currentMap][i].solidArea.x;
                target[gamePanel.currentMap][i].solidArea.y = target[gamePanel.currentMap][i].worldY + target[gamePanel.currentMap][i].solidArea.y;

                switch (direction) {
                    case "up", "stay_up" -> entity.solidArea.y -= entity.speed;
                    case "down", "stay" -> entity.solidArea.y += entity.speed;
                    case "left", "stay_left" -> entity.solidArea.x -= entity.speed;
                    case "right", "stay_right" -> entity.solidArea.x += entity.speed;
                    default -> throw new IllegalStateException("Unexpected value: " + entity.direct);
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
        return index;
    }

    public boolean checkPlayer(Entity entity) {

        var touchPlayer = false;

        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;

        switch (entity.direct) {
            case "up", "stay_up" -> entity.solidArea.y -= entity.speed;
            case "down", "stay" -> entity.solidArea.y += entity.speed;
            case "left", "stay_left" -> entity.solidArea.x -= entity.speed;
            case "right", "stay_right" -> entity.solidArea.x += entity.speed;
            default -> throw new IllegalStateException("Unexpected value: " + entity.direct);
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