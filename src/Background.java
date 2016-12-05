import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;


public class Background implements Scrollable{
	int width, height,level;
	Image i;
	public Background(int levelNum){
		level=levelNum;
		if(level==1)i = Toolkit.getDefaultToolkit().getImage("sky.jpg");
		else if(level==2)i= Toolkit.getDefaultToolkit().getImage("sunset.jpg");
		else i=Toolkit.getDefaultToolkit().getImage("city.jpg");
	}
	public void draw (Graphics2D g, int offset){
	  	if(level==1)g.drawImage(i, 0-(offset/3), 0, 2500, 1000, null);
	  	else if(level==2) g.drawImage(i, 0-(offset/2), -200, 3000, 1200, null);
	  	else g.drawImage(i, 0-(offset/2), -50, 3000, 1000, null);
	 }
}
