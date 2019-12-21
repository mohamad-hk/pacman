import java.awt.*;

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
        if(right) x+=speed;
        if(left)x-=speed;
        if(up)y-=speed;
        if(down)y*=speed;
    }
    public void render(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect(x,y,width,height);
    }

}
