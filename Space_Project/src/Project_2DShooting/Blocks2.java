package Project_2DShooting;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Blocks2 extends Blocks{

	Blocks2(int x, int y) {
		super(x, y);
		
		try {
			img = ImageIO.read(new File("platform2.png"));
			width = 20;
			height = 20;
		} catch (IOException e) {
			System.out.println("Warning: gitkrakenSM.png failed to load");
		}
	}
}