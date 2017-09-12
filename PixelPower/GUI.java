import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{

	public static void game(){

      JFrame frame1 = new JFrame("Pixel Power");// set new frame
	  frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//command to exit frame
	  frame1.setContentPane(new Controller());
	  frame1.pack();
	  frame1.setVisible(true);// frame is now visible
	  frame1.setResizable(false);
	}

	public static void main(String[] args){		

	  JFrame frame = new JFrame("Pixel Power");// set new frame

	  JLabel gameTitle = new JLabel(new ImageIcon("Resources/Main Menu Title.bmp"));

	  ImageIcon start = new ImageIcon("Resources/Start Button.bmp");
	  JButton startButton = new JButton(start);
	  startButton.setBorder(BorderFactory.createEmptyBorder());
	  startButton.setContentAreaFilled(false);
	  startButton.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e) {
        	//frame.setVisible(false); 
        	frame.dispose();
        	game();
        	//add your elements
			//Controller c = new Controller();
	 		//frame.add(c);
    	 }
	  });

	  ImageIcon quit = new ImageIcon("Resources/Quit Button.bmp");
	  JButton quitButton = new JButton(quit);
	  quitButton.setBorder(BorderFactory.createEmptyBorder());
	  quitButton.setContentAreaFilled(false);
	  quitButton.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent e) {
	      System.exit(0);
	     }
	  });

	  //sets background picture
	  JPanel north = new JPanel(); // makes a new panel
	  north.setLayout(new BorderLayout());
      north.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); //sets border 
      
      JLabel menuBackground = new JLabel(new ImageIcon("Resources/Main Menu Background.bmp"));
	  north.add(menuBackground);
	  menuBackground.setLayout(new GridLayout(5,1));
	  menuBackground.add(gameTitle);
	  menuBackground.add( new JPanel(){
	    	{          		    
	         add( new JLabel(" "));
	         setBackground(new Color(0,0,0,0));
	        }
	   } , BorderLayout.CENTER );

	  //adds buttonto the panel
	  menuBackground.add(startButton); 
	  menuBackground.add(quitButton);

	  frame.add(north, BorderLayout.SOUTH); //adds panel to the frame 

	  
	  frame.setSize(1024,768);// frame size
	  frame.setResizable(false);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//command to exit frame
	  frame.setVisible(true);// frame is now visible
	  


	}
/*
 	public static void main( String[] args){
 		mainMenue();
	}*/
}