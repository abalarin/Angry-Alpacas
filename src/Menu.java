import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Menu//This is the menu
{
	enum Gamestate {main, levSelect}
	Gamestate current = Gamestate.main;
	Image mainImage;
	Image goImage;
	Image levelSel;
	Image poofLogo, mia;
	Rectangle grect;
	Boolean hovergo, lev1, lev2, lev3;
	BufferedImage level1, level2, level3,level11, level21, level31,level12, level22, level32;
	Rectangle rect1, rect2, rect3;
	int red,green,blue;
	Random r = new Random();
	public Menu()
	{
		mainImage = Toolkit.getDefaultToolkit().getImage("main.png");
		goImage = Toolkit.getDefaultToolkit().getImage("go.png");
		
		try{
			level11 = ImageIO.read(new File("lev11.png"));
			level21 = ImageIO.read(new File("lev21.png"));
			level31 = ImageIO.read(new File("lev31.png"));
			level12 = ImageIO.read(new File("lev12.png"));
			level22 = ImageIO.read(new File("lev22.png"));
			level32 = ImageIO.read(new File("lev32.png"));
			poofLogo = ImageIO.read(new File("pooflogo.png"));
			mia = ImageIO.read(new File("mia.png"));
			
		}catch(IOException e){}
		levelSel= Toolkit.getDefaultToolkit().getImage("Level Select.png");
		level1 = level11;
		level2 = level21;
		level3 = level31;
		grect= new Rectangle(450+20,650-250,250,100);
		rect1 = new Rectangle(200,200,200,200);
		rect2 = new Rectangle(500,200,200,200);
		rect3 = new Rectangle(800,200,200,200);
		hovergo = false;
		lev1 = false;
		lev2 = false;
		lev3 = false;
		red=150;
		blue=150;
		green=150;
	}

	public void draw(Graphics g)
	{
		red+=(r.nextInt(7)-3);
		if(red>255)red=255;
		if(red<0)red=0;
		green+=(r.nextInt(7)-3);
		if(green>255)green=255;
		if(green<0)green=0;
		blue+=(r.nextInt(7)-3);
		if(blue>255)blue=255;
		if(blue<0)blue=0;
		if(current==Gamestate.main)g.setColor(new Color(red,green,blue));
		else g.setColor(Color.black);
		g.fillRect(0,0,2000,1000);
		if (current == Gamestate.main)
		{
			g.drawImage(mainImage, 460+80,100,null);
			g.drawImage(goImage,(int)grect.getX(), (int)grect.getY(), (int)grect.getWidth(), (int)grect.getHeight(),null);
			g.setFont(new Font("arial",Font.PLAIN,40));
			g.setColor(Color.GREEN);
			//g.drawString("Mildly Irritataed Alpacae", 400,50);
			g.setClip(202,10,740,70);
			g.drawImage(mia,200,10,750,75,null);
			g.setClip(null);
			g.setFont(new Font("arial",Font.PLAIN,25));
			g.setColor(Color.RED);
			g.drawString("Jake Podell, John Mottole, Kevin Greer, Austin Balarin, and Ian Donohue",230-35,780-200);
			g.setColor(Color.white);
			g.setFont(new Font("arial",Font.PLAIN,30));
			g.drawString("#poofBananKillIt2k14   #HELLYEA",410-35,820-200);
			g.drawImage(poofLogo,100,200,300,300,null);
			g.drawImage(poofLogo,800,200,300,300,null);
		}
		if (current == Gamestate.levSelect)
		{
			//g.setFont(new Font("arial",Font.PLAIN,50));
			//g.setColor(Color.ORANGE);
			//g.drawString("Level Select", 500,100);
			g.drawImage(levelSel,400,75,450,100,null);
			g.drawImage(level1,(int)rect1.getX(), (int)rect1.getY(), (int)rect1.getWidth(), (int)rect1.getHeight(),null);
			g.drawImage(level2,(int)rect2.getX(), (int)rect2.getY(), (int)rect2.getWidth(), (int)rect2.getHeight(),null);
			g.drawImage(level3,(int)rect3.getX(), (int)rect3.getY(), (int)rect3.getWidth(), (int)rect3.getHeight(),null);

		}
	}
	public int mousePress(int x, int y)
	{
		if (current == Gamestate.main && grect.contains(x,y))
		{
			current = Gamestate.levSelect;
		}
		if (current == Gamestate.levSelect)
		{
			if (rect1.contains(x,y))
				return 1;
			if (rect2.contains(x,y))
				return 2;
			if (rect3.contains(x,y))
				return 3;
		}
		return 0;
	}
	public void mouseHover(int x, int y)
	{
		if (current == Gamestate.main)
		{

			if (grect.contains(x,y))
			{
				goImage = Toolkit.getDefaultToolkit().getImage("go1.png");
				hovergo = true;
			}
			else if (hovergo == true)
			{
				hovergo = false;
				goImage = Toolkit.getDefaultToolkit().getImage("go.png");
			}

		}



		if (current == Gamestate.levSelect)
		{

			if (rect1.contains(x,y))
			{
				level1 = level12;
				lev1 = true;
			}
			else if (lev1 == true)
			{
				lev1 = false;
				level1 = level11;
			}
			if (rect2.contains(x,y))
			{
				level2 = level22;
				lev2 = true;
			}
			else if (lev2 == true)
			{
				lev2 = false;
				level2 = level21;
			}
			if (rect3.contains(x,y))
			{
				level3 = level32;
				lev3 = true;
			}
			else if (lev3 == true)
			{
				lev3 = false;
				level3 = level31;
			}
		}
	}
}