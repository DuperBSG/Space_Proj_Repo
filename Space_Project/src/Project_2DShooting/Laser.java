package Project_2DShooting;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Laser extends Rectangle{
	static ArrayList<Laser> lasLi = new ArrayList<>();
	int dimX = 100;
	int dimY = 50;
	BufferedImage img;
	double xx, yy; 
	
	Laser(int x, int y) {
		xx = x;
		yy = y;
		
		this.x = x;
		this.y = y;
		
		try {
			img = ImageIO.read(new File("bullet.png"));
		} catch (IOException e) {
			System.out.println("Warning: navigator.png failed to load");
		}
		//lasLi.add(this);
	}
	
	
}
