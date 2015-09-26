package com.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Round {
	
	int numberOfPlayers;
	private List<Hand> hands;
	
	private Set<Card> cardsInPlay;
	final int EXPECTED_CARDS_PER_HAND = 5;
	
	
	public Round() {
		hands = new ArrayList<Hand>();
		cardsInPlay = new HashSet<Card>();
	}
	
	
	public void submit(Hand playerHand) 
			throws DuplicateIDException, DuplicateCardsException {
		
		if(hasDuplicateCards(playerHand)) throw new DuplicateCardsException();
		
		
		if(isUniqueID(playerHand.getPlayerID())){
			numberOfPlayers++;
			hands.add(playerHand);
		}else
			throw new DuplicateIDException();
	}
	
	
	private boolean hasDuplicateCards(Hand playerHand) {
		for(Card c: playerHand.getCards()){
			if (!cardsInPlay.add(c)) return true;
		}
		return false;
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
