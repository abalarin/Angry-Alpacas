import java.awt.*;


public class Building extends Structure
{
	private boolean playSound=false;
	public Building(int x, int y, String imageName, Dimension d)
	{
		super(x, y, imageName, d);
	}
	public Building(int x, Ground g, String iN, Dimension d){
		super(x,g,iN,d);
	}
	public Building(int x, Ground g, Dimension d){
		super(x,g,"solidBuilding.png",d);
	}
	public void explode ()
	{

	}
	public boolean shouldPlay(){
		return playSound;
	}
	public void setSound(boolean b){
		playSound=b;
	}
}
