import java.awt.*;

public class ExplodableBuilding extends Building implements Explodable
{
	boolean exploded = false;
	public ExplodableBuilding(int x, int y, String i, Dimension d)
	{
		super(x, y, i, d);
	}
	public void draw(Graphics2D g)
	{
		if(exploded == false)
			g.drawImage(i, x, y, (int) d.getWidth(), (int) d.getHeight(), null);
	}
	public void explode()
	{
		exploded = true;
		x = 0;
		y = 0;
		i = null;
		d = null;
	}
}
