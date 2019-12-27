import sun.audio.AudioStream;

import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Game extends Canvas implements Runnable, KeyListener {

    private  boolean isRunning=false;
    public static final int Width=640;
    public static final int Height=640;
    public static final String Title="pac-Man";
    private  Thread thread;
    public static Player player;
    public static level level;
    public static SpriteSheet spriteSheet;
    public Game(){
        Dimension dimension=new Dimension(Game.Width,Game.Height);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
      player=new Player(Game.Width/2,Game.Height/2);
      level=new level("/map/map.png");
      addKeyListener(this);
        level =new level("/map/map.png");
        spriteSheet=new SpriteSheet("spritesheet.png");
        new Texture();
    }
    public synchronized void start(){

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
    player.tick();
    level.tick();
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
        player.render(g);
        level.render(g);
        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        requestFocus();
        int fps=0;
        double timer=System.currentTimeMillis();
        long lastTime=System.nanoTime();
        double targettick=60.0;
        double delta=0;
        double ns=1000000000/targettick;
        while ((isRunning)){
            long now=System.nanoTime();

            delta+=(lastTime)/ns;
            lastTime=now;
            while (delta>=1){
                tick();
                render();
                fps++;
                delta--;
            }

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
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.right=true;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) player.left=true;
        if (e.getKeyCode() == KeyEvent.VK_UP) player.up=true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) player.down=true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.right=false;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) player.left=false;
        if (e.getKeyCode() == KeyEvent.VK_UP) player.up=false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) player.down=false;
    }   @Override
    public void keyTyped(KeyEvent e) {

    }

}
