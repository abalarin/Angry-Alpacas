import java.awt.Dimension;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


public class Level {
	static Ground gr;
	private int levelNum,ammoAmount;
	public Level(int num){
		levelNum=num;
		gr = new Ground(levelNum,null);
		
	}
	public CopyOnWriteArrayList<Structure> getStructures(){
		CopyOnWriteArrayList<Structure> structures = new CopyOnWriteArrayList<Structure>();
		if(levelNum==1){
			structures.add(new exBuilding(400,gr,new Dimension(75,400)));
			structures.add(new Grassfro(800,gr,new Dimension(150,150)));
			structures.add(new Building(1600,gr,new Dimension(75,300)));
			structures.add(new exBuilding(1150,gr,new Dimension(75,325)));
			structures.add(new Grassfro(1300,gr,new Dimension(150,150)));
		}
		else if(levelNum==2){
			structures.add(new exBuilding(600,gr,new Dimension(75,300)));
			structures.add(new Grassfro(700,gr,new Dimension(150,150)));
			structures.add(new Building(900,gr,new Dimension(75,300)));
			structures.add(new Building(1100,gr,new Dimension(75,300)));
			structures.add(new Grassfro(1500,gr,new Dimension(150,150)));
			structures.add(new Grassfro(2000,gr,new Dimension(150,150)));
			structures.add(new Building(2300,gr,new Dimension(150,700)));
		}
		else if(levelNum==3){
			structures.add(new Building(1300,gr,new Dimension(75,200)));
			structures.add(new Grassfro(1100,gr,new Dimension(150,150)));
			structures.add(new Grassfro(1400,gr,new Dimension(150,150)));
			structures.add(new Building(1650,gr,new Dimension(75,200)));
			structures.add(new exBuilding(1750,gr,new Dimension(75,300)));
			structures.add(new Grassfro(1850,gr,new Dimension(150,150)));
			structures.add(new Building(2000,gr,new Dimension(75,200)));
			structures.add(new Grassfro(2300,gr,new Dimension(150,150)));
			structures.add(new Grassfro(2700,gr,new Dimension(150,150)));
			
		}
		return structures;
	}
	public int getLevelNum(){
		return levelNum;
	}
	public int getAmmoAmount(){
		if(levelNum==1)return 4;
		if(levelNum==2)return 5;
		if(levelNum==3)return 8;
		return 0;
	}
	public int totalGrass() {
		if(levelNum==1)return 2;
		if(levelNum==2)return 3;
		if(levelNum==3)return 5;
		return 0;
	}
}
