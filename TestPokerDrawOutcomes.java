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
	
	@Before
	public void setUp(){
		round = new Round();
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
	public void test_2playerRankingHighCardDraw() 
			throws DuplicateIDException, DuplicateCardsException,
			InvalidPokerHand, MaxHandsLimitException,
			MinimumHandsException, InvalidHandsException
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
	
		round.submit(hand1);
		round.submit(hand2);
		
		assertEquals(1, round.onePairTieBreaker(hand1, hand2));
	}
	
	public void test_handSortDescendingOrder() 
	{
		Hand hand2 = new Hand("Jody");
		hand2.add(new Card(Card.Rank.King,Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.King,Card.Suit.Spades)); // highest card
		hand2.add(new Card(Card.Rank.Eight,Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.Jack,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Nine,Card.Suit.Spades));
		hand2.sort();
		
		assertEquals("KingDiamonds KingSpades JackClubs NineSpades EightHearts",
				hand2.toString());		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}