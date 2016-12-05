/*import java.util.ArrayList;
public class LevelOld
{
	ArrayList<Grassfro> theGrassFros;
	Alpaca theAlpaca;
	//ArrayList<GlassBuilding> theGlassBuildings;
	//ArrayList<SoildBuilding> theSolidBuildings;
	//ArrayList<BounceBuilding> theBounceBuildings;
	ArrayList<Ground> theGrounds;
	int HighestScore;
	int currentScore;
	String HighScoreName;
	public level(ArrayList<Grassfro> grfo, ArrayList<GlassBuilding> GlB, ArrayList<SoildBuilding> SolB, ArrayList<BounceBuilding> BB, ArrayList<Ground> gr, int ammoAmt, String s, String mouthS, JApplet parent)
	{
		theAlpaca = new Alpaca(ammoAmt, s, mouthS, parent);
		theGrassFros = grfo;
		theGlassBuildings = GlB;
		theSolidBuildings = SolB;
		theBounceBuildings = BB;
		theGrounds = gr;
		currentScore = 0;
		HighestScore = 0;
		HighScoreName = "";
	}
	public ArrayList<Grassfro> getGrassfro()
	{
		return theGrassFros;
	}
	public void draw(Graphics2D g)
	{
		theAlpaca.draw(g);
		for (Grassfro gf : theGrassFros)
			gf.draw(g);
		for (GlassBuilding gb : theGlassBuildings)
			gb.draw(g);
		for (SoildBuilding solB : theSolidBuildings)
			solB.draw(g);
		for (BounceBuilding bonb : theBounceBuildings)
			bonb.draw(g);
		for (Ground gr : theGrounds)
			gr.draw(g);
	}
	public void checkdone()
	{
		if (theGrassFros.size() == 0)
		{
			score = 500 + (500 * theAlpaca.getAmmoLeft());
			if(score>HighestScore)
			{
				newHighestScore(score);
			}
		}
	}
	private void newHighestScore(int newscore)
	{
		HighestScore = newscore;
		//High Score Message, Get name
	}
}*/
