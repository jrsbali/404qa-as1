package com.poker;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPokerDrawOutcomes {
	
	private Round round;
	private Hand hand1;
	private Hand hand2;
	@Before
	public void setUp(){
		round = new Round();
		hand1 = new Hand("Hand1");
		hand2 = new Hand("Hand2");
	}
	
	@After
	public void tearDown(){
		round = null;
	}
	
	
	@Test
	public void test_4playerRankingHighCardDraw() 
			throws DuplicateIDException, DuplicateCardsException,
			InvalidPokerHand, MaxHandsLimitException,
			
			MinimumHandsException
	{
		// HighCard
		Hand hand1 = new Hand("Randy");
		hand1.add(new Card(Card.Rank.Ace,Card.Suit.Hearts)); // highest card
		hand1.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Queen,Card.Suit.Diamonds));
		hand1.add(new Card(Card.Rank.Jack,Card.Suit.Spades));
		hand1.add(new Card(Card.Rank.Nine,Card.Suit.Clubs));
		
		// HighCard
		Hand hand2 = new Hand("Jody");
		hand2.add(new Card(Card.Rank.Two,Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.King,Card.Suit.Spades)); // highest card
		hand2.add(new Card(Card.Rank.Queen,Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.Jack,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Nine,Card.Suit.Spades));
		
		// HighCard
		Hand hand3 = new Hand("Ulric");
		hand3.add(new Card(Card.Rank.Five,Card.Suit.Spades));
		hand3.add(new Card(Card.Rank.Six,Card.Suit.Diamonds));
		hand3.add(new Card(Card.Rank.Three,Card.Suit.Spades));
		hand3.add(new Card(Card.Rank.Two,Card.Suit.Hearts)); 
		hand3.add(new Card(Card.Rank.Eight,Card.Suit.Hearts)); // highest card

		// HighCard
		Hand hand4 = new Hand("Clyde");
		hand4.add(new Card(Card.Rank.Two,Card.Suit.Clubs));
		hand4.add(new Card(Card.Rank.Six,Card.Suit.Hearts));
		hand4.add(new Card(Card.Rank.Five,Card.Suit.Clubs));
		hand4.add(new Card(Card.Rank.Three,Card.Suit.Diamonds));
		hand4.add(new Card(Card.Rank.Nine,Card.Suit.Diamonds)); // highest card

		round.submit(hand1);
		round.submit(hand2);
		round.submit(hand3);
		round.submit(hand4);
			
		List<String> expected = new ArrayList<String>
			(Arrays.asList("Randy HighCard", "Jody HighCard",
					"Clyde HighCard", "Ulric HighCard"));
		assertEquals(expected,round.finalRankings());
	}
	
	
	
	@Test
	public void test_2playerOnePairDraw()  throws InvalidHandsException
	{
		// pair
		Hand hand1 = new Hand("Randy");
		hand1.add(new Card(Card.Rank.King,Card.Suit.Hearts)); 
		hand1.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Queen,Card.Suit.Diamonds)); // highest card
		hand1.add(new Card(Card.Rank.Jack,Card.Suit.Spades));
		hand1.add(new Card(Card.Rank.Nine,Card.Suit.Clubs));
		
		// pair
		Hand hand2 = new Hand("Jody");
		hand2.add(new Card(Card.Rank.King,Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.King,Card.Suit.Spades)); // highest card
		hand2.add(new Card(Card.Rank.Eight,Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.Jack,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Nine,Card.Suit.Spades));
	
		assertEquals(1, round.tieBreaker(hand1, hand2));
	}
	
	@Test
	public void test_handSortDescendingOrder() 
	{
		Hand hand2 = new Hand("Jody");
		hand2.add(new Card(Card.Rank.King,Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.King,Card.Suit.Spades)); // highest card
		hand2.add(new Card(Card.Rank.Eight,Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.Jack,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Nine,Card.Suit.Spades));
		hand2.sort();	// sorts in descending order
	
		assertEquals("KingDiamonds KingSpades JackClubs NineSpades EightHearts",
				hand2.printCardsOnly());		
	}
	
	
	@Test
	public void test_twoPairDraw() throws InvalidHandsException{
		hand1.add(new Card(Card.Rank.Ace,Card.Suit.Clubs)); // THIS Should win 
		hand1.add(new Card(Card.Rank.Ace,Card.Suit.Hearts)); // because of ACEs
		hand1.add(new Card(Card.Rank.King,Card.Suit.Hearts));
		hand1.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Queen,Card.Suit.Diamonds));

		hand2.add(new Card(Card.Rank.Queen,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Queen,Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.King,Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Two,Card.Suit.Diamonds));
		
		assertEquals(1,round.tieBreaker(hand1, hand2));
		
	}
	
	@Test
	public void test_threeOFAKindDraw() throws InvalidHandsException{
		hand1.add(new Card(Card.Rank.Ace,Card.Suit.Hearts));// this should win
		hand1.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Ace,Card.Suit.Diamonds));
		hand1.add(new Card(Card.Rank.King,Card.Suit.Spades));
		hand1.add(new Card(Card.Rank.Queen,Card.Suit.Clubs));
		
		hand2.add(new Card(Card.Rank.King,Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.King,Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.Two,Card.Suit.Spades));
		hand2.add(new Card(Card.Rank.Queen,Card.Suit.Clubs));
		
		// outputs a 1 if hand1 wins, -1 if hand2 wins, 0 if its a tie
		assertEquals(1, round.tieBreaker(hand1,hand2));
		
	}
	
	@Test
	public void test_straight() throws InvalidHandsException{
		hand1.add(new Card(Card.Rank.Five,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Four,Card.Suit.Hearts));
		hand1.add(new Card(Card.Rank.Three,Card.Suit.Hearts));
		hand1.add(new Card(Card.Rank.Two,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Six,Card.Suit.Diamonds));
		
		hand2.add(new Card(Card.Rank.Five,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Four,Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.Three,Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.Two,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Ace,Card.Suit.Diamonds));
		
		assertEquals(-1, round.tieBreaker(hand1, hand2));
	}
	
	
	
	
	
	
	
	
	

}
