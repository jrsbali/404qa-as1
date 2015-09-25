package com.poker;

public class Round {
	
	int numberOfPlayers;
	
	public Round() {
		numberOfPlayers = 0;
	}
	
	
	public void submit(Hand playerHand){
		
	}
	
	
	public boolean validNumberOfPlayers(){
		boolean result = numberOfPlayers <= 4;
		return result;
		
	}
	
	
	
	

}
