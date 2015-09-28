package com.poker;

import org.junit.Before;
import org.junit.Test;

public class TestPokerHand {
	Hand hand;

	@Before
	public void setUp() {
		hand = new Hand("TestPokerRound");
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Nine, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Two, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.King, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.King, Card.Suit.Diamonds));
	}
	
	@Test(expected= MaxCardsLimitException.class)
	public void test_maxCardAddedToAHand(){
		hand.add(new Card(Card.Rank.King, Card.Suit.Diamonds));
		
	}
	
	

}
