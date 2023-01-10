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
	final Color bg = new Color(50, 80, 60);
	Player_Ship player = new Player_Ship(PH/2, PW/2); 
	Better_KeyListener bKeyL = new Better_KeyListener(); 
	Timer t = new Timer(10, new Tl1());
	
	
	Main_Control() {
		setFrame();
		Panel pnl = new Panel();
		window.add(pnl);
		window.pack();
		setGround();
		setGround2();
		//window.setUndecorated(true);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		
		
	}
	
	public void setGround() {
		for (int i = 0; i < 700; i += 70) {
			Blocks.blcLi.add(new Blocks(i, 560));
		}
	}
	public void setGround2() {
		for (int i = 0; i < 700; i += 70) {
			Blocks2.blcLi.add(new Blocks2(i, 630));
		}
	}

	public void setFrame() {
		window = new JFrame("Shooting game");
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
		
		
		
	}
	
	class Panel extends JPanel{
		
		Panel() {
			this.setBackground(bg);
			this.addKeyListener(bKeyL);
			this.setFocusable(true);
			this.setPreferredSize(new Dimension(PW, PH));
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
	        
	       
	        for (Blocks it : Blocks.blcLi) {    
	        
		        if (it.img != null) {
					//g2.drawImage(player.img, player.x, player.y, null);
		        	g2.drawImage(it.img, it.x, it.y, it.dim, it.dim, null);
				} else {
					g2.setColor(it.clr);
					g2.fillRect(it.x,  it.y,  it.width, it.height);
				} 
	        }
	   
	        
	        
	        this.repaint();
		}

	}
	
	
	int time = 0;
	
	
	class Tl1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	
			
			if (player.vy >= -90) {
				player.vy -= 1;
				System.out.println(player.vy);
			}
		
			if (player.y >= 460) {
				player.vy = 0;
				if (bKeyL.isKeyDown('W') || bKeyL.isKeyDown(38) ) player.move('W');
			}
			//move ship (assuming that a key has been pressed)		
			if (bKeyL.isKeyDown('A') || bKeyL.isKeyDown(37)) player.move('A');
			
			if (bKeyL.isKeyDown('D') || bKeyL.isKeyDown(39)) player.move('D');
			//if (bKeyL.isKeyDown('S') || bKeyL.isKeyDown(40)) player.move('S');
			//if (bKeyL.isKeyDown('W') || bKeyL.isKeyDown(38) ) player.move('W');
		
			
			//if (player.y < 460) {
				player.move('F');
			//}
			
		}
	}
}