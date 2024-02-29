package tile.interactive;

import main.GamePanel;

public class MetalPlate extends InteractiveTile {
    public static final String itName = "MetalPlate";
    GamePanel gamePanel;

    public MetalPlate(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        this.gamePanel = gamePanel;

        this.worldX = gamePanel.tileSize * col;
        this.worldY = gamePanel.tileSize * row;

        name = itName;
        down1 = setup("/tile/pictures/metal_plate", gamePanel.tileSize, gamePanel.tileSize);
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
