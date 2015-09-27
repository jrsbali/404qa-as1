package com.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Round {
	
	private List<Hand> hands;
	final private int  MAX_HANDS = 4;
	private Set<Card> cardsInPlay;
	
	public Round() {
		hands = new ArrayList<Hand>();
		cardsInPlay = new HashSet<Card>();
	}
	
	
	/**
	 * 
	 * Validates playerHands prior to ranking them
	 * 
	 * @param playerHand
	 * @throws DuplicateIDException
	 * @throws DuplicateCardsException
	 * @throws InvalidPokerHand
	 * @throws MaxHandsLimitException
	 */
	public void submit(Hand playerHand) 
			throws DuplicateIDException, DuplicateCardsException,
			InvalidPokerHand, MaxHandsLimitException
	{
		
		if(hands.size()+1>MAX_HANDS) throw new MaxHandsLimitException();
		
		if (!playerHand.validPokerHand()) throw new InvalidPokerHand();
		
		if(hasDuplicateCards(playerHand)) throw new DuplicateCardsException();
		
		if(isUniqueID(playerHand.getPlayerID())){
			hands.add(playerHand);
		}else
			throw new DuplicateIDException();
	} // end submit
	
	
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
		int numberOfPlayers = hands.size();
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
