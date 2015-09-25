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
		
		if (containsFlush()) return 5;
		
		if (containsStraight()) return 4;
		
		if(containsTwoPair()) return 3;
		
		if(containsOnePair()) return 2;
		
		return 0;
		
	}
	
	private boolean containsOnePair(){
		// compare the set of integers
		// if its less than the max size then
		// there is at least a pair within the hands
		return cards.size()!=getRankSet();
	}

	/**
	 * if the set of all the integers has the cardinality of 
	 * at least 3 then it has 2 pairs
	 * @return
	 */
	private boolean containsTwoPair(){
		return getRankSet()==3;
	}
	
	
	private boolean containsStraight(){
		List<Integer> intList = new ArrayList<Integer>();

		if (getRankSet()==MAX_SIZE){

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
	
	private int getRankSet(){
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
	
	
	
	private boolean containsFlush(){
		if(sameSuits() && !(containsStraight())){
			return true;
		}
		
		return false;
	}

	

}
