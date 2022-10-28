import javax.swing.JFrame; 
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Color; 
import java.awt.Rectangle;  
import java.awt.Font;  
import java.awt.GridBagLayout;


public class MainMenuPanel extends JPanel
{
	private int frameHeight;
	private int frameWidth;
	private Rectangle r = new Rectangle();
	
	public MainMenuPanel()
	{
		Color backgroundColor = new Color (53, 101, 77);
		this.setLayout(new GridBagLayout());
		this.setBackground(backgroundColor);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		ImageIcon backGroundImage = new ImageIcon(getClass().getResource("MainMenuBackground.png"));
		backGroundImage.paintIcon(this, g, 100,-50);
		frameHeight = getFrameHeightOrWidth(this, 0);
		frameWidth = getFrameHeightOrWidth(this, 1);
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, frameHeight/25)); 
		g.drawString("Welcome to Java Poker", frameWidth/2-frameWidth/8, frameHeight/4-10);
		//System.out.println("Height = " + frameHeight + " width = " + frameWidth);
		
	}
	
	public static int getFrameHeightOrWidth( MainMenuPanel mmp, int option)
	{
		Rectangle r = mmp.getBounds();
		int h = r.height;
		int w = r.width;
		if (option == 0)
		{
			return h;
		}
		else
		{
			return w;
		}
	}
}
