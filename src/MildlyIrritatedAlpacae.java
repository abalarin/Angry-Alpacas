import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.*;

public class MildlyIrritatedAlpacae extends JApplet implements ActionListener, MouseListener, MouseMotionListener{
	public static final Dimension FRAMESIZE = new Dimension(1200,800);
	Timer t = new Timer(60,this);
	Alpaca ari;
	CopyOnWriteArrayList<Ammo> pellets = new CopyOnWriteArrayList<Ammo>();
	Ground gr;
	ParticleEngine pe;
	CopyOnWriteArrayList<Structure> buildings = new CopyOnWriteArrayList<Structure>();
	JProgressBar powerBar= new JProgressBar(0, 100);
	Grassfro gras;
	int powerR = 255, powerG, powerB = 66;
	int offset;
	enum GameState{playing,won,menu,lose}
	GameState state = GameState.menu;
	Level l;
	AudioClip backgroundSound, launchSound, solidSound,menuSound,success,lose,grassfrohit, explodeSound, explodeSound2;
	Background backgroundImage;
	Menu theMenu= new Menu();
	int timeCount;
	static boolean playSound;
	public void init(){
		setVisible(true);
		setLayout(null);
		setContentPane(new drawingPanel());
		l = new Level(1);
		ari = new Alpaca(3,"Ari.png","Ari Alpaca Mouth.png",this);
		addMouseListener(this);
		addMouseMotionListener(this);
		gr = new Ground (l.getLevelNum(), null);
		pe = new ParticleEngine();
		setSize(FRAMESIZE);
		t.start();
		powerBar.setBounds(10, 10, 100, 10);
		add(powerBar);
		powerBar.setVisible(false);
		buildings = l.getStructures();
		backgroundSound = getAudioClip(getDocumentBase(), "background.wav");
		launchSound = getAudioClip(getDocumentBase(), "launch.wav");
		solidSound = getAudioClip(getDocumentBase(), "solidcollide.wav");
		menuSound= getAudioClip(getDocumentBase(),"menubackground.wav");
		success= getAudioClip(getDocumentBase(),"success.wav");
		lose= getAudioClip(getDocumentBase(),"lose.wav");
		grassfrohit= getAudioClip(getDocumentBase(),"grassfroHit.wav");
		explodeSound= getAudioClip(getDocumentBase(),"explode.wav");
		explodeSound2= getAudioClip(getDocumentBase(),"explode2.wav");
		menuSound.loop();
		backgroundImage = new Background(l.getLevelNum());
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(state==GameState.playing){
			for (Particle p : pe.getParticleList())
				p.tick();
			pe.removeOffScreenParticles();
			if(!ari.isOutOfAmmo()){
				ari.fluctuatePower();
				powerBar.setValue(ari.getPower());
				changeProgressColor();
				powerBar.setForeground(new Color(powerR, 255 - powerG, powerB));
				if(pellets.size()==0 && pe.getParticleList().size()<=20)	
					if(offset>0)offset/=1.25;
					else offset=0;
			}
		}
		if(state==GameState.won || state==GameState.lose){
			timeCount++;
			if(timeCount>40){
				timeCount=0;
				state=GameState.menu;
				menuSound.loop();
			}
		}
		repaint();
	}
	public class drawingPanel extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			playSound=true;
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(1));
			if(state==GameState.playing){
				backgroundImage.draw(g2,offset);
				g2.drawRect((int)ari.getMouseLoc().getX(), (int)ari.getMouseLoc().getY(),10,10);
				for(Ammo a: pellets){
					if(a.getX()>700)offset=a.getX()-700;
					a.draw(g2);
					a.checkCollision(gr);
					for (Structure b: buildings)
						a.checkCollision(b);
					if(a.isDead()){
						if(a.getX()<700){
							if(a.explodeStyle==0)
							pe.explodeRandomColors(a.getX(),a.getY());
							if(a.explodeStyle==1)
								pe.explodeFire(a.getX(),a.getY());
							if(a.explodeStyle==2)
								pe.explodeImage(a.getX(),a.getY());
						}
						else{
							if(a.explodeStyle==0)
								pe.explodeRandomColors(700,a.getY());
							if(a.explodeStyle==1)
								pe.explodeFire(700,a.getY());
							if(a.explodeStyle==2)
								pe.explodeImage(700,a.getY());
						}
						if(playSound)explodeSound2.play();
						pellets.remove(a);
					}
				}
				ari.draw(g2, offset);
				ari.drawAmmo(g2, getWidth());
				pe.draw(g2,offset);
				g2.setColor(Color.black);
				gr.draw(g2, offset);
				int grassCount=0;
				for (Structure b: buildings){
					b.draw(g2, offset);
					if(b instanceof Grassfro)grassCount++;
					if(b instanceof Building && !(b instanceof Explodable) && ((Building) b).shouldPlay()){
						solidSound.play();
						((Building)b).setSound(false);
					}
					else if(b instanceof Building && (b instanceof Explodable) && ((exBuilding) b).shouldPlay()){
						explodeSound.play();
						((exBuilding)b).setSound(false);
					}
					if(b instanceof Grassfro && ((Grassfro) b).shouldPlay()){
						grassfrohit.play();
						((Grassfro)b).setSound(false);					}
					if(b instanceof Explodable && b.removing)buildings.remove(b);
				}
				g2.setFont(new Font("Arial",Font.PLAIN,20));
				g2.drawString(grassCount + " / " + l.totalGrass(), (int)getSize().getWidth()-100, 30);
				g2.drawImage(Toolkit.getDefaultToolkit().getImage("grasshead.png"), (int)getSize().getWidth()-105, 35, 60, 75, null);
				if(pellets.size()==0 && pe.getParticleList().size()<=20){
					if(grassCount==0)endLevel();
					else if(ari.isOutOfAmmo())lose();
				}
				else if(ari.isOutOfAmmo())backgroundSound.stop();
			}	
			else if(state==GameState.won){
				g2.setFont(new Font("Arial",Font.PLAIN,90));
				g2.drawString("YOU WON!", 300,300);
			}
			else if(state==GameState.lose){
				g2.setFont(new Font("Arial",Font.PLAIN,90));
				g2.drawString("YOU SUCK!", 300,300);
			}
			else if(state==GameState.menu){
				theMenu.draw(g2);
			}
			g2.setClip(0,0,getWidth(),getHeight()/9);
			g2.scale(0.1, 0.1);
			
			//============================================
			
			
			if(state==GameState.playing){
				backgroundImage.draw(g2,offset);
				for(Ammo a: pellets){
					a.justDraw(g2);
				}
				ari.draw(g2, offset);
				pe.draw(g2,offset);
				g2.setColor(Color.black);
				gr.draw(g2, offset);
				for (Structure b: buildings){
					if(b instanceof Grassfro)((Grassfro)b).drawSmall(g2,offset);
					else b.draw(g2, offset);
				}
				g2.fillOval((int)ari.getMouseLoc().getX(), (int)ari.getMouseLoc().getY(),20,20);
			}	
			
			//=====================================
			g2.scale(10,10);
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
		if (state == GameState.playing){
			if(pellets.size()==0 && offset==0){
				pellets.add(ari.launch());
				launchSound.play();
			}
		}
		else if (state == GameState.menu){
			int result = theMenu.mousePress(arg0.getX(), arg0.getY());
			if (result!=0)
			{
				startLevel(result);
				state = GameState.playing;
			}
		}
		

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void mouseMoved(MouseEvent e)
	{
		if (state == GameState.menu)
			theMenu.mouseHover(e.getX(),e.getY());
	}
	public void mouseDragged(MouseEvent e){}
	public void changeProgressColor()
	{
		if (ari.getPower() / 100.0 <= 1.0)
			powerG = (int) (255 * (ari.getPower() / 100.0));
	}
	public void startLevel(int i){
		menuSound.stop();
		l = new Level(i);
		ari = new Alpaca(l.getAmmoAmount(),"Ari.png","Ari Alpaca Mouth.png",this);
		gr = new Ground (l.getLevelNum(), null);
		buildings = l.getStructures();
		backgroundSound.loop();
		backgroundImage = new Background(l.getLevelNum());
		powerBar.setVisible(true);
	}
	public void endLevel(){
		powerBar.setVisible(false);
		backgroundSound.stop();
		success.play();
		state=GameState.won;
	}
	public void lose(){
		powerBar.setVisible(false);
		backgroundSound.stop();
		lose.play();
		state=GameState.lose;
		timeCount=-10;
	}
}
