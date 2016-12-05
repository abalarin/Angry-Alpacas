import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.event.MouseListener;

public class MainMenu extends JPanel implements MouseListener, MouseMotionListener
{
	int x,y;

	ArrayList<MenuButtons> playButton;
	ArrayList<LevelButtons> levelButton;
	ArrayList<Background> background;
	Boolean MainScreen = true;

	int mouseX,mouseY; //mouse coordinates
	public MainMenu()
	{
		playButton = new ArrayList<MenuButtons>();
		levelButton = new ArrayList<LevelButtons>();
		background = new ArrayList<Background>();
		addMouseListener(this);
      	addMouseMotionListener( this );
		//repaint();
	}

	public void addPlayButton(MenuButtons b)
	{
		playButton.add(b);
	}
	public void addLevelButton(LevelButtons b)
	{
		levelButton.add(b);
	}
	public void addBackground(Background b)
	{
		background.add(b);
	}

	public void mousePressed(MouseEvent e)
	{
		Point pp = new Point(e.getX(), e.getY());
		for(MenuButtons b: playButton)
		{
			b.setClickCoord(pp);
		}
		for(LevelButtons b: levelButton)
		{
			b.setClickCoord(pp);
		}
		repaint();
	}
	public void mouseMoved(MouseEvent e)
	{
		Point p = new Point(e.getX(), e.getY());
		for(MenuButtons b: playButton)
		{
			b.setCoord(p);
		}
		for(LevelButtons b: levelButton)
		{
			b.setCoord(p);
		}
		repaint();
	}
	public void mouseReleased(MouseEvent e)
	{
		for(MenuButtons b: playButton)
		{
			b.mouseReleased();
		}
	}
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){/*System.out.println("mouseEntered");*/}
	public void mouseDragged(MouseEvent e){/*System.out.println("mouseDragged");*/}
	public void mouseExited(MouseEvent e){/*System.out.println("mouseExited");*/}
	public void changeBoolean()
	{
		MainScreen = false;
	}

	public void paintComponent(Graphics g)
	{
    	super.paintComponent(g);
    	Graphics2D g1 = (Graphics2D)g;
    	if (MainScreen)
    	{
        	for(Background b: background){b.drawWallpaper(g1);}
        	for(MenuButtons b: playButton){b.drawButton(g1);}
		}
		if (MainScreen==false)
		{
			g1.setColor(Color.BLACK);
			g1.fillRect(0,0,1000,700);
        	for(Background b: background){b.drawLevelSelectWallpaper(g1);}
        	for(LevelButtons b: levelButton){b.drawButton(g1);}
		}

    }
}
