package tile;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Map extends TileManager {
    public boolean miniMap;
    GamePanel gamePanel;
    BufferedImage[] worldMap;

    public Map(GamePanel gamePanel) throws Exception {
        super(gamePanel);
        this.gamePanel = gamePanel;
        createWorldMap();
    }

    private void createWorldMap() {
        worldMap = new BufferedImage[GamePanel.maxMap];
        int worldMapWidth = gamePanel.tileSize * GamePanel.maxWorldCol;
        int worldMapHeight = gamePanel.tileSize * GamePanel.maxWorldRow;

        for (int i = 0; i < GamePanel.maxMap; i++) {
            worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D;
            graphics2D = worldMap[i].createGraphics();

            int col = 0;
            int row = 0;

            while (col < GamePanel.maxWorldCol && row < GamePanel.maxWorldRow) {
                int tileNum = mapTileNum[i][col][row];
                int x = gamePanel.tileSize * col;
                int y = gamePanel.tileSize * row;
                graphics2D.drawImage(tiles[tileNum].image, x, y, null);

                col++;
                if (col == GamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
        }
    }


    public void drawFullMapScreen(Graphics2D graphics2D) {
        graphics2D.setColor(Color.black);
        graphics2D.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        int width = 500;
        int height = 500;
        int x = gamePanel.screenWidth / 2 - width / 2;
        int y = gamePanel.screenHeight / 2 - height / 2;
        graphics2D.drawImage(worldMap[gamePanel.currentMap], x, y, width, height, null);

        double scale = (double) (gamePanel.tileSize * GamePanel.maxWorldCol) / width;
        int playerX = (int) (x + gamePanel.player.worldX / scale);
        int playerY = (int) (y + gamePanel.player.worldY / scale);
        int playerSize = gamePanel.tileSize/2;
        graphics2D.drawImage(gamePanel.player.down1, playerX, playerY, playerSize, playerSize, null);

        graphics2D.setFont(gamePanel.ui.Monica.deriveFont(20f));
        graphics2D.setColor(new Color(229, 152, 9));
        graphics2D.drawString("Press CTRL to close", 750, 550);
    }
    public void drawMiniMap(Graphics2D graphics2D){
        if (miniMap){
            int width = 200;
            int height = 200;
            int x = gamePanel.screenWidth - width-50;
            int y = 50;
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
            graphics2D.drawImage(worldMap[gamePanel.currentMap], x, y, width, height, null);

            double scale = (double) (gamePanel.tileSize * GamePanel.maxWorldCol) / width;
            int playerX = (int) (x + gamePanel.player.worldX / scale);
            int playerY = (int) (y + gamePanel.player.worldY / scale);
            int playerSize = gamePanel.tileSize/3;
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
            graphics2D.drawImage(gamePanel.player.down1, playerX, playerY, playerSize, playerSize, null);

        }
    }
}
