import java.awt.*;
import java.awt.image.*;
//import javac.imageio.ImageIO;
//import java.io.File;

public class Player{
  //Bounds
	private double xP;
	private double yP;
	private double vX;
	private double vY;

  private boolean left;
  private boolean right;

  //jump/fall options
  private boolean jumping;
  private boolean falling;

  private double gravity;// gravity
  private double yMAX = 768-106; // maximum height
  private double jStart;
  private double stopS;

  private TileMap tM;

  private boolean tL;
  private boolean tR;
  private boolean bL;
  private boolean bR;

  /***private Gentoo gentoo;
  private BufferedImage[] leftW;
  private BufferedImage[] rightW;
  private BufferedImage[] wS;
  private boolean fL;**/

	public Player(TileMap tileM){
    tM = tileM;
    jStart = -15;
    gravity = 2;
	}

  public void setVelX(int q){
    vX = q;
  }

  public void setVelY(int q){
    vY = q;
  }

  public void setX(int q){
    xP = q;
  }

  public void setY(int q){
    yP = q;
  }

  public void setJump(boolean b){
    if(b == true){
      falling = false;
      jumping = true;
    }
  }

  public void update(){
    xP += vX;
    yP += vY;
    if (xP<=0){
      xP=0;
    }
    else if (xP>=1024-134) {
      xP=1024-134;
    }
    if (yP<=0){
      yP = yMAX;
    }
    else if (yP>=yMAX) {
      yP=yMAX;
    }
    if(jumping){
      vY = jStart;
      falling = true;
      jumping = false;
    }
    if(falling == true){
      vY += gravity;
      if(vY>yMAX){
        vY = yMAX;
      }
    }
  }

	public void paintComponent(Graphics2D g){
    //character
    int tX = tM.getX();
    int tY = tM.getY();

    g.setColor(Color.BLUE);
    g.fillRect((int)(tX + xP  - 20/2),(int)(tY + yP  - 20/2),20,20);
  }
}