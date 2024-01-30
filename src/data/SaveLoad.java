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

    private Entity getObj(String itemName) {
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
            case "Bronze Coin" -> new CoinBronze(gamePanel);
            case "Heart" -> new Heart(gamePanel);
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

            dataStorage.currentWeaponSlot = gamePanel.player.getCurrentWeaponSlot();
            dataStorage.currentShieldSlot = gamePanel.player.getCurrentShieldSlot();

            dataStorage.mapObjNames = new String[GamePanel.maxMap][gamePanel.objects[1].length];
            dataStorage.objWorldX = new int[GamePanel.maxMap][gamePanel.objects[1].length];
            dataStorage.objWorldY = new int[GamePanel.maxMap][gamePanel.objects[1].length];
            dataStorage.lootNames = new String[GamePanel.maxMap][gamePanel.objects[1].length];
            dataStorage.objOpened = new boolean[GamePanel.maxMap][gamePanel.objects[1].length];

            for (int i = 0; i < GamePanel.maxMap; i++) {
                for (int j = 0; j < gamePanel.objects[1].length; j++) {
                    if (gamePanel.objects[i][j] == null) {
                        dataStorage.mapObjNames[i][j] = "NA";
                    } else {
                        dataStorage.mapObjNames[i][j] = gamePanel.objects[i][j].name;
                        dataStorage.objWorldX[i][j] = gamePanel.objects[i][j].worldX;
                        dataStorage.objWorldY[i][j] = gamePanel.objects[i][j].worldY;
                        if (gamePanel.objects[i][j].loot != null) {
                            dataStorage.lootNames[i][j] = gamePanel.objects[i][j].loot.name;
                        }
                        dataStorage.objOpened[i][j] = gamePanel.objects[i][j].opened;
                    }
                }
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
        gamePanel.player.currentWeapon = gamePanel.player.inventory.get(dataStorage.currentWeaponSlot);
        gamePanel.player.currentShield = gamePanel.player.inventory.get(dataStorage.currentShieldSlot);
        gamePanel.player.getAttack();
        gamePanel.player.getDefence();
        gamePanel.player.getAttackImage();
        for (int i = 0; i < GamePanel.maxMap; i++) {
            for (int j = 0; j < gamePanel.objects[1].length; j++) {
                if (dataStorage.mapObjNames[i][j].equals("NA")) {
                    gamePanel.objects[i][j] = null;
                } else {
                    gamePanel.objects[i][j] = getObj(dataStorage.mapObjNames[i][j]);
                    gamePanel.objects[i][j].worldX = dataStorage.objWorldX[i][j];
                    gamePanel.objects[i][j].worldY = dataStorage.objWorldY[i][j];
                    if (dataStorage.lootNames[i][j] != null) {
                        gamePanel.objects[i][j].loot = getObj(dataStorage.lootNames[i][j]);
                    }
                    gamePanel.objects[i][j].opened = dataStorage.objOpened[i][j];
                    if (gamePanel.objects[i][j].opened) {
                        gamePanel.objects[i][j].down1 = gamePanel.objects[i][j].image1;
                    }
                }
            }
        }
    }
}
