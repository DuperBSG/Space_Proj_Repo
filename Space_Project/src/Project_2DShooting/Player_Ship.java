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
	double velo = 0;
	int health = 100;
	final int dim = 100;
	BufferedImage img;
	Color clr = Color.GREEN;  //TODO: make the enemy one a different colour
	
	Player_Ship (int x, int y) {
		width = 20;
		height = 20;
		
		xx = x;
		yy = y;
		
		this.x = x;
		this.y = y;
		
		try {
			img = ImageIO.read(new File("navigator.png"));
			width = 20;
			height = 20;
		} catch (IOException e) {
			System.out.println("Warning: gitkrakenSM.png failed to load");
		}
		
	}
	
	public void move (int key, int t) {
		switch (key) {
		case 'W':		
			if (yy > 5) yy -= vy; 
			if (yy < 600) {
				vy = 0;
			}
			break;
		case 'S':
			if (yy < Main_Control.PW - 110) yy +=vy; 
			break;
		case 'A':
			if (xx > 5) xx -=vx; 
			break;
		case 'D':
			if (xx < Main_Control.PH - 90) xx +=vx; 
			break;
		case 'F':
			if (yy < Main_Control.PW - 110) yy += 9.8; 
			break;
		}
		// wrap around on the screen
		/*
		if (xx < 0 - width) 
		if (yy < 0 - height) 
		if (xx > Main_Control.PH) xx = 0;
		if (yy > Main_Control.PW) yy = 0;
		*/
		//update final positions
		x = (int)xx;
		y = (int)yy;
	}
	
}
