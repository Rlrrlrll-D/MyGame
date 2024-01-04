package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {
    GamePanel gamePanel;
    BufferedImage darknessFilter;

    public Lighting(GamePanel gamePanel, int circleSize) {
        this.gamePanel = gamePanel;
        darknessFilter = new BufferedImage(gamePanel.screenWidth, gamePanel.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = (Graphics2D) darknessFilter.getGraphics();

        getScreenArea(gamePanel, circleSize, graphics2D);


    }

    private static void getScreenArea(GamePanel gamePanel, int circleSize, Graphics2D gr) {
        Area screenArea = new Area(new Rectangle2D.Double(0, 0, gamePanel.screenWidth, gamePanel.screenHeight));

        int centerX = gamePanel.player.screenX + (gamePanel.tileSize) / 2;
        int centerY = gamePanel.player.screenY + (gamePanel.tileSize) / 2;

        double x = centerX - ((double) circleSize / 2);
        double y = centerY - ((double) circleSize / 2);

        Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);

        Area lightArea = new Area(circleShape);
        screenArea.subtract(lightArea);
        Color[] color = new Color[12];
        float[] fraction = new float[12];
        color[0] = new Color(0, 0, 0, 0.1f);
        color[1] = new Color(0, 0, 0, 0.42f);
        color[2] = new Color(0, 0, 0, 0.52f);
        color[3] = new Color(0, 0, 0, 0.61f);
        color[4] = new Color(0, 0, 0, 0.69f);
        color[5] = new Color(0, 0, 0, 0.76f);
        color[6] = new Color(0, 0, 0, 0.82f);
        color[7] = new Color(0, 0, 0, 0.87f);
        color[8] = new Color(0, 0, 0, 0.91f);
        color[9] = new Color(0, 0, 0, 0.94f);
        color[10] = new Color(0, 0, 0, 0.96f);
        color[11] = new Color(0, 0, 0, 0.98f);

        fraction[0] = 0f;
        fraction[1] = 0.4f;
        fraction[2] = 0.5f;
        fraction[3] = 0.6f;
        fraction[4] = 0.65f;
        fraction[5] = 0.7f;
        fraction[6] = 0.75f;
        fraction[7] = 0.8f;
        fraction[8] = 0.85f;
        fraction[9] = 0.9f;
        fraction[10] = 0.95f;
        fraction[11] = 1f;

        RadialGradientPaint radialGradientPaint = new RadialGradientPaint(centerX, centerY, ((float) circleSize / 2), fraction, color);
        gr.setPaint(radialGradientPaint);

        gr.fill(lightArea);
        gr.fill(screenArea);
        gr.dispose();
    }

    public void drawing(Graphics2D graphics2D) {
        graphics2D.drawImage(darknessFilter, 0, 0, null);
    }
}
