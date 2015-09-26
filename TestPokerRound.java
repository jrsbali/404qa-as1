package com.poker;

import junit.framework.TestCase;

public class TestPokerRound extends TestCase {
	
	Round round;
	
	public void setUp(){
		round = new Round();
	}
	
	
	public void test_RoundWithOver4Players(){
		round.submit(new Hand("a"));
		round.submit(new Hand("b"));
		round.submit(new Hand("d"));
		round.submit(new Hand("e"));
		round.submit(new Hand("f"));
		
		assertFalse(round.validNumberOfPlayers());		
	}
	
	public void test_RoundWith1Player(){
		round.submit(new Hand("TestPokerRound"));
		assertFalse(round.validNumberOfPlayers());
	}
	
	public void test_RoundWithNoPlayer(){
		assertFalse(round.validNumberOfPlayers());
	}
	
	
	public void test_validPokerHand(){
		Hand hand = new Hand("TestPokerRound");
		assertFalse(hand.validPokerHand());
	}
	
	public void test_validPokerHandWith5Cards(){
		Hand hand = new Hand("TestPokerRound");
		hand.add(new Card());
		hand.add(new Card());
		hand.add(new Card());
		hand.add(new Card());
		hand.add(new Card());
		assertTrue(hand.validPokerHand());
	}
	
	public void test_cardPrintout(){
//		Card card1 = new Card("Ace","Spades");
//		Card card2 = new Card("Two","Hearts");
//		assertEquals("AceSpades", card1.toString());
//		assertEquals("TwoHearts", card2.toString());
		
		Card card1 = new Card(Card.Rank.Ace,Card.Suit.Spades);
		Card card2 = new Card(Card.Rank.Two,Card.Suit.Hearts);
		assertEquals("AceSpades", card1.toString());
		assertEquals("TwoHearts", card2.toString());
	}
	
	
	public void test_validPlayerIDs(){
		Hand hand1 = new Hand("Player1");
		Hand hand2 = new Hand("Player1");
		
		round.submit(hand1);
	    assertEquals(InvalidPlayerIDException.class, round.submit(hand2));
		
	}
	
	
	
	
	
	
	
	
	

}
