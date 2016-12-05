import java.awt.*;
import java.awt.Toolkit;

public abstract class Structure implements Scrollable
{
  int x, y;
  Image i;
  Dimension d;

  public Structure (int px, int py, String imageName, Dimension pd) //p for parameter
  {
	  x = px;
	  y = py;
	  i = Toolkit.getDefaultToolkit().getImage(imageName);
	  d = pd;
  }

  public void draw (Graphics2D g, int offset){
  	g.drawImage(i, x-offset, y, (int) d.getWidth(), (int) d.getHeight(), null);
  }

  public abstract void explode ();
}
