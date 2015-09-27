package com.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Round {
	
	private List<Hand> hands;
	final private int  MAX_HANDS = 4;
	final private int  MIN_HANDS = 2;
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
	public List<String> rankResults() throws MinimumHandsException {
		if(hands.size()<MIN_HANDS) throw new MinimumHandsException();
		List<String> rankings = new ArrayList<String>();
		for (Hand h: sortReceivedHands()){
			rankings.add(h.getPlayerID());
		}
		return rankings;
	}

	/**
	 * sorts the hands in descending order
	 * @return
	 */
	private List<Hand> sortReceivedHands(){
		Collections.sort(hands);
		return hands;
	}
	
	public List<String> rankResultsWithHandType(){
		List<String> results = new ArrayList<String>();
		for (Hand h: sortReceivedHands()){
			results.add(h.getPlayerID()+" "+h.getHandRanking());
		}
		return results;
	}


	public List<String> finalRankings() {
		Hand[] tempArr = new Hand[hands.size()]; 
		hands.toArray(tempArr);
		
		for(int i=0; i<tempArr.length-1; i++){
			for(int j=i; j<tempArr.length-1; j++){
				// they are equal must split them to create
				//	a clear cut ranking
				if(tempArr[j].getResult()==tempArr[j+1].getResult()){
					int highestA = tempArr[j].getHighestRank().getVal();
					int highestB = tempArr[j+1].getHighestRank().getVal();
					
					// must convert aces to something higher than 13 (King)
					if(highestA==1) highestA = 20;
					if(highestB==1) highestB = 20;
					
					if(highestA < highestB){
						//swap
						Hand temp = tempArr[j+1];
						tempArr[j+1] = tempArr[j];
						tempArr[j] = temp;
					}
				
				}
			}
		}
		
		List<String> finalRankings = new ArrayList<String>();
		for(int i=0; i<tempArr.length; i++){
			finalRankings.add(tempArr[i].getPlayerID()+" "+
							tempArr[i].getHandRanking());
		}
		return finalRankings;
	
	}
	
	

}
