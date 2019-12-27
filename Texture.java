import java.awt.image.BufferedImage;

public class Texture {
    public static BufferedImage players;
    public static BufferedImage ghost;
    public Texture(){
        players=Game.spriteSheet.getSprite(0,0);
        ghost= Game.spriteSheet.getSprite(0,16);
    }

}
