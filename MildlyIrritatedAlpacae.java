import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.*;

public class MildlyIrritatedAlpacae extends JApplet implements ActionListener, MouseListener{
	public static final Dimension FRAMESIZE = new Dimension(1200,800);
	Timer t = new Timer(80,this);
	Alpaca ari;
	CopyOnWriteArrayList<Ammo> pellets = new CopyOnWriteArrayList<Ammo>();
	Ground gr;
	ParticleEngine pe;
	CopyOnWriteArrayList<Building> buildings = new CopyOnWriteArrayList<Building>();
	JProgressBar powerBar= new JProgressBar(0, 100);
	int powerR = 255, powerG, powerB = 66;

	public void init(){
		setVisible(true);
		setLayout(null);
		setContentPane(new drawingPanel());
		ari = new Alpaca(3,"Ari.png","Ari Alpaca Mouth.png",this);
		addMouseListener(this);
		gr = new Ground (1);
		pe = new ParticleEngine();
		buildings.add(new Building (850, gr.convertXtoY(850) - 300, "building.jpg", new Dimension (75, 300)));
		buildings.add(new Building (1000, gr.convertXtoY(1000) - 500, "building.jpg", new Dimension (75, 500)));
		setSize(FRAMESIZE);
		t.start();
		powerBar.setBounds(10, 10, 100, 10);
		add(powerBar);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		for (Particle p : pe.getParticleList())
			p.tick();
		pe.removeOffScreenParticles();
		ari.fluctuatePower();
		powerBar.setValue(ari.getPower());
		changeProgressColor();
		powerBar.setForeground(new Color(powerR, 255 - powerG, powerB));
		repaint();
	}
	public class drawingPanel extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			ari.draw(g2, 1);
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(1));
			g2.drawRect((int)ari.getMouseLoc().getX(), (int)ari.getMouseLoc().getY(),10,10);
			for(Ammo a: pellets){
				a.draw(g2);
				a.checkCollision(gr);
				for (Building b: buildings)
					a.checkCollision(b);
				if(a.isDead()){
					pe.explodeFire(a.getX(),a.getY());
					pellets.remove(a);
				}
			}
			pe.draw(g2);
			g2.setColor(Color.black);
			gr.draw(g2);
			for (Building b: buildings)
				b.draw(g2, 1);
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {

	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		pellets.add(ari.launch());

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void changeProgressColor()
	{
		if (ari.getPower() / 100.0 <= 1.0)
			powerG = (int) (255 * (ari.getPower() / 100.0));
	}
}
