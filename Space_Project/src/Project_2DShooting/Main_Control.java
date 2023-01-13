package Project_2DShooting;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import spaceGame.Laser;


public class Main_Control {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main_Control();
			}
		});
	}
	
	final static int PH = 700;
	final static int PW = 980;
	JFrame window;
	final Color bg = new Color(50, 80, 60);
	Player_Ship player = new Player_Ship(100, 400); 
	Enemy enemy = new Enemy(900, 400); 
	Gun gun = new Gun(); 
	Better_KeyListener bKeyL = new Better_KeyListener(); 
	Timer t = new Timer(10, new Tl1());
	ArrayList<Laser> laserList = new ArrayList<Laser>();

	
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
		for (int i = 0; i < PW; i += 70) {
			Blocks.blcLi.add(new Blocks(i, 560));
		}
	}
	public void setGround2() {
		for (int i = 0; i < PW; i += 70) {
			Blocks2.blcLi.add(new Blocks2(i, 630));
		}
	}

	public void setFrame() {
		window = new JFrame("Shooting game");
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	boolean right = true;
	boolean eRight = true;
	
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

	        if (right)  g2.drawImage(player.img, player.x, player.y, player.dim, player.dim, null);
	        else g2.drawImage(player.img, player.x + 70, player.y, -player.dim, player.dim, null);
	      
	        if (right) g2.drawImage(gun.img, player.x, player.y, gun.dim, gun.dim, null);
	        else g2.drawImage(gun.img, player.x + 70, player.y, -gun.dim, gun.dim, null);

	        if (eRight) g2.drawImage(enemy.img, enemy.x, enemy.y, enemy.dim, enemy.dim, null);
	        else g2.drawImage(enemy.img, enemy.x + 70, enemy.y, -enemy.dim, enemy.dim, null);
	        
	       
	        for (Blocks it : Blocks.blcLi) {    
	        
		        if (it.img != null) {
					//g2.drawImage(player.img, player.x, player.y, null);
		        	g2.drawImage(it.img, it.x, it.y, it.dim, it.dim, null);
				} else {
					g2.setColor(it.clr);
					g2.fillRect(it.x,  it.y,  it.width, it.height);
				} 
	        }
	   
	        g2.setColor(Color.green);
	        Font f2 = new Font("Arial", Font.CENTER_BASELINE, 40);
	        g2.setFont(f2);
	       
	        if (player.health != 0) {
	 	        g2.drawString("" + player.health, 100, 100);
	        }
	        else { 
	        	g2.drawString("Game Over", 100, 100);
	        	t.stop();
	        }
	        
	        this.repaint();
	        
	        
	        g.setColor(Laser.clr);
			for (Laser laser : laserList) {
				g.fillRect(laser.x,  laser.y, laser.width, laser.height);
			}
			
		}

	}
	
	boolean intersect() {
		for (Blocks it : Blocks.blcLi) {    
			if (player.intersects(it)) {
				//System.out.println("YES");
				return true;
			}
		}
		return false;
	}

	
	class Tl1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	
			if (player.vy >= -90) {
				player.vy -= 1;
			}
			
			if (enemy.vy >= -90) {
				enemy.vy -= 1;
			}
			
			if (enemy.y >= 460) enemy.vy = 0;
	
			
			if (player.y >= 460) {
				player.vy = 0;
				if (bKeyL.isKeyDown('W')) player.move('W');
			}

			if (bKeyL.isKeyDown('A')) {
				player.move('A');
				right = false;
			}
			
			if (bKeyL.isKeyDown('D')) {
				player.move('D');
				right = true;
			}
			
			if (bKeyL.isKeyDown(37)) {
				enemy.move('A');
				eRight = false;
			}
			if (bKeyL.isKeyDown(39)) {
				enemy.move('D');
				eRight = true;
			}
			if (enemy.y >= 460) {
				enemy.vy = 0;
				if (bKeyL.isKeyDown(38)) enemy.move('W');
			}
			
			player.move('F');
			enemy.move('F');
			
			
		}
	}
}