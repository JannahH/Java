import java.io.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class TileMap{
	private int xM;
	private int yM;

	private int size;
	private int[][] map;

	private int wM;
	private int hM;

	private BufferedImage tileset;
	private Tile[][] tiles;

	public TileMap(String s, int size){
		this.size = size;
		try{
			BufferedReader b = new BufferedReader(new FileReader(s));
			
			wM = Integer.parseInt(b.readLine());
			hM = Integer.parseInt(b.readLine());
			map = new int[hM][wM];

			String space = "\\s+";
			for(int r = 0; r<hM; r++){
				String line = b.readLine();
				String[] a = line.split(space);
				for(int c = 0; c<wM; c++){
					map[r][c] = Integer.parseInt(a[c]);
				}
			}
		}
		catch(Exception e){}
	}

	public void loadTiles(String s){
		try{
			tileset = ImageIO.read(new File(s));
			int numTilesAcross = (tileset.getWidth() + 1) / (size + 1);
			tiles = new Tile[2][numTilesAcross];
			BufferedImage subimage;

			for(int col = 0; col < numTilesAcross; col++){
				subimage = tileset.getSubimage(
					col * size + col,
					0,
					size,
					size);
				tiles[0][col] = new Tile(subimage, false);
				subimage = tileset.getSubimage(
					col * size + col,
					size + 1,
					size,
					size);
				tiles[1][col] = new Tile(subimage, true);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void update(){
  		
  	}

  	public int getX(){
  		return xM;
  	}

  	public int getY(){
  		return yM;
  	}

  	public void setX(int q){
  		xM = q;
  	}

  	public void setY(int q){
  		yM = q;
  	}

  	public int getC(int x){
  		x = x/size;
  		return x;
  	}

  	public int getR(int y){
  		y = y/size;
  		return y;
  	}

  	public int getT(int q, int w){
  		return map[q][w];
  	}

  	public int getSize(){
  		return size;
  	}

  	public boolean isBlocked(int row, int col){
		int rc = map[row][col];
		int rr = rc / tiles[0].length;
		int cc = rc % tiles[0].length;
		return tiles[rr][cc].isBlocked();
	}

	public void paintComponent(Graphics2D g){

  		for(int r = 0; r<hM; r++){
			for(int c = 0; c<wM; c++){
				int rc = map[r][c];
				int rr = rc / tiles[0].length;
				int cc = rc % tiles[0].length;
				g.drawImage(
					tiles[rr][cc].getImage(),
					xM + c * size,
					yM + r * size,
					null);
			
			}
		}
 	}
}