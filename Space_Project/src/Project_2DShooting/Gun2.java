package Project_2DShooting;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Gun2 extends Gun{
	int dim = 90;
	BufferedImage img;
	Gun2() {

		try {
			img = ImageIO.read(new File("flamegun.png"));
		} catch (IOException e) {
			System.out.println("Warning: flamegun.png failed to load");
		}
	}
	
	
}
