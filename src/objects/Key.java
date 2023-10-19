package objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Key extends MotherObject {

    public Key(){
        name = "Key";
        try {

            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/key.png")));

        }catch (IOException e){
            e.getLocalizedMessage();
        }
       // solidArea.x = 5;
    }

}
