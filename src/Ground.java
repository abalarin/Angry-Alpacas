import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.*;

public class Ground implements Scrollable{

	ArrayList<Rectangle> theGrounds = new ArrayList<Rectangle>();
	Image groundImage;
	private int levelNum;
	public Ground(int levelNumber, Image img)
	{
		groundImage = img;
		int ylow= 200;
		if (levelNumber == 1)
		{
			theGrounds.add(new Rectangle(0,580,700,400));
			theGrounds.add(new Rectangle(700,655,400,400));
			theGrounds.add(new Rectangle(1100,570,1000,400));

		}
		else if (levelNumber == 2)
		{
			theGrounds.add(new Rectangle(0,570,500,400));
			theGrounds.add(new Rectangle(500,655,500,400));
			theGrounds.add(new Rectangle(1000,610,700,400));
			theGrounds.add(new Rectangle(1700,300,200,800));
			theGrounds.add(new Rectangle(1900,570,700,400));
		}
		else if (levelNumber == 3)
		{
			theGrounds.add(new Rectangle(0,570,1000,400));
			theGrounds.add(new Rectangle(1000,655,600,400));
			theGrounds.add(new Rectangle(1600,550,600,400));
			theGrounds.add(new Rectangle(2200,400,200,800));
			theGrounds.add(new Rectangle(2400,580,700,400));
		}
		levelNum=levelNumber;
	}
	public void draw(Graphics2D g, int offset)
	{
		for (Rectangle rect : theGrounds)
		//	g.drawImage(groundImage, (int)rect.getX(), (int)rect.getY(), (int) rect.getWidth(), (int)rect.getHeight(),null);
			g.fillRect((int)rect.getX()-offset, (int)rect.getY(),(int)rect.getWidth(),(int)rect.getHeight());
	}
	public ArrayList<Rectangle> getRectangles()
	{
		return theGrounds;
	}
	
	public int convertXtoY (int x)
	{
		/*if (levelNum == 1)
		{
			if (x > 0 && x <= 500) return 600;
			else if (x <= 600) return 690;
			else return 600;
		}
		else if (levelNum == 2)
		{
			if (x > 0 && x <= 400) return 800;
			else if (x <= 500) return 850;
			else if (x <= 1000) return 800;
			else return 500;
		}
		else return 0;*/
		for(Rectangle rect: getRectangles()){
			if(x>rect.getX()&&x<rect.getX()+rect.getWidth()){
				return (int) (rect.getY());
			}
		}
		return 1500;
	}
}
