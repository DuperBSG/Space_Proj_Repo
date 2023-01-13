package Project_2DShooting;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

/* This will just be moving vertically (initially)
 */
class Laser extends Rectangle{
	
	static ArrayList<Laser> laserList = new ArrayList<Laser>();
	static final int MAXSHOT = 15;
	static final int SHOTDELAY = 300;  //milliseconds
	static Color clr = Color.YELLOW;
	static final int RANGE = 500;  //how many pixels the laser will go. To make it interesting
	
	private static long lastShot = System.currentTimeMillis();
	
	//double precision not used in this class.
	private int dist = 0;
	int vx = 0;
	int vy = -3;
	int damage = 20;
	
	Laser(int x, int y) {
		width = 2;
		height = 10;
		this.x = x;
		this.y = y;
	}
	
	static boolean canShoot() {
		if (System.currentTimeMillis() - lastShot > Laser.SHOTDELAY) {			
			lastShot = System.currentTimeMillis();
			return true;
		}
		return false;
	}
	
//	static long getLastShot() { return lastShot; }
//	static void setLastShot() { lastShot = System.currentTimeMillis(); }
	
	boolean move() {
		x += vx;
		y += vy;
		
		dist += Math.abs(vy);
		if (dist > RANGE) return false;
		
		//Standard wrapping code
		if (x < 0 - width) x = Main_Control.PW;
		if (y < 0 - height) y = Main_Control.PH;
		if (x > Main_Control.PW) x = 0;
		if (y > Main_Control.PW) y = 0;
		
		return true;
	}
}