package Project_2DShooting;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/* This will just be moving vertically (initially)
 */
class Laser extends Rectangle{
	
	static ArrayList<Laser> laserList = new ArrayList<Laser>();
	BufferedImage img;
	int dimX = 100;
	int dimY = 50;
	
	
	//private static long lastShot = System.currentTimeMillis();
	
	//double precision not used in this class.
	private int dist = 0;
	int velo = 10;
	double vx = 0;
	double vy = 0;
	int damage = 20;
	double ang;
	
	Laser(int x, int y, double angle) {
		height = 50;
		width = 80;
		height = 10;
		this.x = x;
		this.y = y;
		ang = Math.abs(angle);
		vx = (Math.cos(Math.toRadians(ang)) * (velo));
		vy = (Math.sin(Math.toRadians(ang))* (velo));
		//System.out.println(ang + "  " + vx + "   " + vy);
		
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