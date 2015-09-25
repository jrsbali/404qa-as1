package com.poker;

import junit.framework.TestCase;
/**
 * @author jairus bali
 * 
 * 
 * Points achieved  
 * 
 *  10  royal flush
 *  9 	straight flush
 *  8	four of a kind
 *  7	full house
 *  6	flush
 *  5	straight
 *  4	three pair
 *  3	two pair
 *  2	one pair
 *  1	high card
 *  
 * 
 * 
 * 
 * 
 *
 */
public class TestPokerOutcomes extends TestCase {
	
	Hand hand;
	public void setUp(){
		hand = new Hand();
	}
	
	public void test_onePair(){
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.King,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Queen,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Jack,Card.Suit.Diamonds));
		
		// will have onepair have a value of 2
		// this number will dictate its ranking in accordance
		// with the other hands being played
		assertEquals(2, hand.getResult());
	}
	
	public void test_twoPair(){
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.King,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Queen,Card.Suit.Diamonds));
		
		assertEquals(3, hand.getResult());
	}
	
	
	

}
