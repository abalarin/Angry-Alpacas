import java.awt.*;

public class Grassfro extends Structure implements Scrollable, Explodable
{
	boolean exploded = false;
	boolean shouldPlay;
	Image i2;
	
	public Grassfro (int x, int y, String i, Dimension d)
	{
		super(x, y, i, d);
	}
	public Grassfro(int x, Ground g, Dimension d){
		super(x,(int)(g.convertXtoY(x)-d.getHeight()),"grassfro2.gif",d);
		 i2 = Toolkit.getDefaultToolkit().getImage("grasshead.png");
	}

	public void draw (Graphics2D g, int offset)
	{
		if (exploded == false)
			g.drawImage(i, x-offset, y+10, (int) d.getWidth(), (int) d.getHeight(), null);
	}

	public void explode ()
	{
		exploded = true;
		x = 0;
		y = 0;
		i = null;
		d = null;
	}
	public Rectangle getRect()
	{
		Rectangle theRect = new Rectangle(x+40,y,(int) d.getWidth()-80, (int) d.getHeight());
		return theRect;
	}
	public boolean shouldPlay() {
		// TODO Auto-generated method stub
		return shouldPlay;
	}
	public void setSound(boolean b) {
		// TODO Auto-generated method stub
		shouldPlay=b;
	}
	public void drawSmall(Graphics2D g, int offset) {
		g.drawImage(i2, x-offset-10, y, 120, 150, null);
	}
}
