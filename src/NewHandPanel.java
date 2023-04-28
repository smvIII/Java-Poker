import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;



public class NewHandPanel extends CardPanel //implements ActionListener
{
	
	public void currentHand(Card card1, Card card2)
	{
		cardx = cardx.equals(card1);
		cardy = cardy.equals(card2);
		//System.out.println("hi"+cardy.getSuitImageStr());
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		frameHeight = getFrameHeightOrWidth(this, 0);
		frameWidth = getFrameHeightOrWidth(this, 1);
		
		//handCard1 = new ImageIcon(cardx.getSuitImageStr());
		//handCard2 = new ImageIcon(cardy.getSuitImageStr());
		handCard1 = new ImageIcon(getClass().getResource(cardx.getSuitImageStr()));
		handCard2 = new ImageIcon(getClass().getResource(cardy.getSuitImageStr()));
		cardBack = new ImageIcon(getClass().getResource("cardback.png"));
		//cardBack = new ImageIcon("cardback.png");
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 23)); 
		cardBack.paintIcon(this, g, frameWidth/4, frameHeight/2-80);
		
		g = correctFontColor(cardx.getSuit(), g);
		handCard1.paintIcon(this, g, frameWidth-210, frameHeight-140);
		g.drawString(cardx.getFaceName(), frameWidth-183, frameHeight-110);
		
		g = correctFontColor(cardy.getSuit(), g);
		handCard2.paintIcon(this, g, frameWidth-117, frameHeight-140);
		g.drawString(cardy.getFaceName(), frameWidth-90, frameHeight-110);
		
		cardBack.paintIcon(this, g, frameWidth/2-166, frameHeight-140);
		cardBack.paintIcon(this, g, frameWidth/2-73, frameHeight-140);
		cardBack.paintIcon(this, g, frameWidth/2-166, frameHeight/15);
		cardBack.paintIcon(this, g, frameWidth/2-73, frameHeight/15);
		
		g.setColor(Color.BLACK);
	}
}


