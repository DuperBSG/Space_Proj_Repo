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
	double vy = 5;
	int health = 100;
	int dim = 70;
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
			img = ImageIO.read(new File("PlayerShip.png"));
			width = 1;
			height = 1;
		} catch (IOException e) {
			System.out.println("Warning: gitkrakenSM.png failed to load");
		}
		
	}
	
	void move (int key) {
		switch (key) {
		case 'W':		
			yy -=vy; break;
		case 'S':
			yy +=vy; break;
		case 'A':
			xx -=vx; break;
		case 'D':
			xx +=vx; break;
		}
		// wrap around on the screen
		if (xx < 0 - width) xx = Main_Control.PW;
		if (yy < 0 - height) yy = Main_Control.PH;
		if (xx > Main_Control.PH) xx = 0;
		if (yy > Main_Control.PW) yy = 0;
		
		//update final positions
		x = (int)xx;
		y = (int)yy;
	}
	
}
