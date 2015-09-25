package com.poker;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Hand {
	
	final int MAX_SIZE = 5;
	List<Card> cards = new LinkedList<Card>();
	int numberOfCards = 0;
	
	public Hand() {
		// TODO Auto-generated constructor stub
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
		

		if(containsOnePair()) return 2;
		
		
		
		return 0;
		
	}
	
	private boolean containsOnePair(){
		Iterator<Card> iter = cards.iterator();
		Set<Integer> set = new HashSet<Integer>();
		
		// put all of the values in a set
		while(iter.hasNext())
			set.add(iter.next().getRank().getVal());
	
		// compare the set of integers
		// if its less than the max size then
		// there is at least a pair within the hands
		return cards.size()!=set.size();
	}
	
	
	
	
	
	
	

}
