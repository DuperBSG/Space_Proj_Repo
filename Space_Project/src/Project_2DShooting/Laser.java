package Project_2DShooting;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

class Laser extends Rectangle{
	
	static ArrayList<Laser> laserList = new ArrayList<Laser>();
	BufferedImage img;
	int dimX = 50;
	int dimY = 50;
	int velo = 10;
	double vx = 0;
	double vy = 0;
	double ang;
	
	Laser(int x, int y, double angle) {
		height = 50;
		width = 50;
		height = 10;
		this.x = x;
		this.y = y;
		ang = Math.abs(angle);
		//find out the vertical/horizontal velocity components
		vx = (Math.cos(Math.toRadians(ang)) * (velo));
		vy = (Math.sin(Math.toRadians(ang))* (velo));
	
		try {
			img = ImageIO.read(new File("bullet.png"));
		} catch (IOException e) {
			System.out.println("Warning: bullet.png failed to load");
		}
		
	}
	
	void move() {
		x += vx;
		y -= vy;
	}
}