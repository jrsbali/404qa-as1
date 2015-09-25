package com.poker;

public class Round {
	
	int numberOfPlayers;
	
	public Round() {
	}
	
	
	public void submit(Hand playerHand){
		numberOfPlayers++;
	}
	
	
	public boolean validNumberOfPlayers(){
		boolean result = numberOfPlayers <= 4;
		return result;
	}
	
	
	
	

}
