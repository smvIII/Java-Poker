import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// RENAME THIS TO CARDDECK
public class DeckOfCards
{
	private static ArrayList<Card> cardDeck;
	
	public DeckOfCards()
	{
		List<String> suits = Card.getValidSuits();
		
		List<String> faceNames = Card.getValidFaceNames();
		
		cardDeck = new ArrayList<>();
		
		
		for(String suit:suits)
		{
			for(String faceName:faceNames)
			{
				cardDeck.add(new Card(faceName, suit));
			}
		}
				
		
	}
	
	public String getIndexFaceName(int index)
	{
		return cardDeck.get(index).getFaceName();
	}
	
	public void printDeck()
	{
		for(int i = 0; i < cardDeck.size(); i++)
		{
			 System.out.println(cardDeck.get(i).getFaceName() + " of " + cardDeck.get(i).getSuit());
		}
	}
	
	public Card dealTopCard()
	{
			return cardDeck.remove(0);
	}
	
	public void shuffle()
	{
		Collections.shuffle(cardDeck);
	}
	
	
	
}
