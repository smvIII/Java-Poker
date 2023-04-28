import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;

public class CardPanel extends JPanel 
{
	ImageIcon cardBack;
	int frameHeight;
	int frameWidth;
	Card cardx = new Card();
	Card cardy = new Card();
	Card cardx1 = new Card();
	Card cardy1 = new Card();
	Card carda = new Card();
  	Card cardb = new Card();
	Card cardc = new Card();
	Card cardd = new Card();
	Card carde = new Card();
	
	ImageIcon handCard1;
	ImageIcon handCard2;
	ImageIcon opponentCard1;
	ImageIcon opponentCard2;
	ImageIcon commCard1;
	ImageIcon commCard2;
	ImageIcon commCard3;
	ImageIcon commCard4;
	ImageIcon commCard5;
	
	
	CardPanel()
	{
		Color backgroundColor = new Color (53, 101, 77);
		this.setLayout(new BorderLayout());
		this.setBackground(backgroundColor);
	}
	
	
	public static int getFrameHeightOrWidth( JPanel jp, int option)
	{
		Rectangle r = jp.getBounds();
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
	
	
	public static Graphics correctFontColor (String suit, Graphics g)
	{
		if (suit == "hearts" || suit == "diamonds" )
		{
			g.setColor(Color.RED);
		}
		else
		{
			g.setColor(Color.BLACK);
		}
		return g;
	}
}
