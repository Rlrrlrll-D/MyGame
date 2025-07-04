package environment;

import main.GamePanel;

import java.awt.*;

public class EnvironmentManager {
    GamePanel gamePanel;
    public Lighting lighting;

    public EnvironmentManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setup() {
        lighting = new Lighting(gamePanel);
    }

    public void update() {
        lighting.update();
    }

    public void drawing(Graphics2D graphics2D) {
        lighting.drawing(graphics2D);
    }
}
