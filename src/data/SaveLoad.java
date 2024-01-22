package data;

import entity.Entity;
import main.GamePanel;
import objects.*;

import java.io.*;


public class SaveLoad {

    GamePanel gamePanel;

    public SaveLoad(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public Entity getObj(String itemName) {
        Entity obj;
        obj = switch (itemName) {
            case "Woodcutter's Axe" -> new Axe(gamePanel);
            case "Boots" -> new Boots(gamePanel);
            case "Key" -> new Key(gamePanel);
            case "Lantern" -> new Lantern(gamePanel);
            case "Red Potion" -> new PotionRed(gamePanel);
            case "ShieldBlue" -> new ShieldBlue(gamePanel);
            case "Shield" -> new ShieldWood(gamePanel);
            case "Sword" -> new Sword(gamePanel);
            case "Tent" -> new Tent(gamePanel);
            case "Door" -> new Door(gamePanel);
            case "Chest" -> new Chest(gamePanel);
            default -> null;
        };
        return obj;
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
            for (int i = 0; i < gamePanel.player.inventory.size(); i++) {
                dataStorage.itemNames.add(gamePanel.player.inventory.get(i).name);
                dataStorage.itemAmounts.add(gamePanel.player.inventory.get(i).amount);

            }
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

        gamePanel.player.inventory.clear();
        for (int i = 0; i < dataStorage.itemNames.size(); i++) {
            gamePanel.player.inventory.add(getObj(dataStorage.itemNames.get(i)));
            gamePanel.player.inventory.get(i).amount = dataStorage.itemAmounts.get(i);

        }
    }
}
