import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;



public abstract class LevelButtons
{
	private BufferedImage img1 = null;
	private BufferedImage img2 = null;
	private boolean click = false;
	private int mouseX, mouseY,clickX, clickY, x, y, height, width;
	JApplet j;

	public LevelButtons(int i1, int i2, int pWidth, int pHeight, BufferedImage img1, BufferedImage img2, JApplet j)
	{
		x=i1;
		y=i2;
		img1 = img1;
		img2 = img2;
		height = pHeight;
		width = pWidth;
		this.j=j;
	}
	public abstract void drawButton(Graphics2D g1);

	public void setCoord(Point pp)
	{
		mouseX = (int)pp.getX();
		mouseY = (int)pp.getY();
		isInside();
	}
	public void setClickCoord(Point p)
	{
		clickX = (int)p.getX();
		clickY = (int)p.getY();
		clickInside();
	}
	public boolean clickInside()
	{
		if(clickX > x+10 && clickX < x+width-15 && clickY > y+5 && clickY < y+height-15)
			return true;
		else
			return false;
	}
	public boolean isInside()
	{
		if(mouseX > x+10 && mouseX < x+width-15 && mouseY > y+5 && mouseY < y+height-15)
			return true;
		else
			return false;

	}
	public boolean clicked()
	{
		if (click)
			return true;
		else
			return false;
	}

}
