package main;

import entity.Entity;
import objects.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EntityGenerator {
    GamePanel gamePanel;
    private final Map<String, Function<GamePanel, Entity>> entityCreators;

    public EntityGenerator(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        entityCreators = new HashMap<>();
        entityCreators.put(Axe.objName, Axe::new);
        entityCreators.put(Boots.objName, Boots::new);
        entityCreators.put(Key.objName, Key::new);
        entityCreators.put(Lantern.objName, Lantern::new);
        entityCreators.put(PotionRed.objName, PotionRed::new);
        entityCreators.put(ManaCrystal.objName, ManaCrystal::new);
        entityCreators.put(ShieldBlue.objName, ShieldBlue::new);
        entityCreators.put(ShieldWood.objName, ShieldWood::new);
        entityCreators.put(Sword.objName, Sword::new);
        entityCreators.put(Tent.objName, Tent::new);
        entityCreators.put(Door.objName, Door::new);
        entityCreators.put(DoorIron.objName, DoorIron::new);
        entityCreators.put(Chest.objName, Chest::new);
        entityCreators.put(CoinBronze.objName, CoinBronze::new);
        entityCreators.put(Heart.objName, Heart::new);
        entityCreators.put(Rock.objName, Rock::new);
        entityCreators.put(Fireball.objName, Fireball::new);
        entityCreators.put(Pickaxe.objName, Pickaxe::new);
        entityCreators.put(BigDiamond.objName, BigDiamond::new);
    }

    public Entity getObj(String itemName) {
        Function<GamePanel, Entity> creator = entityCreators.get(itemName);
        return creator != null ? creator.apply(gamePanel) : null;
    }
}
