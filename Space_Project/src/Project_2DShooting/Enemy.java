package Project_2DShooting;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy extends Player_Ship{

	Enemy(int x, int y) {
		super(x, y);

			width = 20;
			height = 20;
			
			xx = x;
			yy = y;
			
			this.x = x;
			this.y = y;
			
			try {
				img = ImageIO.read(new File("Enemy.png"));
				width = 20;
				height = 20;
			} catch (IOException e) {
				System.out.println("Warning: gitkrakenSM.png failed to load");
			}
			
		}
	}

}
