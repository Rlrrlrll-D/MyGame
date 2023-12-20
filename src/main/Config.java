package main;

import java.io.*;

public class Config {
    GamePanel gamePanel;

    public Config(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void saveConfig() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("config.txt"));
        if (gamePanel.fullScreenOn) {
            bufferedWriter.write("On");
        }
        if (!gamePanel.fullScreenOn) {
            bufferedWriter.write("Off");
        }
        bufferedWriter.newLine();
        bufferedWriter.write(String.valueOf(gamePanel.sound.volumeScale));
        bufferedWriter.newLine();
        bufferedWriter.write(String.valueOf(gamePanel.SFX.volumeScale));
        bufferedWriter.newLine();
        bufferedWriter.close();

    }

    public void loadConfig() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("config.txt"));
        String string = bufferedReader.readLine();

        if (string.equals("On")) {
            gamePanel.fullScreenOn = true;
        }
        if (string.equals("Off")) {
            gamePanel.fullScreenOn = false;
        }
        string = bufferedReader.readLine();
        gamePanel.sound.volumeScale = Integer.parseInt(string);

        string = bufferedReader.readLine();
        gamePanel.SFX.volumeScale = Integer.parseInt(string);
        bufferedReader.close();
    }
}
