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
public class TestPokerOutcomes {
	
	Hand hand;
	
	@Before
	public void setUp(){
		hand = new Hand("TestPokerOutcomes");
	}
	@After
	public void tearDown(){
//		System.out.println("Running: tearDown");
        hand = null;
        assertNull(hand);
	}
	
	@Test
	public void test_highCard(){
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Nine,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Two,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Queen,Card.Suit.Diamonds));

		assertEquals(1, hand.getResult());
		assertEquals(Card.Rank.Ace, hand.getHighestRank());
		
	}
	
	@Test
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
	
	@Test
	public void test_twoPair(){
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.King,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Queen,Card.Suit.Diamonds));
		
		assertEquals(3, hand.getResult());
	}
	
	
	@Test
	public void test_threeOFAKind(){
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Diamonds));
		hand.add(new Card(Card.Rank.King,Card.Suit.Spades));
		hand.add(new Card(Card.Rank.Queen,Card.Suit.Clubs));
		
		assertEquals(4 , hand.getResult());
	}
	
	@Test
	public void test_straight(){
		hand.add(new Card(Card.Rank.Five,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Four,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Three,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Two,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Diamonds));
		
		assertEquals(5, hand.getResult());
	}
	
	
	@Test
	public void test_flush(){
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ten,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Seven,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Six,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Two,Card.Suit.Clubs));
		
		assertEquals(6, hand.getResult());
	}
	
	@Test
	public void test_fullHouse(){
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Diamonds));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Six,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Six,Card.Suit.Clubs));
		
		assertEquals(7, hand.getResult());
	}
	
	@Test
	public void test_fourOfAKind(){
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Diamonds));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Spades));
		hand.add(new Card(Card.Rank.Six,Card.Suit.Hearts));
		
		assertEquals(8, hand.getResult());
	}
	
	@Test
	public void test_straightFlush(){
		hand.add(new Card(Card.Rank.Nine,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Eight,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Seven,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Six,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Five,Card.Suit.Clubs));
		
		assertEquals(9 , hand.getResult());
	}
	
	@Test
	public void test_royalFlush(){
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Queen,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Jack,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ten,Card.Suit.Clubs));
		
		assertEquals(10 , hand.getResult());
	}
	
	
	@Test
	public void test_4playerRanking() 
			throws DuplicateIDException, DuplicateCardsException,
			InvalidPokerHand, MaxHandsLimitException
	{
		Round round = new Round();
		
		// royal flush
		Hand hand1 = new Hand("Randy");
		hand1.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Queen,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Jack,Card.Suit.Clubs));
		hand1.add(new Card(Card.Rank.Ten,Card.Suit.Clubs));
		
		// full house
		Hand hand2 = new Hand("Jody");
		hand2.add(new Card(Card.Rank.Ace,Card.Suit.Hearts));
		hand2.add(new Card(Card.Rank.Ace,Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.Ace,Card.Suit.Spades));
		hand2.add(new Card(Card.Rank.Six,Card.Suit.Diamonds));
		hand2.add(new Card(Card.Rank.Six,Card.Suit.Spades));
		
		// two of a kind
		Hand hand3 = new Hand("Ulric");
		hand3.add(new Card(Card.Rank.Two,Card.Suit.Clubs));
		hand3.add(new Card(Card.Rank.Two,Card.Suit.Hearts));
		hand3.add(new Card(Card.Rank.King,Card.Suit.Hearts));
		hand3.add(new Card(Card.Rank.King,Card.Suit.Diamonds));
		hand3.add(new Card(Card.Rank.Queen,Card.Suit.Diamonds));

		// straight flush
		Hand hand4 = new Hand("Clyde");
		hand4.add(new Card(Card.Rank.Nine,Card.Suit.Clubs));
		hand4.add(new Card(Card.Rank.Eight,Card.Suit.Clubs));
		hand4.add(new Card(Card.Rank.Seven,Card.Suit.Clubs));
		hand4.add(new Card(Card.Rank.Six,Card.Suit.Clubs));
		hand4.add(new Card(Card.Rank.Five,Card.Suit.Clubs));

		round.submit(hand1);
		round.submit(hand2);
		round.submit(hand3);
		round.submit(hand4);
			
		List<String> expected = new ArrayList<String>
			(Arrays.asList("Randy", "Clyde", "Jody", "Ulric"));
		assertEquals(expected,round.rankResults());
	}
	
	
	/**
	 * print out the hand 
	 * e.g. "playerName RankSuit RankSuit RankSuit RankSuit RankSuit"
	 */
	@Test
	public void test_handStringOutput(){
		hand = new Hand("Ulric");
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Ace,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.King,Card.Suit.Hearts));
		hand.add(new Card(Card.Rank.King,Card.Suit.Clubs));
		hand.add(new Card(Card.Rank.Queen,Card.Suit.Diamonds));
		
		String expected = 
				"Ulric AceClubs AceHearts KingHearts KingClubs QueenDiamonds";
		
		assertEquals(expected, hand.printHand());
	}
	
	
	



	

}
