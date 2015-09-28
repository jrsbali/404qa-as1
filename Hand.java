package com.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.poker.Card.Suit;

public class Hand implements Comparable {
	
	final int MAX_SIZE = 5;
	List<Card> cards = new LinkedList<Card>();
	int numberOfCards = 0;
	
	String nameID;
	
	public Hand(String nameID) {
		this.nameID = nameID;
	}
	
	public void add(Card card){
		cards.add(card);
		numberOfCards++;
	}
	
	public String getPlayerID(){
		return nameID;
	}
	
	public boolean validPokerHand(){
		return cards.size()==MAX_SIZE;
	}
	
	public List<Card> getCards(){
		return cards;
	}
	
	/**
	 * returns a string in the format of 
	 * "playerName RankSuit RankSuit RankSuit RankSuit RankSuit" 
	 * @return
	 */
	public String printHand(){
		String result = nameID + " ";
		for (Card c: cards){
			result += c.toString() +" ";
		}
		return result.trim();
	}
	public String printCardsOnly(){
		String result = "";
		for (Card c: cards){
			result += c.toString() +" ";
		}
		return result.trim();
	}
	
	public int getResult(){
		if(!validPokerHand()) return -1;
		
		if (hasRoyalFlush()) return 10;
		
		if (hasStraightFlush()) return 9;
		
		if(hasFourOfAKind()) return 8;
		
		if (hasFullHouse()) return 7;
		
		if (hasFlush()) return 6;
		
		if (hasStraight()) return 5;
		
		if(hasThreeOfAKind()) return 4;
		
		if(hasTwoPair()) return 3;
		
		if(hasOnePair()) return 2;
		
		// high card hand
		return 1;
	}

	/**
	 * returns the highest ranking card on hand.
	 * ACE is the highest
	 * @return
	 */
	public Card.Rank getHighestRank(){
		Card.Rank currRank;
		// get the first card
		Card.Rank tempHighest = cards.get(0).getRank();
		for(int i=1; i<cards.size(); i++){
			// highest possible RANK is the ACE
			if(tempHighest==Card.Rank.Ace) return tempHighest;

			currRank = cards.get(i).getRank();
			
			if(currRank.getVal()>tempHighest.getVal())
				tempHighest = currRank; 
		}
		
		return tempHighest;
	}
	
	
	private boolean hasRoyalFlush() {
		if(getRankSetSize()==MAX_SIZE && sameSuits()){
			Iterator<Card> iter = cards.iterator();
			
			// put all card ranks in a list
			List<Integer> intList = new ArrayList<Integer>();
			while (iter.hasNext()){
				int curr = iter.next().getRank().getVal();
				intList.add(curr);
			}
			
			// sort the ranks list
			Collections.sort(intList);
			int[] flushArray = {1,10,11,12,13};
			
			for(int i=0;i<intList.size();i++){
				if(flushArray[i]!=intList.get(i)) return false;
			}

			return true;
		}
		return false;
	}

	private boolean hasThreeOfAKind() {
		if(getRankSetSize()==3){
			Iterator<Card> iter = cards.iterator();
			int counter = 1;
			int currValue = iter.next().getRank().getVal();
			while(iter.hasNext()){
				if(currValue==iter.next().getRank().getVal()){
					counter++;
				}else{
					break;
				}
			}// end while
			
			if (counter==1 || counter == 3)
				return true;
		}
		return false;
	}

	private boolean hasStraightFlush() {
		return hasStraight() && sameSuits(); 
	}

	private boolean hasFourOfAKind() {
		if(getRankSetSize()==2){
			Iterator<Card> iter = cards.iterator();
			int counter = 1;
			int currValue = iter.next().getRank().getVal();
			while(iter.hasNext()){
				if(currValue==iter.next().getRank().getVal()){
					counter++;
				}else{
					break;
				}
			}// end while
			
			if (counter==1 || counter == 4)
				return true;
		}
		return false;
	}

	private boolean hasOnePair(){
		// compare the set of integers
		// if its less than the max size then
		// there is at least a pair within the hands
		return cards.size()!=getRankSetSize();
	}

	/**
	 * if the set of all the integers has the cardinality of 
	 * at least 3 then it has 2 pairs
	 * @return
	 */
	private boolean hasTwoPair(){
		return getRankSetSize()==3;
	}
	
	
	private boolean hasStraight(){
		List<Integer> intList = new ArrayList<Integer>();

		if (getRankSetSize()==MAX_SIZE){

			Iterator<Card> iter = cards.iterator();
			while (iter.hasNext()){
				int curr = iter.next().getRank().getVal();
				intList.add(curr);
			}
			
			// sort in ascended order
			Collections.sort(intList);
			
			Iterator<Integer> sortedList = intList.iterator(); 
			int currInt = sortedList.next();
			
			// check if the difference in value between the 
			// ranks 1
			while (sortedList.hasNext()){
				int next = sortedList.next();
				if(currInt+1 != next) return false;
				currInt = next;
			}// must iterate the entire hand for it to be a straight
			return true;
			
		}// end if
		
		return false;
	} // end straight
	
	private int getRankSetSize(){
		Iterator<Card> iter = cards.iterator();
		Set<Integer> set = new HashSet<Integer>();
		// put all of the values in a set
		while(iter.hasNext())
			set.add(iter.next().getRank().getVal());
		return set.size();
	}
	
	/**
	 * checks to see if ALL the cards in hand has the same suits
	 * @return
	 */
	private boolean sameSuits(){
		Iterator<Card> iter = cards.iterator();
		Suit currSuit = iter.next().getSuit();
		while(iter.hasNext()){
			if (currSuit!=iter.next().getSuit())
			return false;
		}
		
		return true;
	}
	
	
	
	private boolean hasFullHouse(){
		return getRankSetSize()==2;
	}
	
	
	private boolean hasFlush(){
		if(sameSuits() && !(hasStraight())){
			return true;
		}
		return false;
	}

	
	public void sort(){
		Collections.sort(cards);
	}
	
	@Override
	public int compareTo(Object other) {
		// descending order
		return ((Hand)other).getResult()-this.getResult();
	}

	/**
	 * gets the rank of the hand
	 * e.g. RoyalFlush, Flush, Straight etc..
	 * @return
	 */

	public String getHandRanking() {
		String result = "invalid poker hand";
		if (validPokerHand()){
			int rank = getResult();
			switch(rank){
				case(10): result = "RoyalFlush" ;break;
				case(9): result = "StraightFlush" ;break;
				case(8): result = "FourOfAKind" ;break;
				case(7): result = "FullHouse" ;break;
				case(6): result = "Flush" ;break;
				case(5): result = "Straight" ;break;
				case(4): result = "ThreeOfAKind" ;break;
				case(3): result = "TwoPair" ;break;
				case(2): result = "OnePair" ;break;
				case(1): result = "HighCard" ;break;
			}
		}
		return result;
	}// end getHankdRanking

	
	public Card[] cardsToArray(){
		return (Card[])cards.toArray(new Card[cards.size()]);
	}
	
}
