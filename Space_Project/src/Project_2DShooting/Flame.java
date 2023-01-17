package Project_2DShooting;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Flame extends Laser {
	static ArrayList <Flame> flameList = new ArrayList <> ();
	int damage = 20;
	
	Flame(int x, int y, double angle) {
		super(x, y, angle);

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
			img = ImageIO.read(new File("explosion.png"));
		} catch (IOException e) {
			System.out.println("Warning: flame.png failed to load");
		}
		
	}

}