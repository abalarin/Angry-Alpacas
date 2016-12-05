import java.awt.event.MouseEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class LevelSelectButtons extends LevelButtons
{
	BufferedImage img1,img2;
	private int x, y, height, width;
	private boolean hovering = false, check = true;
	JApplet applet;

	public LevelSelectButtons(int x, int y, int pWidth, int pHeight, BufferedImage img1, BufferedImage img2, JApplet applet)
	{
		super(x,y,pWidth, pHeight,img1,img2,applet);
		this.x = x;
		this.y = y;
		this.width = pWidth;
		this.height = pHeight;
		this.img1 = img1;
		this.img2 = img2;
		this.applet = applet;
	}
	public void drawButton(Graphics2D g1)
	{
		checkHovering();
		if(hovering == false)
			g1.drawImage(img1,x, y, null);
		if(hovering){
			g1.drawImage(img2,x, y, null);
		}

	}
	public void checkHovering()
	{
		if(isInside())
			hovering = true;
		else
			hovering=false;
	}
	public void checkClick(Graphics2D g)
	{
		if(clickInside()){}
	}

}




















