package objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Chest extends MotherObject {

    public Chest() {
        name = "Chest";
        try {

            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/chest.png")));

        } catch (IOException e) {
            e.getLocalizedMessage();
        }
    }

}
