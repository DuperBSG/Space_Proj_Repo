package Project_2DShooting;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy extends Player_Ship{

	
	Enemy(int x, int y) {
		super(x, y);
		height = 100;
		width = 100;
		xx = x;
		yy = y;
	
		this.x = x;
		this.y = y;
		
		try {
			img = ImageIO.read(new File("enemy.png"));
		} catch (IOException e) {
			System.out.println("Warning: enemy.png failed to load");
		}
	}
		
	
	
	}