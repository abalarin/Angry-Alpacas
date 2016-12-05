
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;

import javax.swing.*;

public class Alpaca implements MouseMotionListener, Scrollable{

	private int power;
	private boolean subtractPower = true;
	private double theta;
	private int ammoCount, ammoMax;
	private Image i;
	private Image mouth;
	private Image ammoImage;
	private Point mousePoint;
	public static Point ARILOC = new Point (50, 350);
	public static final int HEIGHT = 300;
	public static final int WIDTH = 100;
	public static final Point MOUTHLOC = new Point(ARILOC.x+125,ARILOC.y + 45);


	public Alpaca(int ammoAmount, String s, String mouthS, JApplet parent){
		i = Toolkit.getDefaultToolkit().getImage(s);
		mouth = Toolkit.getDefaultToolkit().getImage(mouthS);
		ammoCount=ammoAmount;
		ammoMax=ammoCount;
		mousePoint = new Point(0,0);
		parent.addMouseMotionListener(this);
		power = 100;
		ammoImage= Toolkit.getDefaultToolkit().getImage("Spitball.png");
	}

	public void draw(Graphics2D g, int offset){
		calcTheta();
		g.drawImage(i,ARILOC.x-offset,ARILOC.y,null);
		AffineTransform transform = new AffineTransform();
		AffineTransform old = g.getTransform();
		transform.rotate(theta, MOUTHLOC.getX()-offset, MOUTHLOC.getY());
		g.transform(transform);
		g.drawImage(mouth,MOUTHLOC.x-offset,MOUTHLOC.y,null);
		g.setTransform(old);
	}
	public void drawAmmo(Graphics2D g, int farX){
		g.setFont(new Font("arial",Font.PLAIN,20));
		g.drawString(ammoCount+" / "+ammoMax, farX-200, 30);
		g.drawImage(ammoImage,farX-208,51,null);
	}

	public void rorateMouth(){

	}
	public void fluctuatePower(){
		if (subtractPower)
			power-=4;
		else
			power+=4;
		if (power < 15 || power > 100)
			subtractPower = !subtractPower;
	}
	public Ammo launch(){
		double xVel = 1.1*power*Math.cos(theta);
		double yVel = 1.1*power*Math.sin(theta);
		ammoCount--;
		return(new Ammo((int)xVel,(int)yVel));
	}
	public boolean isOutOfAmmo(){
		return ammoCount<=0;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePoint = e.getPoint();

	}
	public Point getMouseLoc(){
		return mousePoint;
	}
	public int getPower()
	{
		return power;
	}
	private void calcTheta(){
		double x =  mousePoint.getX()-MOUTHLOC.getX();
		double y =  mousePoint.getY()-MOUTHLOC.getY();
		double angle = Math.atan(y/x);
		if(angle<-Math.PI/4)angle=-Math.PI/4;
		else if(angle>Math.PI/4)angle=Math.PI/4;
		theta= angle;
	}
}
