package com.poker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestPokerRound {
	
	Round round;
	
	@Before
	public void setUp(){
		round = new Round();
	}
	
	@Test
	public void test_RoundWithOver4Players() throws DuplicateIDException{
		round.submit(new Hand("a"));
		round.submit(new Hand("b"));
		round.submit(new Hand("c"));
		round.submit(new Hand("d"));
		round.submit(new Hand("e"));
		
		assertFalse(round.validNumberOfPlayers());		
	}
	
	@Test
	public void test_RoundWith1Player() throws DuplicateIDException {
		round.submit(new Hand("TestPokerRound"));
		assertFalse(round.validNumberOfPlayers());
	}
	
	@Test
	public void test_RoundWithNoPlayer(){
		assertFalse(round.validNumberOfPlayers());
	}
	
	@Test
	public void test_validPokerHand(){
		Hand hand = new Hand("TestPokerRound");
		assertFalse(hand.validPokerHand());
	}
	
	@Test
	public void test_validPokerHandWith5Cards(){
		Hand hand = new Hand("TestPokerRound");
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Nine,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Two,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Queen,Card.Suit.Diamonds));
		assertTrue(hand.validPokerHand());
	}
	
	@Test
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
	

	
	@Test(expected=DuplicateIDException.class)
	public void throws_validPlayerIDs() throws DuplicateIDException  {
		Hand hand1 = new Hand("Player1");
		Hand hand2 = new Hand("player1");
		
		round.submit(hand1);
		round.submit(hand2);
	}
	
	
	public void test_duplicateCardsInSubmittedHands() throws DuplicateIDException{
		Hand hand1 = new Hand("Player1");
		Hand hand2 = new Hand("Player2");
		
		hand1.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Nine,Card.Suit.Hearts));
		hand1.add(new Card(Card.Rank.Two,Card.Suit.Hearts));
		hand1.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Queen,Card.Suit.Diamonds));
		
		hand2.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Nine,Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.Two,Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Queen,Card.Suit.Diamonds));
		
		round.submit(hand1);
		round.submit(hand2);
	}
	
	
	
	
	
	
	
	

}
