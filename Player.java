public class Player
{
	private String type;
	private int moneyAvailable = 20000;
	private int currentBet = 0;
	private int winValue = 0;
	private int moneyContributed = 0;
	private int[] cpuDecision = {0, 0};
	private String winner;
	private boolean bigBlind = false; // false = smallBlind
	
	
	public Player(int option)
	{
		if (option == 1)
		{
			type = "Human";
		}
		else
		{
			type = "Computer";
		}
	}
	
	public void updateMoney(int num)
	{
		moneyAvailable = moneyAvailable - num;
		moneyContributed = moneyContributed + num;
		//return moneyAvailable;
	}
	
	public void rewardMoney(int pot)
	{
		moneyAvailable = moneyAvailable + pot;
	}
	
	public int getMoneyContributed()
	{
		return moneyContributed;
	}
	
	public void resetMoneyContributed()
	{
		moneyContributed = 0;
	}
	
	public int getMoneyAvailable()
	{
		return moneyAvailable;
	}
	
	public int getCurrentBet()
	{
		return currentBet;
	}
	public int getWinValue()
	{
		return winValue;
	}
	
	public boolean getBigBlind()
	{
		return bigBlind;
	}
	
	public int[] getCpuDecision()
	{
		return cpuDecision;
	}
	
	public void setBet(int sliderNum)
	{
		currentBet = sliderNum;
	}
	
	public void setWinValue(int value)
	{
		winValue = value;
	}
	
	public void setBigBlind(boolean bool)
	{
		bigBlind = bool;
	}
	
}
