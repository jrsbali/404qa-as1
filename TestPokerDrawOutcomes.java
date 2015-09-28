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
	public void test_2playerRankingHighCardDraw() throws InvalidHandsException  
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

		assertEquals(1, round.tieBreaker(hand1, hand2));
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
	public void test_straightDraw() throws InvalidHandsException{
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
	
	
	@Test
	public void test_flushDraw() throws InvalidHandsException{
		hand1.add(new Card(Card.Rank.Five,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Ten,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Seven,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Six,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Two,Card.Suit.Clubs));
		
		hand2.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Ten,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Seven,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Six,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Two,Card.Suit.Clubs));
		
		
		assertEquals(-1, round.tieBreaker(hand1, hand2));
	}
	
	@Test
	public void test_fullHouseDraw()throws InvalidHandsException{
		hand1.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));  
		hand1.add(new Card(Card.Rank.Ace,Card.Suit.Diamonds));
		hand1.add(new Card(Card.Rank.Ace,Card.Suit.Hearts));
		hand1.add(new Card(Card.Rank.Two,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Two,Card.Suit.Clubs));
		
		hand2.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.King,Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.King,Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.Queen,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Queen,Card.Suit.Clubs));
		
		//hand1 wins because of the 3-Aces
		assertEquals(1, round.tieBreaker(hand1, hand2));
	}
	
	
	@Test
	public void test_fourOfAKindDraw() throws InvalidHandsException{
		hand1.add(new Card(Card.Rank.Ace,Card.Suit.Hearts));
		hand1.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Ace,Card.Suit.Diamonds));
		hand1.add(new Card(Card.Rank.Ace,Card.Suit.Spades));
		hand1.add(new Card(Card.Rank.Three,Card.Suit.Hearts));
		
		hand2.add(new Card(Card.Rank.King,Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.King,Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.King,Card.Suit.Spades));
		hand2.add(new Card(Card.Rank.Queen,Card.Suit.Hearts));
		
		//hand1 should win because of the Aces
		assertEquals(1, round.tieBreaker(hand1, hand2));
	}
	
	@Test
	public void test_straightFlushDraw()throws InvalidHandsException{
		hand1.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Queen,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Jack,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Ten,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Nine,Card.Suit.Clubs));
		
		hand2.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Two,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Three,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Four,Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Five,Card.Suit.Clubs));
		
		//hand2 should win because of the Aces
		assertEquals(-1, round.tieBreaker(hand1, hand2));
	}
	
	@Test
	public void test_royalFlushDraw()throws InvalidHandsException{
		hand1.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Queen,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Jack,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Ten,Card.Suit.Clubs));
		
		hand2.add(new Card(Card.Rank.Ace,Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.King,Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.Queen,Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.Jack,Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.Ten,Card.Suit.Diamonds));
		
		// highest possible hand, with no tie breaker
		assertEquals(0, round.tieBreaker(hand1, hand2));
	}
	
	
	@Test
	public void test_mixedHandTypesWithDraws()throws InvalidHandsException,
	 DuplicateIDException, DuplicateCardsException,
	 InvalidPokerHand, MaxHandsLimitException
	{
		
		// high card
		Hand kitkat = new Hand("kitkat");
		kitkat.add(new Card(Card.Rank.Ace,Card.Suit.Hearts)); // highest card
		kitkat.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		kitkat.add(new Card(Card.Rank.Queen,Card.Suit.Diamonds));
		kitkat.add(new Card(Card.Rank.Jack,Card.Suit.Spades));
		kitkat.add(new Card(Card.Rank.Nine,Card.Suit.Clubs));
		
		// three of a kind
		Hand jairus = new Hand("jairus");
		jairus.add(new Card(Card.Rank.Two,Card.Suit.Hearts));
		jairus.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		jairus.add(new Card(Card.Rank.Ace,Card.Suit.Diamonds));
		jairus.add(new Card(Card.Rank.Ace,Card.Suit.Spades));
		jairus.add(new Card(Card.Rank.Three,Card.Suit.Hearts));
	
		// straight flush
		Hand bombadil = new Hand("bombadil");
		bombadil.add(new Card(Card.Rank.Six,Card.Suit.Clubs));
		bombadil.add(new Card(Card.Rank.Five,Card.Suit.Clubs));
		bombadil.add(new Card(Card.Rank.Four,Card.Suit.Clubs));
		bombadil.add(new Card(Card.Rank.Three,Card.Suit.Clubs));
		bombadil.add(new Card(Card.Rank.Two,Card.Suit.Clubs));
		
		// straight flush
		Hand tom = new Hand("tom");
		tom.add(new Card(Card.Rank.Three,Card.Suit.Diamonds));
		tom.add(new Card(Card.Rank.Four,Card.Suit.Diamonds));
		tom.add(new Card(Card.Rank.Seven,Card.Suit.Diamonds));
		tom.add(new Card(Card.Rank.Six,Card.Suit.Diamonds));
		tom.add(new Card(Card.Rank.Five,Card.Suit.Diamonds));
		
		List<String> expected = new ArrayList<String>(
				Arrays.asList("tom StraightFlush", "bombadil StraightFlush",
				"jairus ThreeOfAKind", "kitkat HighCard"));
		
		round.submit(kitkat);
		round.submit(jairus);
		round.submit(bombadil);
		round.submit(tom);
		
		assertEquals(expected, round.rankResultsWithHandType());
		
	}
	
	

}
