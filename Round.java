package com.poker;

public class Round {
	
	int numberOfPlayers;
	
	public Round() {
	}
	
	
	public void submit(Hand playerHand){
		numberOfPlayers++;
	}
	
	
	public boolean validNumberOfPlayers(){
		return numberOfPlayers >= 2 && numberOfPlayers <= 4;
	}
	
	
	
	

}
