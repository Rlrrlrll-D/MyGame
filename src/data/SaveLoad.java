package data;

import main.GamePanel;

import java.io.*;


public class SaveLoad {

    GamePanel gamePanel;

    public SaveLoad(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void save() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("save.dat"));
            DataStorage dataStorage = new DataStorage();
            dataStorage.level = gamePanel.player.level;
            dataStorage.maxLife = gamePanel.player.maxLife;
            dataStorage.life = gamePanel.player.life;
            dataStorage.maxMana = gamePanel.player.maxMana;
            dataStorage.mana = gamePanel.player.mana;
            dataStorage.strength = gamePanel.player.strength;
            dataStorage.dexterity = gamePanel.player.dexterity;
            dataStorage.exp = gamePanel.player.exp;
            dataStorage.nextLevelExp = gamePanel.player.nextLevelExp;
            dataStorage.coin = gamePanel.player.coin;

            objectOutputStream.writeObject(dataStorage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("save.dat"));
        DataStorage dataStorage = (DataStorage) objectInputStream.readObject();

        gamePanel.player.level = dataStorage.level;
        gamePanel.player.maxLife = dataStorage.maxLife;
        gamePanel.player.life = dataStorage.life;
        gamePanel.player.maxMana = dataStorage.maxMana;
        gamePanel.player.mana = dataStorage.mana;
        gamePanel.player.strength = dataStorage.strength;
        gamePanel.player.dexterity = dataStorage.dexterity;
        gamePanel.player.exp = dataStorage.exp;
        gamePanel.player.nextLevelExp = dataStorage.nextLevelExp;
        gamePanel.player.coin = dataStorage.coin;
    }
}
