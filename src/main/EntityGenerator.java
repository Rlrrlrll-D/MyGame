package main;

import entity.Entity;
import objects.*;

public class EntityGenerator {
    GamePanel gamePanel;
       public EntityGenerator(GamePanel gamePanel){
           this.gamePanel = gamePanel;
       }
    public Entity getObj(String itemName) {
        Entity obj;
        obj = switch (itemName) {
            case Axe.objName -> new Axe(gamePanel);
            case Boots.objName -> new Boots(gamePanel);
            case Key.objName -> new Key(gamePanel);
            case Lantern.objName-> new Lantern(gamePanel);
            case PotionRed.objName -> new PotionRed(gamePanel);
            case ManaCrystal.objName -> new ManaCrystal(gamePanel);
            case ShieldBlue.objName -> new ShieldBlue(gamePanel);
            case ShieldWood.objName-> new ShieldWood(gamePanel);
            case Sword.objName-> new Sword(gamePanel);
            case Tent.objName-> new Tent(gamePanel);
            case Door.objName -> new Door(gamePanel);
            case Chest.objName -> new Chest(gamePanel);
            case CoinBronze.objName -> new CoinBronze(gamePanel);
            case Heart.objName -> new Heart(gamePanel);
            case Rock.objName -> new Rock(gamePanel);
            case Fireball.objName -> new Fireball(gamePanel);
            default -> null;
        };
        return obj;
    }
}
