package Project_2DShooting;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class Main_Control {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main_Control();
			}
		});
	}
	
	final static int PH = 700;
	final static int PW = 700;
	JFrame window;
	final Color bg = new Color(60, 100, 60);
	Player_Ship player = new Player_Ship(PH/2, PW/2); 
	Better_KeyListener bKeyL = new Better_KeyListener(); 
	Timer t = new Timer(10, new Tl1());
	
	
	Main_Control() {
		setFrame();
		Panel pnl = new Panel();
		window.add(pnl);
		
	}
	

	public void setFrame() {
		window = new JFrame("Shooting game");
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setSize(new Dimension(PW, PH));
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
	}
	
	class Panel extends JPanel{
		
		Panel() {
			this.setBackground(bg);
			this.addKeyListener(bKeyL);
			this.setFocusable(true);
			t.start();
		}
		
		
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
	        super.paintComponent(g2); 

	        if (player.img != null) {
				//g2.drawImage(player.img, player.x, player.y, null);
	        	g2.drawImage(player.img, player.x, player.y, player.dim, player.dim, null);
			} else {
				g2.setColor(player.clr);
				g2.fillRect(player.x,  player.y,  player.width, player.height);
			}
	        
	        this.repaint();
		}

	}
	
	class Tl1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		
		//move ship (assuming that a key has been pressed)		
		if (bKeyL.isKeyDown('A') || bKeyL.isKeyDown(37)) player.move('A');
		if (bKeyL.isKeyDown('W') || bKeyL.isKeyDown(38)) player.move('W');
		if (bKeyL.isKeyDown('D') || bKeyL.isKeyDown(39)) player.move('D');
		if (bKeyL.isKeyDown('S') || bKeyL.isKeyDown(40)) player.move('S');
		
		
		}
	}
}