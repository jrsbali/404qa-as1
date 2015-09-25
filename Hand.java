package com.poker;

import java.util.LinkedList;
import java.util.List;

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
	
	
	
	
	
	
	

}
