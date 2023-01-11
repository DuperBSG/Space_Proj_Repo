package Project_2DShooting;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Blocks extends Rectangle{
	
	static ArrayList<Blocks> blcLi = new ArrayList<>();
	int x;
	int y;
	final int dim = 70;
	BufferedImage img;
	Color clr = Color.GREEN;
	
	Blocks (int x, int y) {
		height = 100;
		width = 100;
		this.x = x;
		this.y = y;
		height = 20;
		width = 20;
		
		
		try {
			img = ImageIO.read(new File("platform.png"));
		} catch (IOException e) {
			System.out.println("Warning: platform.png failed to load");
		}
		
		blcLi.add(this);
	}
	
	static void boost(Player_Ship sp) {
		sp.vx += 2;
		sp.vy += 2;
	}
	
	
}
