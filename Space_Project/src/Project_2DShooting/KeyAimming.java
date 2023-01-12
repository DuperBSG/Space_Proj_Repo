package Project_2DShooting;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class KeyAimming extends JFrame implements KeyListener{ 

	int gunPos1 = 0, gunPos2 = 180;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyChar()) {
		case 'g': 
			if(gunPos1 >=180) {
				gunPos1 = 180;
			}else {
				gunPos1 += 5;
			}
			break;
		case 'h':
			if(gunPos1 <=0) {
				gunPos1 = 0;
			}else {
				gunPos1 -= 5;
			}
			break;
		case ',':
			if(gunPos2 >=180) {
				gunPos2 = 180;
			}else {
				gunPos2 += 5;
			}
			break;
		case '.':
			if(gunPos2 <=0) {
				gunPos2 = 0;
			}else {
				gunPos2 -= 5;
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	};
	
	
}
