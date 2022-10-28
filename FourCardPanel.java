import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;

public class FourCardPanel extends CardPanel
{
	public void currentHand(Card card1, Card card2, Card card3, Card card4)
	{
		cardx = cardx.equals(card1);
		cardy = cardy.equals(card2);
		cardx1 = cardx1.equals(card3);
		cardy1 = cardy1.equals(card4);
		
		
		ImageIcon handCard1 = new ImageIcon(cardx.getSuitImageStr());
		ImageIcon handCard2 = new ImageIcon(cardy.getSuitImageStr());
		
		
		ImageIcon opponentCard1 = new ImageIcon(cardx1.getSuitImageStr());
		ImageIcon opponentCard2 = new ImageIcon(cardy1.getSuitImageStr());
	}
	
	public void currentCommunityCards(Card card1, Card card2, Card card3, Card card4, Card card5)
	{
		carda = carda.equals(card1);
		cardb = cardb.equals(card2);
		cardc = cardc.equals(card3);
		cardd = cardd.equals(card4);
		carde = carde.equals(card5);
		ImageIcon commCard1 = new ImageIcon(getClass().getResource(carda.getSuitImageStr()));
		ImageIcon commCard2 = new ImageIcon(getClass().getResource(cardb.getSuitImageStr()));
		ImageIcon commCard3 = new ImageIcon(getClass().getResource(cardc.getSuitImageStr()));
		
		
	}
		
		
	

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		frameHeight = getFrameHeightOrWidth(this, 0);
		frameWidth = getFrameHeightOrWidth(this, 1);
			
		cardBack = new ImageIcon(getClass().getResource("cardback.png"));
		handCard1 = new ImageIcon(getClass().getResource(cardx.getSuitImageStr()));
		handCard2 = new ImageIcon(getClass().getResource(cardy.getSuitImageStr()));
		opponentCard1 = new ImageIcon(getClass().getResource(cardx1.getSuitImageStr()));
		opponentCard2 = new ImageIcon(getClass().getResource(cardy1.getSuitImageStr()));	
		commCard1 = new ImageIcon(getClass().getResource(carda.getSuitImageStr()));
		commCard2 = new ImageIcon(getClass().getResource(cardb.getSuitImageStr()));
		commCard3 = new ImageIcon(getClass().getResource(cardc.getSuitImageStr()));
		commCard4 = new ImageIcon(getClass().getResource(cardd.getSuitImageStr()));
		commCard5 = new ImageIcon(getClass().getResource(carde.getSuitImageStr()));
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 23)); 
		
		g = correctFontColor(cardx.getSuit(), g);

		cardBack.paintIcon(this, g, frameWidth/4, frameHeight/2-80);

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
		
		g = correctFontColor(carda.getSuit(), g);
		commCard1.paintIcon(this, g, frameWidth/4+93, frameHeight/2-80);
		g.drawString(carda.getFaceName(), frameWidth/4+120, frameHeight/2-50);
		
		g = correctFontColor(cardb.getSuit(), g);
		commCard2.paintIcon(this, g, frameWidth/4+186, frameHeight/2-80);
		g.drawString(cardb.getFaceName(), frameWidth/4+213, frameHeight/2-50);

		g = correctFontColor(cardc.getSuit(), g);
		commCard3.paintIcon(this, g, frameWidth/4+279, frameHeight/2-80);
		g.drawString(cardc.getFaceName(), frameWidth/4+306, frameHeight/2-50);
		
		g = correctFontColor(cardd.getSuit(), g);
		commCard4.paintIcon(this, g, frameWidth/4+372, frameHeight/2-80);
		g.drawString(cardd.getFaceName(), frameWidth/4+399, frameHeight/2-50);

		g.setColor(Color.BLACK);
		

	}
}
