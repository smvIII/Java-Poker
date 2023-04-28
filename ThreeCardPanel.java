import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;

public class ThreeCardPanel extends CardPanel
{
	
	public void currentHand(Card card1, Card card2)
	{
		cardx = cardx.equals(card1);
		
		cardy = cardy.equals(card2);
		
		ImageIcon handCard1 = new ImageIcon(cardx.getSuitImageStr());
		ImageIcon handCard2 = new ImageIcon(cardy.getSuitImageStr());
		//System.out.println("hi"+cardy.getSuitImageStr());
	}
	
	public void currentCommunityCards(Card card1, Card card2, Card card3)
	{
		carda = carda.equals(card1);
		cardb = cardb.equals(card2);
		cardc = cardc.equals(card3);
		ImageIcon commCard1 = new ImageIcon(getClass().getResource(carda.getSuitImageStr()));
		ImageIcon commCard2 = new ImageIcon(getClass().getResource(cardb.getSuitImageStr()));
		ImageIcon commCard3 = new ImageIcon(getClass().getResource(cardc.getSuitImageStr()));
		
		
	}
		
		
	

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		frameHeight = getFrameHeightOrWidth(this, 0);
		frameWidth = getFrameHeightOrWidth(this, 1);
			
		//cardBack = new ImageIcon("cardback.png");
		cardBack = new ImageIcon(getClass().getResource("cardback.png"));
		handCard1 = new ImageIcon(getClass().getResource(cardx.getSuitImageStr()));
		handCard2 = new ImageIcon(getClass().getResource(cardy.getSuitImageStr()));
		commCard1 = new ImageIcon(getClass().getResource(carda.getSuitImageStr()));
		commCard2 = new ImageIcon(getClass().getResource(cardb.getSuitImageStr()));
		commCard3 = new ImageIcon(getClass().getResource(cardc.getSuitImageStr()));
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 23)); 
		cardBack.paintIcon(this, g, frameWidth/4, frameHeight/2-80);
		
		g = correctFontColor(cardx.getSuit(), g);
		handCard1.paintIcon(this, g, frameWidth-210, frameHeight-140);
		g.drawString(cardx.getFaceName(), frameWidth-183, frameHeight-110);
		
		g = correctFontColor(cardy.getSuit(), g);
		handCard2.paintIcon(this, g, frameWidth-117, frameHeight-140);
		g.drawString(cardy.getFaceName(), frameWidth-90, frameHeight-110);
		
		//draw players hidden cards
		cardBack.paintIcon(this, g, frameWidth/2-166, frameHeight-140);
		cardBack.paintIcon(this, g, frameWidth/2-73, frameHeight-140);
		
		//draw opponent hidden cards
		cardBack.paintIcon(this, g, frameWidth/2-166, frameHeight/15);
		cardBack.paintIcon(this, g, frameWidth/2-73, frameHeight/15);
		
		g.setColor(Color.BLACK);
		
		g = correctFontColor(carda.getSuit(), g);
		commCard1.paintIcon(this, g, frameWidth/4+93, frameHeight/2-80);
		g.drawString(carda.getFaceName(), frameWidth/4+120, frameHeight/2-50);
		
		g = correctFontColor(cardb.getSuit(), g);
		commCard2.paintIcon(this, g, frameWidth/4+186, frameHeight/2-80);
		g.drawString(cardb.getFaceName(), frameWidth/4+213, frameHeight/2-50);
		
		g = correctFontColor(cardc.getSuit(), g);
		commCard3.paintIcon(this, g, frameWidth/4+279, frameHeight/2-80);
		g.drawString(cardc.getFaceName(), frameWidth/4+306, frameHeight/2-50);
		
		
		g.setColor(Color.BLACK);
		

	}

	/*public static int getFrameHeightOrWidth( ThreeCardPanel tcp, int option)
	{
		Rectangle r = tcp.getBounds();
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
	}*/
	
	/*public static Graphics correctFontColor (String suit, Graphics g)
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
	}*/

}
	
