import java.awt.*;

public class Apples extends Rectangle {
    public Apples(int x,int y){
        setBounds(x+10,y+8,8,8);
    }
    public void render(Graphics g){
        g.setColor(Color.green);
        g.fillRect(x,y,width,height);
    }
}
