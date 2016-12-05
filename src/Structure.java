import java.awt.*;

public abstract class Structure implements Scrollable
{
  int x, y;
  Image i;
  Dimension d;
  boolean removing;

  public Structure (int px, int py, String imageName, Dimension pd) //p for parameter
  {
	  x = px;
	  y = py;
	  i = Toolkit.getDefaultToolkit().getImage(imageName);
	  d = pd;
  }
  public Structure(int px, Ground g, String imageName, Dimension pd){
	  x=px;
	  i = Toolkit.getDefaultToolkit().getImage(imageName);
	  d = pd;
	  y = (int) (g.convertXtoY(x)-d.getHeight());
  }

  public void draw (Graphics2D g, int offset){
  	g.drawImage(i, x-offset-10, y, (int) d.getWidth()+20, (int) d.getHeight(), null);
  }

  public abstract void explode ();
  public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}

	public Rectangle getRect()
	{
		return (new Rectangle (x, y, (int) d.getWidth(), (int) d.getHeight()));
	}
}
