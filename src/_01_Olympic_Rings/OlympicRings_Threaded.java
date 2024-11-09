package _01_Olympic_Rings;

import java.awt.Color;

import org.jointheleague.graphical.robot.Robot;

public class OlympicRings_Threaded {
	int turn=3;
	int size=5;
	// Make A Program that uses Threads and robots to draw the Olympic rings. One robot should draw one ring simultaneously with the other 4 robots.
public static void main(String[] args) {
	new OlympicRings_Threaded().run();
	
}
void run() {
	Robot redmond = new Robot(1000,300);
	Robot pepe = new Robot(900,400);
	Robot black = new Robot(775,300);
	Robot corn = new Robot(675,400);
	Robot blutarch = new Robot(550,300);
	
	Thread r1 = new Thread(()->drawCircle(redmond, new Color(200,0,0)));
	Thread r2 = new Thread(()->drawCircle(pepe,new Color(0,200,50)));
	Thread r3 = new Thread(()->	drawCircle(black,new Color(0,0,0))
);
	Thread r4 = new Thread(()->	drawCircle(corn,new Color(250,250,0))
);
	Thread r5 = new Thread(()->	drawCircle(blutarch,new Color(0,30,250))
);
	r1.start();
	r2.start();
	r3.start();
	r4.start();
	r5.start();
	
	
	
}
void drawCircle(Robot rob, Color c) {
	rob.penDown();
	rob.setPenColor(c);
	rob.setPenWidth(40);
	rob.setSpeed(30);
	int x=rob.getX();
	int y=rob.getY();
	rob.move(size);
	int turnsLeft=360/turn;
	while(turnsLeft>0) {
		rob.turn(turn);
		rob.move(size);
		turnsLeft--;
	}
}
}

