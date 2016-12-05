import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;

public class MenuDriver extends JApplet
{
	BufferedImage PlayButtonSelect, PlayButtonDeselect, Wallpaper, Wallpaper2;
	BufferedImage Level1Select, Level1noSelect, Level2Select, Level2noSelect,Level3Select, Level3noSelect;
	int width, height;

	MainMenu MM = new MainMenu();
	public void init()
	{
		try
		{
			Wallpaper          = ImageIO.read(new File("Background3.jpg")); 	  //must download an image(currently jsut a place holder)
			Wallpaper2         = ImageIO.read(new File("LevelSelectBackground.jpg")); //must download an image(currently jsut a place holder)
							//rest of images are uploaded on 
			PlayButtonSelect   = ImageIO.read(new File("PlayButtonActive.png"));
			PlayButtonDeselect = ImageIO.read(new File("PlayButtonNonActive.png"));

			Level1Select 	   = ImageIO.read(new File("Icon-UI-Level1Hover.png"));
			Level1noSelect 	   = ImageIO.read(new File("Icon-UI-Level1noHover.png"));
			Level2Select 	   = ImageIO.read(new File("Icon-UI-Level2Hover.png"));
			Level2noSelect 	   = ImageIO.read(new File("Icon-UI-Level2noHover.png"));
			Level3Select 	   = ImageIO.read(new File("Icon-UI-Level3Hover.png"));
			Level3noSelect 	   = ImageIO.read(new File("Icon-UI-Level3noHover.png"));
		}
		catch(IOException e){}
		MM.addPlayButton(new PlayButton(50,70,width = PlayButtonSelect.getWidth(),height = PlayButtonSelect.getHeight(), PlayButtonDeselect, PlayButtonSelect,this,MM));

		MM.addLevelButton(new LevelSelectButtons(110,250,width = Level1Select.getWidth(),height = Level1Select.getHeight(), Level1noSelect, Level1Select,this));
		//MM.addLevelButton(new LevelSelect2(380,250,width = Level1Select.getWidth(),height = Level1Select.getHeight(), Level2noSelect, Level2Select,this));
		MM.addLevelButton(new LevelSelectButtons(380,250,width = Level1Select.getWidth(),height = Level1Select.getHeight(), Level2noSelect, Level2Select,this));
		MM.addLevelButton(new LevelSelectButtons(650,250,width = Level1Select.getWidth(),height = Level1Select.getHeight(), Level3noSelect, Level3Select,this));

		MM.addBackground(new Background(0,0,Wallpaper, Wallpaper2));

		setContentPane(MM);
		setSize(1000,700);
	}
}
