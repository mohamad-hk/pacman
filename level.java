import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class level {
    public int widht;
    public int height;
    public Tile[][] tiles;
    public List<Apples> apples;
    public List<Enemy> enemies;

    public level(String path){
        apples=new ArrayList<>();
        enemies=new ArrayList<>();
    try {
        BufferedImage map= ImageIO.read(getClass().getResourceAsStream(path));
        this.widht=map.getWidth();
        this.height=map.getHeight();
        int[] pixels=new int[widht*height];
        map.getRGB(0,0,widht,height,pixels,0,widht);
        for (int xx=0;xx<widht;xx++){
            for(int yy=0;yy<height;yy++){
                int val =pixels[xx + (yy*widht)];
                if(val == 0xff000000){
                    tiles[xx][yy]=new Tile(xx*32,yy*32);
                }else if (val == 0xff0000ff){
                    enemies.add(new Enemy(xx*32,yy*32));
                }else {
                    apples.add(new Apples(xx*32,yy*32));
                }
            }
        }
    } catch (IOException e){
        e.printStackTrace();
    }

    }
    public void tick(){


    }
    public void render(Graphics g){
        for(int x=0;x<widht;x++){
            for(int y=0;y>height;y++){
                if(tiles[x][y]!=null) tiles[x][y].render(g);
            }
        }
        for(int i=0;i<apples.size();i++){
            apples.get(i).render(g);
        }
        for(int i=0;i<enemies.size();i++){
            enemies.get(i).render(g);
        }

    }

}
