package _02_Advanced_Robot_Race;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.jointheleague.graphical.robot.Robot;

public class AdvancedRobotRace {
	Random ran = new Random();
	Robot[] base = new Robot[4];
	boolean stop = true;
	Thread update;
	Runnable[] coms = new Runnable[4];
	BufferedImage scorc;
	Graphics garf;

	public static void main(String[] args) {
		new AdvancedRobotRace();
		
	}

	public AdvancedRobotRace() {
		update = new Thread(()->{while(true) {update();}});
		Robot organs = new Robot();
		organs.hide();
		garf=organs.getWindow().getGraphics();
		try {
			scorc = ImageIO.read(AdvancedRobotRace.class.getResourceAsStream("scorcese.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i < coms.length; i++) {
			int e = i;
			coms[i] = () -> {
				while (true) {
					//int g=e;
					System.out.println("ABSOLUTE CINEMA");
					
					while (!stop) {
						//System.out.println("Thread "+e+" unstopped");
						base[e].move(ran.nextInt(5));
						if (base[e].getY() < 100) {
							stop = true;
							base[e].turn(720);

						}
					}
				}
			};
		}
		for (int i = 0; i < base.length; i++) {
			base[i] = new Robot();
			base[i].setX(300 * (i + 1));
			base[i].setY(800);
			base[i].setSpeed(25);
		}
		update();
		for (int i = 0; i < 4; i++) {
			new Thread(coms[i]).start();
		}
		JOptionPane.showMessageDialog(null, "start");
		stop = false;
		update.start();
	}
 void draw(Graphics g, Robot rob) {
	 
g.drawImage(scorc, rob.getX()-50, rob.getY()-50, 231, 180, null);
}
 void update() {
			for(Robot r:base) {
				if(r!=null) {
			draw(garf,r);	
				}
			}	
 }
 public void paintComponent(Graphics g){
	 System.out.println("pant");
	 if(garf!=null) {
		 garf=g;
	 }
 }
}