import java.awt.image.*;
public class Gentoo{
	private BufferedImage[] frame;
	private int currF;

	private long sT;
	private long delay;

	public Gentoo(){}
	
	public void setFrames(BufferedImage[] b){
		frame = b;
		if(currF>= frame.length){
			currF = 0;
		}
	}

	public setDelay(long d){
		delay = d;
	}

	public void update(){
		if(delay == -1){
			return;
		}
		long e = (System.nanoTime()- sT)/ 1000000;
		if(e>delay){
			currF++;
			sT = System.nanoTime();
		}
		if(currF == frame.length){
			currF = 0;
		}
	}

	public BufferedImage getI(){
		return frame[currF];
	}
}