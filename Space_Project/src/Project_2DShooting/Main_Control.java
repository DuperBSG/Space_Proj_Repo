//The Zhangs (Bowei, Michael Jessica)
//this is a two player game where players can move/jump and shoot at each other
//player who survive wins
package Project_2DShooting;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.ArrayList;
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
	final static int CRATE = 1;
	final static int GROUND = 2;
	final static int PH = 700;
	final static int PW = 980;
	final int GROUNDLVL = 460;
	final Color bg = new Color(50, 80, 60);
	static double gunAng1 = 0.0;
	static double gunAng2 = 180.0;
	boolean right = true;
	boolean eRight = false;
	int coolDown = 0;
	int coolDown2 = 0;
	JFrame window;
	Player_Ship player = new Player_Ship(100, 400); 
	Enemy enemy = new Enemy(800, 400); 
	Gun gun = new Gun(); 
	Gun2 gun2 = new Gun2();
	Better_KeyListener bKeyL = new Better_KeyListener(); 
	Timer t = new Timer(10, new Tl1());
	Panel pnl = new Panel();
	AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(gunAng1), player.x, player.y);
	AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	AffineTransform at = AffineTransform.getTranslateInstance(player.x, player.y);

	
	
	Main_Control() {
		setFrame();
		window.add(pnl);
		window.pack();
		setGround();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	int[][] blocks = new int[10][14];

	public void setGround () {
		//build the blocks (both ground and crate)
		for (int col = 0; col < 14; col++) {
			blocks[8][col] = CRATE; 
			blocks[9][col] = GROUND;
			//blocks[2][col] = CRATE; 
		}
		/*
		for (int col = 4; col <= 9; col ++) {
			blocks[7][col] = CRATE;
		}

		for (int col = 5; col <= 8; col ++) {
			blocks[6][col] = CRATE;

		}

		blocks[5][6] = CRATE;
		blocks[5][7] = CRATE;
		*/

		//adds the block to the list
		for (int row = 9; row >= 0; row--) {
			for (int col = 13; col >= 0; col--) {
				if (blocks[row][col] == CRATE) {
					Blocks.blcLi.add(new Blocks(col * Blocks.dim, row * Blocks.dim));
				}
				if(blocks[row][col] == GROUND) {
					Blocks2.blcLi.add(new Blocks2(col * Blocks2.dim, row * Blocks2.dim));

				}
			}
		}
	}

	
	
	public void setFrame() {
		window = new JFrame("Shooting game");
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			at.rotate(Math.toRadians(gunAng1), player.x/2, player.y);
			super.paintComponent(g2); 
			AffineTransform old = g2.getTransform();

			//entire block of code draws player and the gun
			if (right) g2.drawImage(player.img, player.x, player.y, player.dim, player.dim, null);
			else g2.drawImage(player.img, player.x +100, player.y, -player.dim, player.dim, null);

			g2.rotate(Math.toRadians(gunAng1), player.x + 50, player.y + 50);
			g2.drawImage(gun.img, player.x, player.y, gun.dim, gun.dim, null);
			g2.setTransform(old);

			if (eRight) g2.drawImage(enemy.img, enemy.x, enemy.y, enemy.dim, enemy.dim, null);
			else g2.drawImage(enemy.img, enemy.x + 100, enemy.y, -enemy.dim, enemy.dim, null);

			g2.rotate(Math.toRadians(gunAng2), enemy.x + 50, enemy.y + 50);
        	g2.drawImage(gun2.img, enemy.x, enemy.y, gun2.dim, gun2.dim, null);
        	g2.setTransform(old);
        	
        	
        	
        	//drawings bullet on the screen
        	for (Laser laser : Laser.laserList) g2.drawImage(laser.img, laser.x, laser.y, laser.dimX, laser.dimY, null);
        	for (Flame flame : Flame.flameList) g2.drawImage(flame.img, flame.x, flame.y, flame.dimX, flame.dimY, null);

        	//draws blocks on screen
			for (Blocks it : Blocks.blcLi) g2.drawImage(it.img, it.x, it.y, it.dim, it.dim, null);

			
			
			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(3));

			//set up x, y coordinates and height of the health bars
			int playerBarX = PW/98*10;
			int enemyBarX = PW/98*80;
			int barY = PH/7;
			int barH = 20;
			
			//draw the outline of the health bars
			g2.drawRect(playerBarX, barY, 100, barH);
			g2.drawRect(enemyBarX, barY, 100, barH);
			
			Font f2 = new Font("Arial", Font.CENTER_BASELINE, 15);
			Font f3 = new Font("Arial", Font.CENTER_BASELINE, 40);


			//create a health bar for the player
			g2.setColor(Color.green);
			if (player.health != 0) {
				g2.setFont(f2);
				g2.fillRect(playerBarX, barY, player.health-(1/20),barH);
				g2.drawString("" + player.health, playerBarX, barY);

			}
			else { 
				g2.setFont(f3);
				g2.drawString("Game Over", PW/2-80, PH/7);
				t.stop();
			}

			//create a health bar for the enemy
			g2.setColor(Color.red);
			if (enemy.health != 0) {
				g2.setFont(f2);
				g2.fillRect(enemyBarX, barY, enemy.health-(1/20), barH);
				g2.drawString("" + enemy.health, enemyBarX, barY);

			}
			else { 
				g2.setFont(f3);
				g2.drawString("Game Over", PW/2-80, barY);
				t.stop();
			}

		}

	}


	
	class Tl1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			
			//vertical velocity decreases (gravity)
			player.vy -= 1;
			enemy.vy -= 1;
			
			//gun angle adjustments	
			if (bKeyL.isKeyDown('H')) {
				if(gunAng1 >=0) {
					gunAng1 = 0;
				}else {
					gunAng1 += 5;
				}
				System.out.println(gunAng1);
			}
			if (bKeyL.isKeyDown('G')) {
				if(gunAng1 <=-180) {
					gunAng1 = -180;
				}else {
					gunAng1 -= 5;
				}
				System.out.println(gunAng1);
			}
			if (bKeyL.isKeyDown('.')) {
				if(gunAng2 >= 0) {
					gunAng2 = 0;
				}else {
					gunAng2 += 5;
				}
			}
			if (bKeyL.isKeyDown(',')) {
				if(gunAng2 <= -180) {
					gunAng2 = -180;
				}else {
					gunAng2 -= 5;
				}
			}

			
			
			//the player/enemy stops at this point, so they do not fall through ground
			if (player.y >= GROUNDLVL) {
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
			if (enemy.y >= GROUNDLVL) {
				enemy.vy = 0;
				if (bKeyL.isKeyDown(38)) enemy.move('W');
			}
			if (bKeyL.isKeyDown(37)) {
				enemy.move('A');
				eRight = false;
			}
			if (bKeyL.isKeyDown(39)) {
				enemy.move('D');
				eRight = true;
			}
			
			
			
			//shooting code, gun shoots when button pressed
			if (bKeyL.isKeyDown(' ') && coolDown < 0) {
				Laser.laserList.add(new Laser(player.x, player.y +20, gunAng1));
				coolDown = 30;
			}
			if (bKeyL.isKeyDown('M') && coolDown2 < 0) {
				Flame.flameList.add(new Flame(enemy.x, enemy.y +20, gunAng2));
				coolDown2 = 30;
			}

			
			
			//check if bullet hits either player or enemy, (they cannot take damage from their own bullet
			for (int i = 0; i < Laser.laserList.size(); i++) {
				Laser.laserList.get(i).move();
				if (Laser.laserList.get(i).intersects(enemy)) {
					enemy.health -= 5;
					Laser.laserList.remove(i);
				}
			}
			for (int i = 0; i < Flame.flameList.size(); i++) {
				Flame.flameList.get(i).move();
				if (Flame.flameList.get(i).intersects(player)) {
					player.health -= 5;
					Flame.flameList.remove(i);
				}
			}
			
			
			
			//update variables (gravity and cool down)
			player.move('F');
			enemy.move('F');
			coolDown--;
			coolDown2--;
			pnl.repaint();
		}
	}
}