import java.awt.Dimension;
import java.awt.Toolkit;


public class exBuilding extends Building implements Explodable{

	public exBuilding(int x, Ground g, String iN, Dimension d) {
		super(x, g, iN, d);
		// TODO Auto-generated constructor stub
	}
	public exBuilding(int x, Ground g, Dimension d){
		super(x,g,d);
		i = Toolkit.getDefaultToolkit().getImage("building.png");
	}

}
