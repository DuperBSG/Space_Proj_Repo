package Project_2DShooting;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player_Ship extends Rectangle {

	double xx, yy;  //double versions of x,y for precise moving
	double vx = 5;
	double vy = 0;
	int health = 100;
	int dim = 100;
	BufferedImage img;
	Color clr = Color.GREEN;  //TODO: make the enemy one a different colour
	
	Player_Ship (int x, int y) {
		
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
	
	public void move (int key) {
		switch (key) {
		case 'W':	 
			vy = 15;
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
