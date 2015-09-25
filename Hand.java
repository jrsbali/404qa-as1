package com.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.poker.Card.Suit;

public class Hand {
	
	final int MAX_SIZE = 5;
	List<Card> cards = new LinkedList<Card>();
	int numberOfCards = 0;
	
	public Hand() {
	}
	
	public void add(Card card){
		cards.add(card);
		numberOfCards++;
	}
	
	
	public boolean validPokerHand(){
		return cards.size()==MAX_SIZE;
	}
	
	
	public int getResult(){
		if(!validPokerHand()) return -1;
		
		if(hasFourOfAKind()) return 7;
		
		if (hasFullHouse()) return 6;
		
		if (hasFlush()) return 5;
		
		if (hasStraight()) return 4;
		
		if(hasTwoPair()) return 3;
		
		if(hasOnePair()) return 2;
		
		return 0;
		
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

	

}
