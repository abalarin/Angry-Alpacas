import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Ammo
{
	BufferedImage theImage;
	public static final int WIDTH = 35;
	public static final int HEIGHT =35;
	int xVelocity, yVelocity;
	int oldX, oldY;
	private int x;
	private int y;
	Point theLocation;
	private final double COEFFICIENT_OF_FRICTION = 0.5;
	private boolean bouncing; //determines if the move method still needs to be called
	int explodeStyle;
	public Ammo(int xVel, int yVel)
	{
		try
		{
			theImage = ImageIO.read(new File("Spitball.png"));
		}
		catch(IOException e){}
		theLocation = new Point(50,50);
		x = (int) Alpaca.MOUTHLOC.getX();
		y = (int) Alpaca.MOUTHLOC.getY();
		theLocation = new Point(x,y);
		xVelocity=xVel;
		yVelocity=yVel;
		bouncing=true;

	}
	public void draw(Graphics2D g)
	{
		int drawX=x;
		if(x>700) drawX=700;
		g.drawImage(theImage, drawX, y, WIDTH, HEIGHT, null);
		move();
	}
	public void justDraw(Graphics2D g){
		int drawX=x;
		if(x>700) drawX=700;
		g.drawImage(theImage, drawX, y, WIDTH, HEIGHT, null);
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Rectangle getRect()
	{
		return (new Rectangle (x, y, WIDTH, HEIGHT));
	}
	private void kill(){
		yVelocity=0;
		xVelocity=0;
	}
	public void checkCollision(Ground g){
		if(y-(HEIGHT*3)>=g.convertXtoY(x+xVelocity)){
			xVelocity*=(COEFFICIENT_OF_FRICTION / 1.5);
			xVelocity=-xVelocity;
			x=x-(WIDTH+10);
		}
		else if(y+HEIGHT>=g.convertXtoY(x)){
			y=(int) g.convertXtoY(x)-HEIGHT;
			if(bouncing){
				yVelocity-=10;
				yVelocity=-yVelocity;
				if(Math.abs(yVelocity)<=10){
					yVelocity=0;
					bouncing =false;
					System.out.println("died");
				}
			}
			xVelocity*=COEFFICIENT_OF_FRICTION;
			if(Math.abs(xVelocity)<1)xVelocity=0;
		}
	}
	public void checkCollision(Structure b){
		if(getRect().intersects(b.getRect()) && y + HEIGHT - yVelocity > b.getY())
		{
			if(b instanceof Explodable){
				kill();
				b.removing=true;
				MildlyIrritatedAlpacae.playSound=false;
				if(b instanceof Building){
					((exBuilding) b).setSound(true);
					explodeStyle=1;
				}
				if(b instanceof Grassfro){
					((Grassfro) b).setSound(true);
					explodeStyle=2;
				}
			}
			else{
				((Building) b).setSound(true);
			}
			if(bouncing){
				xVelocity*=(COEFFICIENT_OF_FRICTION / 1.5);
				xVelocity=-xVelocity;
				if (oldX >= b.getX() + WIDTH) x = b.getX() + (int) b.getRect().getWidth();
				else x=b.getX()- WIDTH;
			}
		}
		else if (getRect().intersects(b.getRect()))
		{
			if(b instanceof Explodable){
				kill();
				b.removing=true;
				MildlyIrritatedAlpacae.playSound=false;
				if(b instanceof Building){
					((exBuilding) b).setSound(true);
					explodeStyle=1;
				}
				if(b instanceof Grassfro){
					((Grassfro) b).setSound(true);
					explodeStyle=2;
				}
			}
			else{
				((Building) b).setSound(true);
			}
			y=(int) b.getY()-HEIGHT;
			if(bouncing){
				yVelocity-=10;
				yVelocity=-yVelocity;
				if(Math.abs(yVelocity)<=10){
					yVelocity=0;
					xVelocity = 0;
					bouncing =false;
					System.out.println("died");
				}
			}
		}
	}

	public boolean isDead(){
		return(xVelocity==0&&yVelocity==0) || y>1000;
	}
	public void launch(int xVel, int yVel)
	{
		xVelocity = xVel;
		yVelocity = yVel;
	}
	public void move()
	{
		oldX = x;
		oldY = y;
		if(bouncing){
			yVelocity += 5;
			y+=yVelocity;
		}
		x+=xVelocity;

	}
	public Point getLocation()
	{
		return theLocation;
	}
	public void explode()
	{
		Rectangle ammorect = new Rectangle((int)theLocation.getX() - 25, (int)theLocation.getY() - 25, 50,50);
	/*	ArrayList<Grassfro> grassfros = level.getGrassfro(); //Gets all grassfros in level
		for (Grassfro grass : grassfros)
		{
			if (ammorect.intersects(grass.getRect()))
			{
				grass.explode();
			}
		}*/
	}

}
