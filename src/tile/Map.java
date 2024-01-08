package tile;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Map extends TileManager{
    GamePanel gamePanel;
    BufferedImage[] worldMap;
    public boolean miniMap;
    public Map(GamePanel gamePanel) throws Exception {
        super(gamePanel);
        this.gamePanel = gamePanel;
        createWorldMap();
    }
    public  void createWorldMap(){
        worldMap = new BufferedImage[GamePanel.maxMap];
        int worldMapWidth = gamePanel.tileSize*GamePanel.maxWorldCol;
        int worldMapHeight = gamePanel.tileSize*GamePanel.maxWorldRow;

        for (int i = 0; i < GamePanel.maxMap; i++) {
            worldMap[i] = new BufferedImage(worldMapWidth,worldMapHeight,BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D;
            graphics2D = worldMap[i].createGraphics();

            int col = 0;
            int row = 0;

            while(col<GamePanel.maxWorldCol&&row<GamePanel.maxWorldRow){
                int tileNum = mapTileNum[i][col][row];
                int x  = gamePanel.tileSize*col;
                int y  = gamePanel.tileSize*row;
                graphics2D.drawImage(tiles[tileNum].image, x, y, null);

                col++;
                if (col==GamePanel.maxWorldCol){
                    col =0;
                    row++;
                }
            }
        }
    }


    public void drawFullMapScreen(Graphics2D graphics2D) {
        graphics2D.setColor(Color.black);
        graphics2D.fillRect(0,0,gamePanel.screenWidth,gamePanel.screenHeight);

        int width = 500;
        int height = 500;
        int x  = gamePanel.screenWidth/2-width/2;
        int y  = gamePanel.screenHeight/2-height/2;
        graphics2D.drawImage(worldMap[gamePanel.currentMap],x,y, width, height,null);
    }
}
