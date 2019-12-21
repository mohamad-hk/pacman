import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    private  boolean isRunning=false;
    public static final int Width=640;
    public static final int Height=640;
    public static final String Title="pac-Man";
    private  Thread thread;
    public Game(){
        Dimension dimension=new Dimension(Game.Width,Game.Height);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
    }
    public synchronized  void start(){
    if(isRunning)return;
    isRunning=true;
    thread=new Thread(this);
    thread.start();
    }
    public synchronized  void stop(){
        if(isRunning)return;
        isRunning=false;
        try {
            thread.join();

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    private void tick(){
    System.out.println("working");
    }
    private void render(){

        BufferStrategy bs=getBufferStrategy();
        if(bs==null){
            createBufferStrategy(3);
            return;
        }
        Graphics g=bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Game.Width,Game.Height);
        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        int fps=0;
        double timer=System.currentTimeMillis();
        long lasttime=System.nanoTime();
        double targettick=60.0;
        double delta=0;
        double ns=1000000000/targettick;
        while ((isRunning)){
            long now=System.nanoTime();
            delta+=(lasttime)/ns;
            lasttime=now;
            while (delta>=1){
                tick();
                render();
                fps++;
                delta--;
            }
            render();
            fps++;
            if(System.currentTimeMillis()-timer>=1000){
                System.out.println(fps);
                fps=0;
                timer+=1000;
            }
            tick();
            render();
        }
        stop();
    }
    public static void main(String [] args){
        Game game=new Game();
        JFrame frame=new JFrame();
        frame.setTitle(Game.Title);
        frame.add(game);
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();
    }
}
