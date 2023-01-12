package Project_2DShooting;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Gun {

	int dim = 90;
	BufferedImage img;
	Gun() {

		try {
			img = ImageIO.read(new File("lighter.png"));
		} catch (IOException e) {
			System.out.println("Warning: lighter.png failed to load");
		}
	}

}
