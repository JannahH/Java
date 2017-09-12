import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class Controller extends JPanel implements Runnable,KeyListener{

  public static final int w = 1024;
  public static final int h = 768;

  private boolean running;
  private Thread thread;

  private BufferedImage pic;
  private Graphics2D gra;

  private int FPS = 30;
  private int tT = 1000/FPS;

  private TileMap tM;
  private Player play;

	public Controller(){
	  super();
    setPreferredSize(new Dimension (w,h));
    setFocusable(true);
    requestFocus();
	}

  public void addNotify(){
    super.addNotify();
    if(thread == null){
      thread = new Thread(this);
      thread.start();
    }
    addKeyListener(this);
  }

  public void run(){
    init();
  
    long sTime;
    long urdTime;
    long wTime;

    while(running){
      sTime = System.nanoTime();

      update();
      render();
      draw();

      urdTime = (System.nanoTime()- sTime)/1000000;
      wTime = tT - urdTime;

      try{
        Thread.sleep(wTime);
      }
      catch(Exception e){}
    }
  }

  public void init(){
    running = true;
    pic = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    gra = (Graphics2D)pic.getGraphics();
    tM = new TileMap("testmap2.txt",32);
    tM.loadTiles("Resources/tileset.gif");
    play = new Player(tM);
    play.setY(768-106);
    play.setX(10);
  }

  public void update(){
    tM.update();
    play.update();
  }

  public void render(){
    gra.setColor(Color.BLACK);
    gra.fillRect(0, 0, WIDTH, HEIGHT);
    tM.paintComponent(gra);
    play.paintComponent(gra);
  }


  public boolean getRunning(){
    return true;
  }

  public void draw(){
    Graphics g = getGraphics();
    g.drawImage(pic, 0, 0, null);
    g.dispose();
  }

  public void keyPressed(KeyEvent e){
    int code = e.getKeyCode();

    if(code == KeyEvent.VK_UP){
      play.setJump(true);
    }

    if(code == KeyEvent.VK_RIGHT){
      play.setVelX(5);
    }

    if(code == KeyEvent.VK_LEFT){
      play.setVelX(-5);
    }
  }

  public void keyTyped(KeyEvent e){}

  public void keyReleased(KeyEvent e){
    int code = e.getKeyCode();
    if(code == KeyEvent.VK_RIGHT){
      play.setVelX(0);
    }

    if(code == KeyEvent.VK_LEFT){
      play.setVelX(0);
    }
  }

}