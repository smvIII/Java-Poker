//rename this to GameMethods

public class GameMethod
{
	static int roundCounter = 1;
	static int potMoney = 0;
	static String winner = "";
	static String winningHand = "";
	static boolean callPresent = false;
	
	
	static void incrementRound()
	{
		roundCounter++;
	}
			
	public static void resetCounter()
	{
		roundCounter = 1;
	}
	
	public static String setWinnerStr(int option) // 1 == human won 2 == computer won
	{
		if (option == 1)
		{
			winner = "player";
		}
		if (option == 2)
		{
			winner = "computer";
		}
		if (option == 3)
		{
			winner = "It's a tie! The pot will be split.";
		}
		return winner;
	}
	
	public static void addBetToPot(int bet)
	{
		potMoney = potMoney + bet;
	}
	
	public static void addBlindToPot(int blind)
	{
		potMoney = potMoney + blind;
	}
	
	public static void resetPot()
	{
		potMoney = 0;	
	}
	
	public int getPotMoney()
	{
		return potMoney;
	}
	
	public boolean getCallPresent()
	{
		return callPresent;
	}
	
	public static void setCallPresent(boolean bool)
	{
		callPresent = bool;
	}
	public static String setWinningHandStr(int winValue)
	{
		
		if (winValue < 8421377 && winValue > 4210689)
		{
			winningHand = "royal flush";
			
		}
		else if (winValue < 4210689 && winValue > 2129921)
		{
			winningHand = "straight flush";
		}
		else if (winValue < 2129921 && winValue > 1081345)
		{
			winningHand = "four of a kind";
		}
		else if (winValue < 1081345 && winValue > 557057)
		{
			winningHand = "full house";
		}
		else if (winValue < 557057 && winValue > 294913)
		{
			winningHand = "flush";
		}
		else if (winValue < 294913 && winValue > 163841)
		{
			winningHand = "straight";
		}
		else if (winValue < 163841 && winValue > 98305)
		{
			winningHand = "three of a kind";
		}
		else if (winValue < 98305 && winValue > 65537)
		{
			winningHand = "two pair";
		}
		else if (winValue < 65537 && winValue > 32769)
		{
			winningHand = "pair";
		}
		else
		{
			winningHand = "high card";
		}
		
		return winningHand;
			
			
			
	}
	
	
}
	
	
