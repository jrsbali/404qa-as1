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
	
	public void tearDown(){
		System.out.println("Running: tearDown");
	        hand = null;
	        assertNull(hand);
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
	
	
	public void test_threeOFAKind(){
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Diamonds));
		hand.add(new Card(Card.Rank.King,Card.Suit.Spades));
		hand.add(new Card(Card.Rank.Queen,Card.Suit.Clubs));
		
		assertEquals(4 , hand.getResult());
	}
	
	public void test_straight(){
		hand.add(new Card(Card.Rank.Five,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Four,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Three,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Two,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Diamonds));
		
		assertEquals(5, hand.getResult());
	}
	
	
	public void test_flush(){
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ten,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Seven,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Six,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Two,Card.Suit.Clubs));
		
		assertEquals(6, hand.getResult());
	}
	
	
	public void test_fullHouse(){
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Six,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Six,Card.Suit.Clubs));
		
		assertEquals(7, hand.getResult());
	}
	
	public void test_fourOfAKind(){
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Diamonds));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Spades));
		hand.add(new Card(Card.Rank.Six,Card.Suit.Hearts));
		
		assertEquals(8, hand.getResult());
	}
	
	public void test_straightFlush(){
		hand.add(new Card(Card.Rank.Nine,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Eight,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Seven,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Six,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Five,Card.Suit.Clubs));
		
		assertEquals(9 , hand.getResult());
	}
	
	public void test_roayFlush(){
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Queen,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Jack,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ten,Card.Suit.Clubs));
		
		assertEquals(10 , hand.getResult());
	}
	



	

}
