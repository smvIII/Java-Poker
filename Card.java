import javax.swing.ImageIcon;
import java.util.List;
import java.util.Arrays;

public class Card
{
	private String faceName, suit, suitImageStr;
	private ImageIcon suitImage;
	private ImageIcon backImage;
	
	public Card(String faceName, String suit)
	{
		setFaceName(faceName);
		setSuit(suit);
		backImage = new ImageIcon("cardback.png");
		setSuitImage(suit);
		
	}
	
	public Card()
	{
		/*
		faceName = "2";
		suit = "spades";
		backImage = new ImageIcon("cardback.png");
		setSuit(suit);*/
	}
	
	public void setFaceName(String faceName)
	{
		List<String> validFaceNames = getValidFaceNames();
		if (validFaceNames.contains(faceName))
		{
			this.faceName = faceName;
		}
	}
	
	public void setSuit(String suit)
	{
		List<String> validSuits = getValidSuits();
		if (validSuits.contains(suit))
		{
			this.suit = suit;
		}
	}
	
	public void setSuitStr(String suit)
	{
		switch (suit)
		{
			case "spades":
			{
				this.suitImageStr = "spadecard.png";
				break;
			}
			case "clubs":
			{
				this.suitImageStr = "clubcard.png";
				break;
			}
			case "hearts":
			{
				this.suitImageStr = "heartcard.png";
				break;
			}
			case "diamonds": 
			{
				this.suitImageStr = "diamondcard.png";
				break;
			}
		}
	}
	
	public void setSuitImage(String suit)
	{
		switch (suit)
		{
			case "spades":
			{
				this.suitImage = new ImageIcon("spadecard.png");
				break;
			}
			case "clubs":
			{
				this.suitImage = new ImageIcon("clubcard.png");
				break;
			}
			case "hearts":
			{
				this.suitImage = new ImageIcon("heartcard.png");
				break;
			}
			case "diamonds": 
			{
				this.suitImage = new ImageIcon("diamondcard.png");
				break;
			}
		}
	}
	
	public Card equals(Card card)
	{
		this.faceName = card.faceName;
		this.suit = card.suit;
		//System.out.println("WOWOOW"+this.suit);
		setSuitStr(this.suit);
		//System.out.println(this.suitImageStr);
		return this;
	}
	
	public String getFaceName()
	{
		return this.faceName;
	}
	
	public static int getFaceNameValue(String face)
	{
			switch(face)
			{
				case "A":
				{
					return 14;
				}
				case "K":
				{
					return 13;
				}
				case "Q":
				{
					return 12;
				}
				case "J":
				{
					return 11;
				}
				case "10":
				{
					return 10;
				}
				case "9":
				{
					return 9;
				}
				case "8":
				{
					return 8;
				}
				case "7":
				{
					return 7;
				}
				case "6":
				{
					return 6;
				}
				case "5":
				{
					return 5;
				}
				case "4":
				{
					return 4;
				}
				case "3":
				{
					return 3;
				}
				case "2":
				{
					return 2;
				}
			}
			return 0;
		}
					
	
	public String getSuit()
	{
		return this.suit;
	}
	
	public String getSuitImageStr()
	{
		return this.suitImageStr;
	}
	
	
	public ImageIcon getSuitImage()
	{
		return this.suitImage;
	}
	
	public static List<String> getValidFaceNames()
	{
		return Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9",
							"10", "J", "Q", "K", "A");
	}
	
	public static List<String> getValidSuits()
	{
		return Arrays.asList("spades", "clubs", "hearts", "diamonds");
	}
	
	public String toString()
	{
		return String.format("%s of %s", faceName, suit);
	} 
	
	
	
	
}
