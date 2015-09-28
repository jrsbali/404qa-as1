package com.poker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestPokerRound {

	Round round;

	Hand hand;

	@Before
	public void setUp() throws MaxCardsLimitException {
		round = new Round();
		hand = new Hand("TestPokerRound");
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Nine, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Two, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.King, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.King, Card.Suit.Diamonds));
	}

	@Test(expected = MaxHandsLimitException.class)
	public void test_RoundWithOver4Players() throws DuplicateIDException,
			DuplicateCardsException, InvalidPokerHand, MaxHandsLimitException,
			MaxCardsLimitException {
		Round round = new Round();

		// royal flush
		Hand hand1 = new Hand("a");
		hand1.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.King, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Queen, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Jack, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Ten, Card.Suit.Clubs));

		// full house
		Hand hand2 = new Hand("b");
		hand2.add(new Card(Card.Rank.Ace, Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.Ace, Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.Ace, Card.Suit.Spades));
		hand2.add(new Card(Card.Rank.Six, Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.Six, Card.Suit.Spades));

		// two of a kind
		Hand hand3 = new Hand("c");
		hand3.add(new Card(Card.Rank.Two, Card.Suit.Clubs));
		hand3.add(new Card(Card.Rank.Two, Card.Suit.Hearts));
		hand3.add(new Card(Card.Rank.King, Card.Suit.Hearts));
		hand3.add(new Card(Card.Rank.King, Card.Suit.Diamonds));
		hand3.add(new Card(Card.Rank.Queen, Card.Suit.Diamonds));

		// straight flush
		Hand hand4 = new Hand("d");
		hand4.add(new Card(Card.Rank.Nine, Card.Suit.Clubs));
		hand4.add(new Card(Card.Rank.Eight, Card.Suit.Clubs));
		hand4.add(new Card(Card.Rank.Seven, Card.Suit.Clubs));
		hand4.add(new Card(Card.Rank.Six, Card.Suit.Clubs));
		hand4.add(new Card(Card.Rank.Five, Card.Suit.Clubs));

		Hand hand5 = new Hand("e");
		hand5.add(new Card(Card.Rank.Nine, Card.Suit.Diamonds));
		hand5.add(new Card(Card.Rank.Eight, Card.Suit.Diamonds));
		hand5.add(new Card(Card.Rank.Two, Card.Suit.Diamonds));
		hand5.add(new Card(Card.Rank.Four, Card.Suit.Clubs));
		hand5.add(new Card(Card.Rank.Nine, Card.Suit.Hearts));

		round.submit(hand1);
		round.submit(hand2);
		round.submit(hand3);
		round.submit(hand4);
		round.submit(hand5);
	}

	@Test
	public void test_RoundWith1Player() throws DuplicateIDException,
			DuplicateCardsException, InvalidPokerHand, MaxHandsLimitException {
		round.submit(hand);

		assertFalse(round.validNumberOfPlayers());
	}

	@Test
	public void test_RoundWithNoPlayer() {
		assertFalse(round.validNumberOfPlayers());
	}

	@Test
	public void test_validPokerHand() {
		Hand hand = new Hand("TestPokerRound");
		assertFalse(hand.validPokerHand());
	}

	@Test
	public void test_validPokerHandWith5Cards() throws MaxCardsLimitException {
		Hand hand = new Hand("TestPokerRound");
		hand.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Nine, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Two, Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.King, Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Queen, Card.Suit.Diamonds));
		assertTrue(hand.validPokerHand());
	}

	@Test
	public void test_cardPrintout() {
		Card card1 = new Card(Card.Rank.Ace, Card.Suit.Spades);
		Card card2 = new Card(Card.Rank.Two, Card.Suit.Hearts);
		assertEquals("AceSpades", card1.toString());
		assertEquals("TwoHearts", card2.toString());
	}

	@Test(expected = DuplicateIDException.class)
	public void throws_validPlayerIDs() throws DuplicateIDException,
			DuplicateCardsException, InvalidPokerHand, MaxHandsLimitException,
			MaxCardsLimitException {
		Hand hand1 = new Hand("TestPokerROUND");
		hand1.add(new Card(Card.Rank.Jack, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Seven, Card.Suit.Hearts));
		hand1.add(new Card(Card.Rank.Three, Card.Suit.Hearts));
		hand1.add(new Card(Card.Rank.Queen, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Jack, Card.Suit.Diamonds));

		round.submit(hand);
		round.submit(hand1);
	}

	@Test(expected = DuplicateCardsException.class)
	public void test_duplicateCardsInSubmittedHands()
			throws DuplicateIDException, DuplicateCardsException,
			InvalidPokerHand, MaxHandsLimitException, MaxCardsLimitException {
		Hand hand1 = new Hand("Player1");
		Hand hand2 = new Hand("Player2");

		hand1.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Nine, Card.Suit.Hearts));
		hand1.add(new Card(Card.Rank.Two, Card.Suit.Hearts));
		hand1.add(new Card(Card.Rank.King, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Queen, Card.Suit.Diamonds));

		hand2.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Nine, Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.Two, Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.King, Card.Suit.Clubs));
		hand2.add(new Card(Card.Rank.Queen, Card.Suit.Diamonds));

		round.submit(hand1);
		round.submit(hand2);
	}

	// check the printout ranking (descending order)
	@Test
	public void test_4playerRanking() throws DuplicateIDException,
			DuplicateCardsException, InvalidPokerHand, MaxHandsLimitException,

			MinimumHandsException, MaxCardsLimitException {
		Round round = new Round();

		// royal flush
		Hand hand1 = new Hand("Randy");
		hand1.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.King, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Queen, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Jack, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Ten, Card.Suit.Clubs));

		// full house
		Hand hand2 = new Hand("Jody");
		hand2.add(new Card(Card.Rank.Ace, Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.Ace, Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.Ace, Card.Suit.Spades));
		hand2.add(new Card(Card.Rank.Six, Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.Six, Card.Suit.Spades));

		// two of a kind
		Hand hand3 = new Hand("Ulric");
		hand3.add(new Card(Card.Rank.Two, Card.Suit.Clubs));
		hand3.add(new Card(Card.Rank.Two, Card.Suit.Hearts));
		hand3.add(new Card(Card.Rank.King, Card.Suit.Hearts));
		hand3.add(new Card(Card.Rank.King, Card.Suit.Diamonds));
		hand3.add(new Card(Card.Rank.Queen, Card.Suit.Diamonds));

		// straight flush
		Hand hand4 = new Hand("Clyde");
		hand4.add(new Card(Card.Rank.Nine, Card.Suit.Clubs));
		hand4.add(new Card(Card.Rank.Eight, Card.Suit.Clubs));
		hand4.add(new Card(Card.Rank.Seven, Card.Suit.Clubs));
		hand4.add(new Card(Card.Rank.Six, Card.Suit.Clubs));
		hand4.add(new Card(Card.Rank.Five, Card.Suit.Clubs));

		round.submit(hand1);
		round.submit(hand2);
		round.submit(hand3);
		round.submit(hand4);

		List<String> expected = new ArrayList<String>(Arrays.asList("Randy",
				"Clyde", "Jody", "Ulric"));
		assertEquals(expected, round.rankResults());
	}

	@Test(expected = InvalidPokerHand.class)
	public void test_submitCardsWithLessThan5Cards()
			throws DuplicateIDException, DuplicateCardsException,
			InvalidPokerHand, MaxHandsLimitException, MaxCardsLimitException {
		Hand hand1 = new Hand("Player1");

		hand1.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Nine, Card.Suit.Hearts));
		hand1.add(new Card(Card.Rank.Two, Card.Suit.Hearts));

		round.submit(hand1);
	}

	@Test(expected = MinimumHandsException.class)
	public void test_getRankResultsWithOnlyOneSubmission()
			throws DuplicateIDException, DuplicateCardsException,
			InvalidPokerHand, MaxHandsLimitException, MinimumHandsException,
			MaxCardsLimitException {

		// royal flush
		Hand hand1 = new Hand("Randy");
		hand1.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.King, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Queen, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Jack, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Ten, Card.Suit.Clubs));

		round.submit(hand1);

		// will throw MinimumHandsExeption if
		// the total hands submitted is less than 2
		round.rankResults();
	}

	@Test
	public void test_4playerRankingWithRankResultsWithHandType()
			throws DuplicateIDException, DuplicateCardsException,
			InvalidPokerHand, MaxHandsLimitException, MaxCardsLimitException,

			MinimumHandsException {
		Round round = new Round();

		// royal flush
		Hand hand1 = new Hand("Randy");
		hand1.add(new Card(Card.Rank.Ace, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.King, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Queen, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Jack, Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Ten, Card.Suit.Clubs));

		// full house
		Hand hand2 = new Hand("Jody");
		hand2.add(new Card(Card.Rank.Ace, Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.Ace, Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.Ace, Card.Suit.Spades));
		hand2.add(new Card(Card.Rank.Six, Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.Six, Card.Suit.Spades));

		// two of a kind
		Hand hand3 = new Hand("Ulric");
		hand3.add(new Card(Card.Rank.Two, Card.Suit.Clubs));
		hand3.add(new Card(Card.Rank.Two, Card.Suit.Hearts));
		hand3.add(new Card(Card.Rank.King, Card.Suit.Hearts));
		hand3.add(new Card(Card.Rank.King, Card.Suit.Diamonds));
		hand3.add(new Card(Card.Rank.Queen, Card.Suit.Diamonds));

		// straight flush
		Hand hand4 = new Hand("Clyde");
		hand4.add(new Card(Card.Rank.Nine, Card.Suit.Clubs));
		hand4.add(new Card(Card.Rank.Eight, Card.Suit.Clubs));
		hand4.add(new Card(Card.Rank.Seven, Card.Suit.Clubs));
		hand4.add(new Card(Card.Rank.Six, Card.Suit.Clubs));
		hand4.add(new Card(Card.Rank.Five, Card.Suit.Clubs));

		round.submit(hand1);
		round.submit(hand2);
		round.submit(hand3);
		round.submit(hand4);

		List<String> expected = new ArrayList<String>(Arrays.asList(
				"Randy RoyalFlush", "Clyde StraightFlush", "Jody FullHouse",
				"Ulric TwoPair"));
		assertEquals(expected, round.rankResultsWithHandType());
	}

}
