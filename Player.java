import java.awt.*;
import java.util.logging.Level;

public class Player extends Rectangle {
    public boolean right;
    public boolean left;
    public boolean up;
    public boolean down;
    private int speed=2;

    public Player(int x,int y){

        setBounds(x,y,32,32);
    }
    public void tick(){
        if(right && canMove(x+speed,y)) x+=speed;
        if(left && canMove(x-speed,y))x-=speed;
        if(up && canMove(x,y-speed))y-=speed;
        if(down && canMove(x,y+speed))y+=speed;
        level level=Game.level;
        for (int i=0;i<level.apples.size();i++){
            if(this.intersects(level.apples.get(i))){
                level.apples.remove(i);
                break;
            }
        }
        if (level.apples.size()==0){

        }
    }
    private boolean canMove(int nextx,int nedxty){
        Rectangle bounds = new Rectangle(nextx,nedxty,width,height);
        level level=Game.level;
        for(int xx=0;xx<level.tiles.length;xx++){
            for(int yy=0;yy<level.tiles[0].length;y++){
                if (level.tiles[xx][yy]!=null){
                    if(bounds.intersects(level.tiles[xx][yy])){
                        return false;
                    }
                }
            }
        }
    return true;
    }
    public void render(Graphics g){
        SpriteSheet sheet=Game.spriteSheet;
       g.drawImage(sheet.getSprite(0,0),x,y,32,32,null);
    }

}
