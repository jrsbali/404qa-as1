package com.poker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jairus bali
 * 
 * 
 *         Points achieved
 * 
 *         10 royal flush 9 straight flush 8 four of a kind 7 full house 6 flush
 *         5 straight 4 three pair 3 two pair 2 one pair 1 high card
 * 
 * 
 * 
 * 
 * 
 *
 */
public class TestPokerOutcomes {

	Hand hand;

	@Before
	public void setUp() {
		hand = new Hand("TestPokerOutcomes");
	}

	@After
	public void tearDown() {
		// System.out.println("Running: tearDown");
		hand = null;
		assertNull(hand);
	}

	@Test
	public void test_highCard() throws MaxCardsLimitException {
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Nine, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Two, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.King, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Queen, Card.Suit.Diamonds));

		assertEquals(1, hand.getResult());
		assertEquals(Card.Rank.Ace, hand.getHighestRank());

	}

	@Test
	public void test_onePair() throws MaxCardsLimitException {
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.King, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Queen, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Jack, Card.Suit.Diamonds));

		// will have onepair have a value of 2
		// this number will dictate its ranking in accordance
		// with the other hands being played
		assertEquals(2, hand.getResult());
	}

	@Test
	public void test_twoPair() throws MaxCardsLimitException {
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.King, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.King, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Queen, Card.Suit.Diamonds));

		assertEquals(3, hand.getResult());
	}

	@Test
	public void test_threeOFAKind() throws MaxCardsLimitException {
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Diamonds));
		hand.add(new Card(Card.Rank.King, Card.Suit.Spades));
		hand.add(new Card(Card.Rank.Queen, Card.Suit.Clubs));

		assertEquals(4, hand.getResult());
	}

	@Test
	public void test_straight() throws MaxCardsLimitException {
		hand.add(new Card(Card.Rank.Five, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Four, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Three, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Two, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Diamonds));

		assertEquals(5, hand.getResult());
	}

	@Test
	public void test_flush() throws MaxCardsLimitException {
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ten, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Seven, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Six, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Two, Card.Suit.Clubs));

		assertEquals(6, hand.getResult());
	}

	@Test
	public void test_fullHouse() throws MaxCardsLimitException {
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Diamonds));
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Six, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Six, Card.Suit.Clubs));

		assertEquals(7, hand.getResult());
	}

	@Test
	public void test_fourOfAKind() throws MaxCardsLimitException {
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Diamonds));
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Spades));
		hand.add(new Card(Card.Rank.Six, Card.Suit.Hearts));

		assertEquals(8, hand.getResult());
	}

	@Test
	public void test_straightFlush() throws MaxCardsLimitException {
		hand.add(new Card(Card.Rank.Nine, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Eight, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Seven, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Six, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Five, Card.Suit.Clubs));

		assertEquals(9, hand.getResult());
	}

	@Test
	public void test_royalFlush() throws MaxCardsLimitException {
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.King, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Queen, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Jack, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ten, Card.Suit.Clubs));

		assertEquals(10, hand.getResult());
	}

	/**
	 * print out the hand e.g.
	 * "playerName RankSuit RankSuit RankSuit RankSuit RankSuit"
	 */
	@Test
	public void test_handStringOutput() throws MaxCardsLimitException {
		hand = new Hand("Ulric");
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.King, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.King, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Queen, Card.Suit.Diamonds));

		String expected = "Ulric AceClubs AceHearts KingHearts KingClubs QueenDiamonds";

		assertEquals(expected, hand.printHand());
	}

	@Test
	public void test_getHandRanking() throws MaxCardsLimitException {
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.King, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Queen, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Jack, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ten, Card.Suit.Clubs));

		assertEquals("RoyalFlush", hand.getHandRanking());

	}

}
