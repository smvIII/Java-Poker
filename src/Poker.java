// *********************************************************************************************************************
// *   Program: Poker                                                                                                  *
// *   Author: Stanley Vossler                                                                                         *
// *   FSU Mail name: smv19c                                                                                           *
// *   COP 3252 - Summer 2022                                                                                          *
// *   Project Number: X                                                                                               *
// *   Due Date: Monday 7/25/2022                                                                                      *
// *   Platform: java / UNIX OS                                                                                        *
// *                                                                                                                   *
// *   SUMMARY                                                                                                         *
// *   This program is the final term project for COP3252 Summer 2022 section. All of its components are written solely*
// *   by Stanley Vossler. All images used in this program were made by Stanley Vossler on pixilart.com. This program  *
// *   implements a 2 player of the Texas-Holdem style of poker. The game is meant to be played solo as the 2nd player *
// *   is an AI. The standard rules of that game are applied and implemented. The programs works by adding and removin-*
// *   g a series of custom made JPanels for each phase of the game.The program utilizes 13 different java source code *
// *   files and 6 custom made png files for the cards images and main menu background.                                *
// *********************************************************************************************************************

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JPanel;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.lang.Math;


public class Poker
{
	public static void main(String[] args)
    {
		int roundCounter = 1;
		Player humanPlayer = new Player(1);
		Player computerPlayer = new Player(0);
		
		// EXTRA FEATURE APART FROM IMPLEMENTAITON main menu
		// custom drawn png file for mmp.
		MainMenuPanel mmp = new MainMenuPanel();
		JButton newGameButton = new JButton("New Game");
		
		GameFrame gf = new GameFrame();
		
		
		gf.add(mmp);
		
		newGameButton.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed( ActionEvent event )
				{
					gf.getContentPane().removeAll();
					newRound(humanPlayer, computerPlayer, gf);
				}
			}
		);
		
		mmp.add(newGameButton);
		SwingUtilities.updateComponentTreeUI(gf);
    }
	
	public static int getWinValue(Card hand1, Card hand2, Card cc1, Card cc2, Card cc3, Card cc4, Card cc5)
	{
		String[] strFaceNames = new String[] {"2", "3", "4", "5", "6", "7", "8", "9",
							"10", "J", "Q", "K", "A"};
							
		String[] strSuits = new String[] {"spades", "clubs", "hearts", "diamonds"};
		Card[] playerCards =   new Card[] {hand1, hand2, cc1, cc2, cc3, cc4, cc5};
		String[] playerFaceNamesStr = new String[playerCards.length];
		int[] playerFaceValues = new int[playerCards.length];
		Card[] communityCards = new Card[]{cc1, cc2, cc3, cc4, cc5};
		
		Hashtable<String, Integer> faceNameHash = new Hashtable<String, Integer>();
		for (int i = 0; i < strFaceNames.length; i++)
		{
			faceNameHash.put(strFaceNames[i], 0);
		}
		
		Hashtable<String, Integer> suitHash = new Hashtable<String, Integer>();
		for (int i = 0; i< strSuits.length; i++)
		{
			suitHash.put(strSuits[i], 0);
		}
		
		int straightCounter = 1;
		int winValue = 0;
		int highBonus = getHighOrLow(hand1, hand2, 1);
		int lowBonus = getHighOrLow(hand1, hand2, 2);
		
		String cardSuits[] = new String[2];
		int handCounter = 0;
		boolean royalFlush, straightFlush, fourOK, fullHouse, flush, straight,
				threeOK, twoPair, pair;
				
		pair = false;
		twoPair = false;
		threeOK = false;
		fourOK = false;
		fullHouse = false;
		flush = false;
		straight = false;
		straightFlush = false;
		royalFlush = false;
	
	
		for (int i = 0; i < playerFaceNamesStr.length; i++)
		{
			playerFaceNamesStr[i] = playerCards[i].getFaceName();
			playerFaceValues[i] = Card.getFaceNameValue(playerFaceNamesStr[i]);
		}
		
		// order by face value
		Arrays.sort(playerFaceValues);



		for(int i = 0; i < playerCards.length; i++)
		{
			if (faceNameHash.containsKey(playerCards[i].getFaceName()))
			{
				faceNameHash.merge(playerCards[i].getFaceName(), 1, Integer::sum);
			}
		}
		
		for(int i = 0; i < playerCards.length; i++)
		{
			if (suitHash.containsKey(playerCards[i].getSuit()))
			{
				suitHash.merge(playerCards[i].getSuit(), 1, Integer::sum);
			}
		}

		// straight counter													
		for (int i = 0; i < playerFaceValues.length; i++)
		{
			if (i != playerFaceValues.length-1) 
			{
				if (playerFaceValues[i] + 1 == playerFaceValues[i+1])
				{
					System.out.println(straightCounter);
					straightCounter++;
				}
				else if ( straightCounter < 4)
				{
					straightCounter = 0;
				}
			}
		}
		
		if ( straightCounter > 4 ) // straight
		{
			straight = true;
			winValue = (int)(Math.pow(2, 18) + highBonus + lowBonus);
			if (suitHash.containsValue(5) == true)
			{
				straight = false;
				flush = false;
				straightFlush = true;
				winValue = (int)(Math.pow(2, 22) + highBonus + lowBonus);
			}
		}

		if (suitHash.containsValue(5) == true && !straight && !straightFlush) // flush check 
		{
			winValue = (int)(Math.pow(2, 19) + highBonus + lowBonus);;
			flush = true;
			// check straight flush
			
		}
		
		
		if (faceNameHash.containsValue(4) == true) // four of a kind check
		{
			winValue = (int)(Math.pow(2, 21) + highBonus + lowBonus);
			fourOK = true;
		}
		
		if (faceNameHash.containsValue(3) == true && !straight) // three of a kind check
		{
			winValue = (int)(Math.pow(2, 17) + highBonus + lowBonus);
			threeOK = true;
			if(faceNameHash.containsValue(2) == true)
			{
				winValue =  (int)(Math.pow(2, 20) + highBonus + lowBonus);
				threeOK = false;
				fullHouse =true;
			}
		}
		
		String hashString = faceNameHash.toString(); // two pair counter
		int pairCount = 0;
		for (int i = 0; i < hashString.length(); i++)
		{	
			if (hashString.charAt(i) == '=' && i != hashString.length())
			{
				if (hashString.charAt(i+1) == '2')
				{
					pairCount++;
				}
			}
			
		}
		// two pair
		if (pairCount > 1 && !threeOK && !fullHouse && !twoPair && !straight && !flush && !straightFlush)
		{
			twoPair = true;
			winValue = (int)(Math.pow(2, 16) + highBonus + lowBonus);
		}
		
		if (faceNameHash.containsValue(2) == true && !threeOK && !fullHouse && !twoPair && !straight && !flush && !straightFlush) // pair
		{
			pair = true;
			
			winValue = (int)(Math.pow(2, 15) + highBonus + lowBonus);
		} // pair
		
		if (!pair && !twoPair && !threeOK && !fourOK && !fullHouse && !flush && !straight && !straightFlush) // high
		{
			winValue = highBonus + lowBonus;
		}
			

	//	System.out.println("straightFlush is " + straightFlush);
	//	System.out.println("straight is " + straight);
	//	System.out.println("flush is " + flush);
	//	System.out.println("full house is " + fullHouse);
	//	System.out.println("four of a kind is " + fourOK);
	//	System.out.println("three of a kind is " + threeOK);
	//	System.out.println("twopair is " + twoPair);
	//	System.out.println("pair is " + pair);
	//	System.out.println("the win value is "+ winValue);
		return winValue;
		
	
	}


	public static int getHighOrLow(Card hand1, Card hand2, int option) // 1 for high 2 for low
	{
		int playerHighOrLow = 0;
		double temp1;
		double temp2;
	
		temp1 = Card.getFaceNameValue(hand1.getFaceName());
		temp2 = Card.getFaceNameValue(hand2.getFaceName());
		
		if (option == 1)
		{
			if (temp1 > temp2)
			{
				playerHighOrLow = (int)(Math.pow(2, temp1));
			}
			else if (temp2 > temp1)
			{
				playerHighOrLow = (int)(Math.pow(2, temp2));
			}
			else
			{
				playerHighOrLow = (int)(Math.pow(2, temp1));
			}
			return playerHighOrLow;
		}
		else
		{
			if (temp1 < temp2)
			{
				playerHighOrLow = (int)(Math.pow(2, temp1));
			}
			else if (temp2 < temp1)
			{
				playerHighOrLow = (int)(Math.pow(2, temp2));
			}
			else
			{
				playerHighOrLow = (int)(Math.pow(2, temp1));
			}
			return playerHighOrLow;
		}
	}
	
	static void newRound(Player humanPlayer, Player computerPlayer, GameFrame gf)
	{
		String[] checkOrCall = new String[] {"Check", "Call"};
		String[] betOrRaise = new String[] {"Bet", "Raise"};
		String winner;

		GameMethod method = new GameMethod();
		
		humanPlayer.resetMoneyContributed();
		computerPlayer.resetMoneyContributed();
		
		
		if (humanPlayer.getBigBlind() == false) 
		{
			
			humanPlayer.setBigBlind(true);
			humanPlayer.updateMoney(600);
			System.out.println("The player puts in $600 blinds to the pot");
			
			computerPlayer.setBigBlind(false);
			computerPlayer.updateMoney(300);
			System.out.println("The computer puts in $300 blinds to the pot");
			
			method.addBlindToPot(900);
		}
		else
		{
			humanPlayer.setBigBlind(false);
			humanPlayer.updateMoney(300);
			System.out.println("The player puts in $300 blinds to the pot");
			
			computerPlayer.setBigBlind(true);
			computerPlayer.updateMoney(600);
			System.out.println("The computer puts in $600 blinds to the pot");
			
			method.addBlindToPot(900);
		}
		// Every turn, whoever has big/small blinds swaps.
		
		DeckOfCards deck = new DeckOfCards();
		deck.shuffle();
		
		JButton nextRoundButton = new JButton();
		Card hand1 = deck.dealTopCard();
		Card hand2 = deck.dealTopCard();
		
		Card opponent1 = deck.dealTopCard();
		Card opponent2 = deck.dealTopCard();
		
		Card cc1 = deck.dealTopCard();
		Card cc2 = deck.dealTopCard();
		Card cc3 = deck.dealTopCard();
		Card cc4 = deck.dealTopCard();
		Card cc5 = deck.dealTopCard();

		SwingUtilities.updateComponentTreeUI(gf);
		NewHandPanel nhp = new NewHandPanel();
		nhp.currentHand(hand1, hand2);
		
		JPanel southGamePanel = new JPanel();
		JPanel northGamePanel = new JPanel();
		
		southGamePanel.setLayout(new GridLayout());
		
		JButton foldButton = new JButton("Fold");
		JButton checkOrCallButton = new JButton("Check");
		JButton betOrRaiseButton = new JButton("Bet");
		foldButton.setFont( new Font("TimesRoman", Font.PLAIN, 20));
		checkOrCallButton.setFont( new Font("TimesRoman", Font.PLAIN, 20));
		betOrRaiseButton.setFont( new Font("TimesRoman", Font.PLAIN, 20));
		
		if(computerPlayer.getMoneyContributed() > humanPlayer.getMoneyContributed())
		{
			checkOrCallButton.setText("Call $"+ (computerPlayer.getMoneyContributed() - humanPlayer.getMoneyContributed()));
		}
		
		JPanel leftTextPanel = new JPanel();
		JPanel rightTextPanel = new JPanel();
		JLabel moneyLabel = new JLabel();
		JLabel potLabel = new JLabel();
		JLabel cpuActivityLabel = new JLabel("Player's Turn");

		moneyLabel.setText("Money: $" + humanPlayer.getMoneyAvailable());
		potLabel.setText("Bet: "+humanPlayer.getCurrentBet() + " Pot: $" + method.getPotMoney());
		
		moneyLabel.setFont( new Font("TimesRoman", Font.BOLD, 16));
		potLabel.setFont( new Font("TimesRoman", Font.BOLD, 16));
		cpuActivityLabel.setFont( new Font("TimesRoman", Font.BOLD, 16));
		
		JSlider increaseBetSlider = new JSlider();
		increaseBetSlider.setValue(0);
			
		leftTextPanel.add(moneyLabel);
		rightTextPanel.add(potLabel);
			
		southGamePanel.add(leftTextPanel);
		northGamePanel.add(cpuActivityLabel);
	
		gf.add(nhp, BorderLayout.CENTER);
		
		southGamePanel.add(foldButton);
		southGamePanel.add(checkOrCallButton);
		southGamePanel.add(betOrRaiseButton);
		southGamePanel.add(increaseBetSlider);
		southGamePanel.add(rightTextPanel);
		
		gf.add(southGamePanel, BorderLayout.SOUTH);
		gf.add(northGamePanel, BorderLayout.NORTH);
		
		
		
		foldButton.addActionListener //FOLD
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					
					computerPlayer.rewardMoney(method.getPotMoney());
					addNextRoundButton(gf, southGamePanel, method, humanPlayer, computerPlayer, method.setWinnerStr(1), method.setWinningHandStr(computerPlayer.getWinValue()), true, false, moneyLabel);
					method.resetPot();
					SwingUtilities.updateComponentTreeUI(gf);
				}	
			}
		);
		
		if (humanPlayer.getBigBlind() == true) // if cpu first 
		{
			cpuTurn(humanPlayer, computerPlayer, opponent1, opponent2, cc1, cc2, cc3, cc4, cc5, gf,
			southGamePanel, cpuActivityLabel, potLabel, checkOrCallButton, moneyLabel, increaseBetSlider,
			method, humanPlayer.getBigBlind());
		} 
		else
		{
			checkOrCallButton.setText("Check");
		}

		checkOrCallButton.addActionListener //CHECKorCALL
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					method.incrementRound();
					// think of this as a loop every time the button is pressed!!!!
					if (computerPlayer.getMoneyContributed() > humanPlayer.getMoneyContributed())
					{
						method.setCallPresent(true);
					}
					else if (computerPlayer.getMoneyContributed() - humanPlayer.getMoneyContributed() != 0 && humanPlayer.getMoneyContributed() < computerPlayer.getMoneyContributed())
					{
							checkOrCallButton.setText("Call $"+ (computerPlayer.getMoneyContributed() - humanPlayer.getMoneyContributed()));
					}
					
					
					if (humanPlayer.getBigBlind() == true && method.roundCounter != 5) // if cpu first 
					{
						method.addBetToPot(computerPlayer.getMoneyContributed() - humanPlayer.getMoneyContributed());
						humanPlayer.updateMoney(computerPlayer.getMoneyContributed() - humanPlayer.getMoneyContributed());
						moneyLabel.setText("Money: $" + humanPlayer.getMoneyAvailable());
						method.setCallPresent(false);
							
						humanPlayer.resetMoneyContributed();
						computerPlayer.resetMoneyContributed();
						cpuTurn(humanPlayer, computerPlayer, opponent1, opponent2, cc1, cc2, cc3, cc4, cc5, gf, southGamePanel, cpuActivityLabel, potLabel, checkOrCallButton, moneyLabel,increaseBetSlider,  method, humanPlayer.getBigBlind());
					}
					else
					{
						checkOrCallButton.setText("Check");
					}
					
					// END CHECK/CALL LOOP***************************************
					
					ThreeCardPanel tcp = new ThreeCardPanel();
					if (method.roundCounter == 2) // three card panel
					{
						
						gf.remove(nhp);
						SwingUtilities.updateComponentTreeUI(gf);
						tcp.currentHand(hand1, hand2);
						tcp.currentCommunityCards(cc1, cc2, cc3);
						
						if (humanPlayer.getBigBlind() == false) // if player first 
						{
							humanPlayer.resetMoneyContributed();
							computerPlayer.resetMoneyContributed();
							cpuTurn(humanPlayer, computerPlayer, opponent1, opponent2, cc1, cc2, cc3, cc4, cc5, gf, southGamePanel, cpuActivityLabel, potLabel, checkOrCallButton, moneyLabel, increaseBetSlider, method, true);
						} 
						
						
						potLabel.setText("Bet: $"+humanPlayer.getCurrentBet() + " Pot: $" + method.getPotMoney());
						gf.add(tcp, BorderLayout.CENTER);
					}	
					else if (method.roundCounter == 3)
					{
						
						gf.getContentPane().removeAll();

						gf.add(northGamePanel);
						FourCardPanel focp = new FourCardPanel();
						focp.currentHand(hand1, hand2, opponent1, opponent2);
						focp.currentCommunityCards(cc1, cc2, cc3, cc4, cc5);
						gf.add(focp, BorderLayout.CENTER);
						gf.add(southGamePanel, BorderLayout.SOUTH);
						
						if (humanPlayer.getBigBlind() == false) // if player first 
						{
							humanPlayer.resetMoneyContributed();
							computerPlayer.resetMoneyContributed();
							cpuTurn(humanPlayer, computerPlayer, opponent1, opponent2, cc1, cc2, cc3, cc4, cc5, gf, southGamePanel, cpuActivityLabel, potLabel, checkOrCallButton, moneyLabel, increaseBetSlider, method, true);
						} 
						
						potLabel.setText("Bet: $"+humanPlayer.getCurrentBet() + " Pot: $" + method.getPotMoney());
						SwingUtilities.updateComponentTreeUI(gf);
					}
					else if (method.roundCounter == 4)
					{
						gf.getContentPane().removeAll();
						FiveCardPanel ficp = new FiveCardPanel();
						ficp.currentHand(hand1, hand2, opponent1, opponent2);
						ficp.currentCommunityCards(cc1, cc2, cc3, cc4, cc5);
						
						if (humanPlayer.getBigBlind() == false) // if player first 
						{
							humanPlayer.resetMoneyContributed();
							computerPlayer.resetMoneyContributed();
							cpuTurn(humanPlayer, computerPlayer, opponent1, opponent2, cc1, cc2, cc3, cc4, cc5, gf, southGamePanel, cpuActivityLabel, potLabel, checkOrCallButton, moneyLabel, increaseBetSlider, method, true);
						} 
						
						gf.add(ficp, BorderLayout.CENTER);
						gf.add(southGamePanel, BorderLayout.SOUTH);
						gf.add(northGamePanel, BorderLayout.NORTH);
						SwingUtilities.updateComponentTreeUI(gf);
						
						potLabel.setText("Bet: $"+humanPlayer.getCurrentBet() + " Pot: $" + method.getPotMoney());
					}
					else
					{
						gf.getContentPane().removeAll();
						FinalCardPanel finalcp = new FinalCardPanel();
						finalcp.currentHand(hand1, hand2, opponent1, opponent2);
						finalcp.currentCommunityCards(cc1, cc2, cc3, cc4, cc5);
						humanPlayer.setWinValue(getWinValue(hand1, hand2, cc1, cc2, cc3, cc4, cc5));
						computerPlayer.setWinValue(getWinValue(opponent1, opponent2, cc1, cc2, cc3, cc4, cc5));
						
						if (humanPlayer.getBigBlind() == false) // if player first 
						{
							humanPlayer.resetMoneyContributed();
							computerPlayer.resetMoneyContributed();
							cpuTurn(humanPlayer, computerPlayer, opponent1, opponent2, cc1, cc2, cc3, cc4, cc5, gf, southGamePanel, cpuActivityLabel, potLabel, checkOrCallButton, moneyLabel, increaseBetSlider, method, true);
						} 
						
						gf.add(finalcp);
						gf.add(southGamePanel, BorderLayout.SOUTH);
						gf.add(northGamePanel, BorderLayout.NORTH);
						SwingUtilities.updateComponentTreeUI(gf);
									
						if (humanPlayer.getWinValue() > computerPlayer.getWinValue())
						{
							System.out.println("The player wins the hand!");
							if (computerPlayer.getMoneyAvailable() > 0)
							{
								humanPlayer.rewardMoney(method.getPotMoney());
								moneyLabel.setText("Money: $" + humanPlayer.getMoneyAvailable());
								addNextRoundButton(gf, southGamePanel, method, humanPlayer, computerPlayer, method.setWinnerStr(1), method.setWinningHandStr(humanPlayer.getWinValue()), false, false, moneyLabel);
								//
								
								method.resetPot();

							}
						}
						else if (humanPlayer.getWinValue() < computerPlayer.getWinValue())
						{
							System.out.println("The computer wins the hand!");
							if (humanPlayer.getMoneyAvailable() > 0)
							{
								computerPlayer.rewardMoney(method.getPotMoney());
								addNextRoundButton(gf, southGamePanel, method, humanPlayer, computerPlayer, method.setWinnerStr(2), method.setWinningHandStr(computerPlayer.getWinValue()), false, false, moneyLabel);
								method.resetPot();
							
							}
						}
						else if (humanPlayer.getWinValue() == computerPlayer.getWinValue())
						{
							System.out.println("It's a tie!");
							computerPlayer.rewardMoney(method.getPotMoney()/2);
							humanPlayer.rewardMoney(method.getPotMoney()/2);
							addNextRoundButton(gf, southGamePanel, method, humanPlayer, computerPlayer, method.setWinnerStr(2), method.setWinningHandStr(computerPlayer.getWinValue()), false, true, moneyLabel);
						}
						SwingUtilities.updateComponentTreeUI(gf);
					}
				}
							
			}
		);
		//*********   END OF CHECK OR CALL    ***********
		
		
		betOrRaiseButton.addActionListener //BET
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					
					if (computerPlayer.getMoneyContributed() > humanPlayer.getMoneyContributed())
					{
						method.setCallPresent(true);
					}
					else if (computerPlayer.getMoneyContributed() - humanPlayer.getMoneyContributed() != 0 && humanPlayer.getMoneyContributed() < computerPlayer.getMoneyContributed())
					{
							checkOrCallButton.setText("Call $"+ (computerPlayer.getMoneyContributed() - humanPlayer.getMoneyContributed()));
					}
					
					
					if (humanPlayer.getBigBlind() == true) // if cpu first 
					{
						method.addBetToPot(computerPlayer.getMoneyContributed() - humanPlayer.getMoneyContributed());
						humanPlayer.updateMoney(computerPlayer.getMoneyContributed() - humanPlayer.getMoneyContributed());
						moneyLabel.setText("Money: $" + humanPlayer.getMoneyAvailable());
						method.setCallPresent(false);
							
						humanPlayer.resetMoneyContributed();
						computerPlayer.resetMoneyContributed();
						cpuTurn(humanPlayer, computerPlayer, opponent1, opponent2, cc1, cc2, cc3, cc4, cc5, gf, southGamePanel, cpuActivityLabel, potLabel, checkOrCallButton, moneyLabel,increaseBetSlider,  method, false);
					}
					else
					{
						checkOrCallButton.setText("Check");
					}
					
					method.incrementRound();
					ThreeCardPanel tcp = new ThreeCardPanel();
					
					
					
					// END BET LOOP ***********************************
					if (method.roundCounter == 2)
					{
						gf.remove(nhp);
						SwingUtilities.updateComponentTreeUI(gf);
						tcp.currentHand(hand1, hand2);
						tcp.currentCommunityCards(cc1, cc2, cc3);
						humanPlayer.updateMoney(humanPlayer.getCurrentBet());
						method.addBetToPot(humanPlayer.getCurrentBet());
						moneyLabel.setText("Money: $" + humanPlayer.getMoneyAvailable());
						
						if (humanPlayer.getBigBlind() == false) // if player first 
						{
							humanPlayer.resetMoneyContributed();
							computerPlayer.resetMoneyContributed();
							cpuTurn(humanPlayer, computerPlayer, opponent1, opponent2, cc1, cc2, cc3, cc4, cc5, gf, southGamePanel, cpuActivityLabel, potLabel, checkOrCallButton, moneyLabel, increaseBetSlider, method, true);
						} 
						
						potLabel.setText("Bet: "+humanPlayer.getCurrentBet() + " Pot: $" + method.getPotMoney());
						gf.add(tcp, BorderLayout.CENTER);
						SwingUtilities.updateComponentTreeUI(gf);
					}	
					else if (method.roundCounter == 3) // bet or raise
					{
						gf.getContentPane().removeAll();
						FourCardPanel focp = new  FourCardPanel();
						focp.currentHand(hand1, hand2, opponent1, opponent2);
						focp.currentCommunityCards(cc1, cc2, cc3, cc4, cc5);
						humanPlayer.updateMoney(humanPlayer.getCurrentBet());
						method.addBetToPot(humanPlayer.getCurrentBet());
						moneyLabel.setText("Money: $" + humanPlayer.getMoneyAvailable());
						potLabel.setText("Bet: "+humanPlayer.getCurrentBet() + " Pot: $" + method.getPotMoney());
						
						if (humanPlayer.getBigBlind() == false) // if player first 
						{
							humanPlayer.resetMoneyContributed();
							computerPlayer.resetMoneyContributed();
							cpuTurn(humanPlayer, computerPlayer, opponent1, opponent2, cc1, cc2, cc3, cc4, cc5, gf, southGamePanel, cpuActivityLabel, potLabel, checkOrCallButton, moneyLabel, increaseBetSlider, method, true);
						} 
						
						gf.add(focp, BorderLayout.CENTER);
						gf.add(southGamePanel, BorderLayout.SOUTH);
						gf.add(northGamePanel, BorderLayout.NORTH);
						SwingUtilities.updateComponentTreeUI(gf);
					}
					else if (method.roundCounter == 4)			// bet or raise
					{			
						
						gf.getContentPane().removeAll();
						FiveCardPanel ficp = new  FiveCardPanel();
						ficp.currentHand(hand1, hand2, opponent1, opponent2);
						ficp.currentCommunityCards(cc1, cc2, cc3, cc4, cc5);
						
						if (humanPlayer.getBigBlind() == false) // if player first 
						{
							humanPlayer.resetMoneyContributed();
							computerPlayer.resetMoneyContributed();
							cpuTurn(humanPlayer, computerPlayer, opponent1, opponent2, cc1, cc2, cc3, cc4, cc5, gf, southGamePanel, cpuActivityLabel, potLabel, checkOrCallButton, moneyLabel, increaseBetSlider, method, true);
						} 
			
						gf.add(ficp, BorderLayout.CENTER);
						gf.add(southGamePanel, BorderLayout.SOUTH);
						gf.add(northGamePanel, BorderLayout.NORTH);
						SwingUtilities.updateComponentTreeUI(gf);
						potLabel.setText("Bet: $"+humanPlayer.getCurrentBet() + " Pot: $" + method.getPotMoney());
					}
					else 
					{

						gf.getContentPane().removeAll();
						FinalCardPanel finalcp = new FinalCardPanel();
						finalcp.currentHand(hand1, hand2, opponent1, opponent2);
						finalcp.currentCommunityCards(cc1, cc2, cc3, cc4, cc5);
						humanPlayer.setWinValue(getWinValue(hand1, hand2, cc1, cc2, cc3, cc4, cc5));
						computerPlayer.setWinValue(getWinValue(opponent1, opponent2, cc1, cc2, cc3, cc4, cc5));
						
						if (humanPlayer.getBigBlind() == false) // if player first 
						{
							humanPlayer.resetMoneyContributed();
							computerPlayer.resetMoneyContributed();
							cpuTurn(humanPlayer, computerPlayer, opponent1, opponent2, cc1, cc2, cc3, cc4, cc5, gf, southGamePanel, cpuActivityLabel, potLabel, checkOrCallButton, moneyLabel, increaseBetSlider, method, true);
						} 
						
						gf.add(finalcp);
						gf.add(southGamePanel, BorderLayout.SOUTH);
						gf.add(northGamePanel, BorderLayout.NORTH);
						SwingUtilities.updateComponentTreeUI(gf);
									
						if (humanPlayer.getWinValue() > computerPlayer.getWinValue())
						{
							System.out.println("The player wins the hand!");
							if (computerPlayer.getMoneyAvailable() > 0)
							{
								humanPlayer.rewardMoney(method.getPotMoney());
								moneyLabel.setText("Money: $" + humanPlayer.getMoneyAvailable());
								addNextRoundButton(gf, southGamePanel, method, humanPlayer, computerPlayer, method.setWinnerStr(1), method.setWinningHandStr(humanPlayer.getWinValue()), false, false, moneyLabel);
								//
								
								method.resetPot();

							}
						}
						else if (humanPlayer.getWinValue() < computerPlayer.getWinValue())
						{
							System.out.println("The computer wins the hand!");
							if (humanPlayer.getMoneyAvailable() > 0)
							{
								computerPlayer.rewardMoney(method.getPotMoney());
								addNextRoundButton(gf, southGamePanel, method, humanPlayer, computerPlayer, method.setWinnerStr(2), method.setWinningHandStr(computerPlayer.getWinValue()), false, false, moneyLabel);
								method.resetPot();
							
							}
						}
						else if (humanPlayer.getWinValue() == computerPlayer.getWinValue())
						{
							System.out.println("It's a tie!");
							computerPlayer.rewardMoney(method.getPotMoney()/2);
							humanPlayer.rewardMoney(method.getPotMoney()/2);
							addNextRoundButton(gf, southGamePanel, method, humanPlayer, computerPlayer, method.setWinnerStr(2), method.setWinningHandStr(computerPlayer.getWinValue()), false, true, moneyLabel);
							
						}
					
					}
				}
			}
		);
		// ***************    END OF BET    ******************
		
		increaseBetSlider.addChangeListener
		(
			new ChangeListener()
			{
				public void stateChanged(ChangeEvent event)
				{
					increaseBetSlider.setMaximum(humanPlayer.getMoneyAvailable());
					humanPlayer.setBet(increaseBetSlider.getValue());
					potLabel.setText("Bet: $"+increaseBetSlider.getValue()+ " |  Pot: $" + method.getPotMoney());
				}
			}
		);

}
	public static void cpuTurn(Player humanPlayer, Player computerPlayer, Card opponent1, Card opponent2,
	Card cc1, Card cc2, Card cc3, Card cc4, Card cc5, GameFrame gf, JPanel southGamePanel,
	JLabel cpuActivityLabel, JLabel potLabel, JButton checkOrCallButton, JLabel moneyLabel,
	JSlider increaseBetSlider, GameMethod method, boolean playerFirst)
	{
		
		int decision;
		int betMultiple;
		int bet;
		int call = 0;
		int allInChance;
		int foldChance;
		
		
		Random rand = new Random();
		decision = rand.nextInt(2);
		foldChance  = rand.nextInt(101);
		allInChance = rand.nextInt(101);
		
		if (foldChance > 21 && method.roundCounter != 5)
		{
			decision = 2;
		}
		
		
		switch (decision)
			{
				case 0:
				{
					southGamePanel.removeAll();
					addNextRoundButton(gf, southGamePanel, method, humanPlayer, computerPlayer, method.setWinnerStr(2), method.setWinningHandStr(computerPlayer.getWinValue()), true, false, moneyLabel);
					SwingUtilities.updateComponentTreeUI(gf);
					break;
				}
				case 1:
				{

					cpuActivityLabel.setText("The CPU checked!");
					checkOrCallButton.setText("Check");
					break;
				}
				case 2:
				{
					betMultiple = rand.nextInt((20-4)+1) + 4;
					bet = betMultiple*100;
					computerPlayer.updateMoney(bet);
					method.addBetToPot(bet);
					increaseBetSlider.setMinimum(computerPlayer.getMoneyContributed()-humanPlayer.getMoneyContributed());
					cpuActivityLabel.setText("The CPU bet $" + bet);
					potLabel.setText("Bet: $"+increaseBetSlider.getValue()+ " |  Pot: $" + method.getPotMoney());
					checkOrCallButton.setText("Call $"+(computerPlayer.getMoneyContributed()-humanPlayer.getMoneyContributed()));
					break;
				}
				
			}
	}

	public static void addNextRoundButton(GameFrame gf, JPanel southGamePanel,
	GameMethod method, Player humanPlayer, Player computerPlayer,
	String playerType, String winningHand, boolean folded, 
	boolean tie, JLabel moneyLabel)
	{
		JButton nextRoundButton = new JButton();
		nextRoundButton.setFont( new Font("TimesRoman", Font.BOLD, 16));
		moneyLabel.setText("Money: $" + humanPlayer.getMoneyAvailable());
		
		if (tie)
		{
			southGamePanel.removeAll();
			nextRoundButton.setText("It's a tie! The pot will be split | Click to go to the next round.");
			nextRoundButton.addActionListener
			(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						gf.getContentPane().removeAll();
														
						southGamePanel.add(nextRoundButton);
						method.resetCounter();
						newRound(humanPlayer, computerPlayer, gf);
					}
				}
			);
			southGamePanel.add(nextRoundButton);
		}
			
		if (folded)
		{
			southGamePanel.removeAll();
			nextRoundButton.setText("The " +playerType+ " folded. | The computer has $" + computerPlayer.getMoneyAvailable() +" available. | Click to go to the next round.");
			nextRoundButton.addActionListener
			(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						gf.getContentPane().removeAll();
						southGamePanel.add(nextRoundButton);
						method.resetCounter();
						newRound(humanPlayer, computerPlayer, gf);
					}
				}
			);
		}
		southGamePanel.add(nextRoundButton);
		
		if (!folded)
		{
			southGamePanel.removeAll();
			nextRoundButton.setText("The " + playerType + " wins the hand with a " + winningHand + "! | The computer has $" + computerPlayer.getMoneyAvailable() +" available. | Click to go to the next round.");
			nextRoundButton.addActionListener
			(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						gf.getContentPane().removeAll();
														
						southGamePanel.add(nextRoundButton);
						method.resetCounter();
						newRound(humanPlayer, computerPlayer, gf);
					}
				}
			);
		southGamePanel.add(nextRoundButton);
		}
	}
}
    

