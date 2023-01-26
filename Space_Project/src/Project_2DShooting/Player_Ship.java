package Project_2DShooting;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Player_Ship extends Rectangle {

	double xx, yy;  //double versions of x,y for precise moving
	double vx = 7;
	double vy = 0;
	int health = 100;
	int dim = 100; //dimension
	BufferedImage img;
	
	Player_Ship (int x, int y) {
		height = 60;
		width = 60;
		xx = x;
		yy = y;
		
		this.x = x;
		this.y = y;
		
		try {
			img = ImageIO.read(new File("navigator.png"));
		} catch (IOException e) {
			System.out.println("Warning: navigator.png failed to load");
		}
		

	}
	
	
	//move method to update cordinate
	public void move (char key) {
		switch (key) {
		case 'W':	 
			vy = 20;
			break;
		case 'S':
			if (yy < Main_Control.PH) yy += vy; 
			break;
		case 'A':
			if (xx > 22) xx -=vx; 
			break;
		case 'D':
			if (xx < Main_Control.PW - 90) xx +=vx; 
			break;
		case 'F':
			if (yy < Main_Control.PH) yy -= vy; 
			break;
		}
		//update final positions
		x = (int)xx;
		y = (int)yy;
	}
	
	
}
