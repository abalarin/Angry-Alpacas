import java.awt.*;

public class Background
{
	int x, y;
	Image i, i2;
	public Background(int x, int y, Image i, Image i2)
	{
		this.x=x;
		this.y=y;
		this.i=i;
		this.i2=i2;
	}
	public void drawWallpaper(Graphics2D g1)
	{
		g1.drawImage(i,x, y, null);
	}
	public void drawLevelSelectWallpaper(Graphics2D g1)
	{
		g1.drawImage(i2,x, y, null);
	}
}
