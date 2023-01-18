package Project_2DShooting;
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
	
	final static int PH = 700;
	final static int PW = 980;
	static double gunAng1 = 0.0;
	static double gunAng2 = 180.0;
	JFrame window;
	final Color bg = new Color(50, 80, 60);
	Player_Ship player = new Player_Ship(100, 400); 
	Enemy enemy = new Enemy(900, 400); 
	Gun gun = new Gun(); 
	Gun2 gun2 = new Gun2();
	Better_KeyListener bKeyL = new Better_KeyListener(); 
	Timer t = new Timer(10, new Tl1());
	Panel pnl;
	
	AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(gunAng1), player.x, player.y);
	//AffineTransform tx1 = AffineTransform.getRotateInstance(Math.toRadians(gunAng2), enemy.x, enemy.y);

    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	AffineTransform at = AffineTransform.getTranslateInstance(player.x, player.y);
	
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
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	boolean right = true;
	boolean eRight = false;
	
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
	        
	        if (right) {
	        	g2.drawImage(player.img, player.x, player.y, player.dim, player.dim, null);
	        	//g2.fillRect(player.x, player.y, player.dim, player.dim);
	        }
	        else {
	        	g2.drawImage(player.img, player.x +100, player.y, -player.dim, player.dim, null);
	        	//g2.fillRect(player.x + 70, player.y, -player.dim, player.dim);
	        }
	        
	       
	
	        
	        
	        
	        AffineTransform old = g2.getTransform();
	        
	       // if (right) {
	        	g2.rotate(Math.toRadians(gunAng1), player.x + 50, player.y + 50);
	        	g2.drawImage(gun.img, player.x, player.y, gun.dim, gun.dim, null);
	
	        	
	    
	       // }
	       // else {
//	        	g2.rotate(Math.toRadians(gunAng1), player.x + 50, player.y + 50);
//	        	g2.drawImage(gun.img, player.x, player.y, gun.dim, gun.dim, null);
	        //}
	        
	        
	        
	        
	        g2.setTransform(old);
	        
	        AffineTransform laz = g2.getTransform();
	        
	        for (Laser laser : Laser.laserList) {
	        	g2.rotate(Math.toRadians(gunAng1), player.x, player.y);
				g2.drawImage(laser.img, laser.x, laser.y, laser.dimX, laser.dimY, null);
				g2.setTransform(old);
				
			}
	        g2.setTransform(laz);
	        
	        AffineTransform flam = g2.getTransform();
	        for (Flame flame : Flame.flameList) {
//	        	g2.rotate(Math.toRadians(gunAng2), player.x, player.y);
				g2.drawImage(flame.img, flame.x, flame.y, flame.dimX, flame.dimY, null);
//				g2.setTransform(old);
			}
	        g2.setTransform(flam);
	        
	         
	        if (eRight) g2.drawImage(enemy.img, enemy.x, enemy.y, enemy.dim, enemy.dim, null);
	        else g2.drawImage(enemy.img, enemy.x + 100, enemy.y, -enemy.dim, enemy.dim, null);
	        
	        //g2.rotate(Math.toRadians(gunAng2), enemy.x + 50, enemy.y + 50);
	       
	        g2.rotate(Math.toRadians(gunAng2), enemy.x + 50, enemy.y + 50);
        	g2.drawImage(gun2.img, enemy.x, enemy.y, gun2.dim, gun2.dim, null);
        	g2.setTransform(old);
        	
	        //g2.drawImage(laser.img, laser.x + 70, laser.y, laser.dimX, laser.dimY, null);
	        
	        
	       
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
	       
	        if (player.health != 0 && enemy.health != 0) {
	 	        g2.drawString("" + player.health, 100, 100);
	 	        g2.drawString("" + enemy.health, 800, 100);
	        }else if(player.health != 0){ 
	        	g2.drawString("Game Over: Red Player Wins", 100, 100);
	        	t.stop();
	        }else if(enemy.health != 0) {
	        	g2.drawString("Game Over: Green Player Wins", 100, 100);
	        	t.stop();
	        }
		        
			this.repaint();
		
		}

	}

	
	int coolDown = 0;
	int coolDown2 = 0;
	class Tl1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	
			if (player.vy >= -90) {
				player.vy -= 1;
			}	
			if (enemy.vy >= -90) {
				enemy.vy -= 1;
			}
		
	
	
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
			
			if (bKeyL.isKeyDown(',')) {
				if(gunAng2 >=0) {
					gunAng2 = 0;
				}else {
					gunAng2 += 5;
				}
				System.out.println(gunAng1);
			}
			
			if (bKeyL.isKeyDown('.')) {
				if(gunAng2 <=-180) {
					gunAng2 = -180;
				}else {
					gunAng2 -= 5;
				}
				System.out.println(gunAng1);
			}
			
			/*
			if (bKeyL.isKeyDown('<')) {
				if(gunPos1 >=180) {
					gunPos2 = 0;
				}else {
					gunPos2 += 1;
				}
				System.out.println(gunPos1);
			}
			
			if (bKeyL.isKeyDown('>')) {
				if(gunPos2 <=0) {
					gunPos2 = 0;
				}else {
					gunPos2 -= 1;
				}
				System.out.println(gunPos1);
			}
			
			
			
			
			*
			*/
			
			if (bKeyL.isKeyDown('M') && coolDown2 <= 0) {
				Flame.flameList.add(new Flame(enemy.x, enemy.y +20, gunAng2));
				coolDown2 = 30;
			}
			
			
			if (enemy.y >= 460) {
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
			if (bKeyL.isKeyDown(' ') && coolDown < 0) {
				Laser.laserList.add(new Laser(player.x, player.y +20, gunAng1));
				coolDown = 30;
			}

			//ArrayList<Laser> arr = new ArrayList<>();
			//arr = Laser.laserList;
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
		//	Laser.laserList =;
			
			player.move('F');
			enemy.move('F');
			coolDown--;
			coolDown2--;
			//System.out.println(coolDown);
			
		}
	}
}