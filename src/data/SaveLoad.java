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
            savePlayerData(dataStorage);
            saveInventoryData(dataStorage);
            saveObjectData(dataStorage);
            objectOutputStream.writeObject(dataStorage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void savePlayerData(DataStorage dataStorage) {
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
    }

    private void saveInventoryData(DataStorage dataStorage) {
        for (int i = 0; i < gamePanel.player.inventory.size(); i++) {
            dataStorage.itemNames.add(gamePanel.player.inventory.get(i).name);
            dataStorage.itemAmounts.add(gamePanel.player.inventory.get(i).amount);
        }
        dataStorage.currentWeaponSlot = gamePanel.player.getCurrentWeaponSlot();
        dataStorage.currentShieldSlot = gamePanel.player.getCurrentShieldSlot();
    }

    private void saveObjectData(DataStorage dataStorage) {
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
    }

    public void load() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("save.dat"));
        DataStorage dataStorage = (DataStorage) objectInputStream.readObject();

        loadPlayerData(dataStorage);
        loadInventoryData(dataStorage);
        loadObjectData(dataStorage);
    }

    private void loadPlayerData(DataStorage dataStorage) {
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

    private void loadInventoryData(DataStorage dataStorage) {
        gamePanel.player.inventory.clear();

        for (int i = 0; i < dataStorage.itemNames.size(); i++) {
            gamePanel.player.inventory.add(gamePanel.entityGenerator.getObj(dataStorage.itemNames.get(i)));
            gamePanel.player.inventory.get(i).amount = dataStorage.itemAmounts.get(i);
        }
        gamePanel.player.currentWeapon = gamePanel.player.inventory.get(dataStorage.currentWeaponSlot);
        gamePanel.player.currentShield = gamePanel.player.inventory.get(dataStorage.currentShieldSlot);
        gamePanel.player.getAttack();
        gamePanel.player.getDefence();
        gamePanel.player.getAttackImage();
    }

    private void loadObjectData(DataStorage dataStorage) {
        for (int i = 0; i < GamePanel.maxMap; i++) {
            for (int j = 0; j < gamePanel.objects[1].length; j++) {
                if (dataStorage.mapObjNames[i][j].equals("NA")) {
                    gamePanel.objects[i][j] = null;
                } else {
                    gamePanel.objects[i][j] = gamePanel.entityGenerator.getObj(dataStorage.mapObjNames[i][j]);
                    gamePanel.objects[i][j].worldX = dataStorage.objWorldX[i][j];
                    gamePanel.objects[i][j].worldY = dataStorage.objWorldY[i][j];
                    if (dataStorage.lootNames[i][j] != null) {
                        gamePanel.objects[i][j].setLoot(gamePanel.entityGenerator.getObj(dataStorage.lootNames[i][j]));
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