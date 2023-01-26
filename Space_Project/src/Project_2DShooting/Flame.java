package Project_2DShooting;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Flame extends Laser {
	static ArrayList <Flame> flameList = new ArrayList <> ();
	
	Flame(int x, int y, double angle) {
		super(x, y, angle);

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
			img = ImageIO.read(new File("explosion.png"));
		} catch (IOException e) {
			System.out.println("Warning: flame.png failed to load");
		}
	}

}
