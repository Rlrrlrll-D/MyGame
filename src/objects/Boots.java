package objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Boots extends
MotherObject{
    public Boots(){
        name = "Boots";
        try {

            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/boots.png")));

        }catch (IOException e){
            e.getLocalizedMessage();
        }
        // solidArea.x = 5;
    }
}
