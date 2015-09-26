package com.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Round {
	
	int numberOfPlayers;
	List<Hand> hands;
	
	
	public Round() {
		hands = new ArrayList<Hand>();
	}
	
	
	public void submit(Hand playerHand) throws DuplicateIDException {
		if(isUniqueID(playerHand.getPlayerID())){
			numberOfPlayers++;
			hands.add(playerHand);
		}else
			throw new DuplicateIDException();
	}
	
	
	private boolean isUniqueID(String playerID) {
		if(hands.isEmpty()) return true;
		for(Hand h: hands)
			if(h.getPlayerID().equalsIgnoreCase(playerID)) return false;
		
		return true;
	}


	public boolean validNumberOfPlayers(){
		return numberOfPlayers >= 2 && numberOfPlayers <= 4;
	}
	
	/**
	 * returns a list of playerIDs listing them from
	 * highest to lowest ranking
	 * @return
	 */
	public List<String> rankResults(){
		Collections.sort(hands);
		
		List<String> rankings = new ArrayList<String>();
		for (Hand h: hands){
			rankings.add(h.getPlayerID());
		}
	
		return rankings;
	}
	
	
	

}
