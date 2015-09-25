package com.poker;

import junit.framework.TestCase;

public class TestPoker extends TestCase {
	
	Round round;
	
	public void setUp(){
		round = new Round();
	}
	
	
	public void test_RoundWithOver4Players(){
		round.submit(new Hand());
		round.submit(new Hand());
		round.submit(new Hand());
		round.submit(new Hand());
		round.submit(new Hand());
		
		assertFalse(round.validNumberOfPlayers());		
	}
	
	public void test_RoundWith1Player(){
		round.submit(new Hand());
		assertFalse(round.validNumberOfPlayers());
	}
	
	public void test_RoundWithNoPlayer(){
		assertFalse(round.validNumberOfPlayers());
	}
	
	
	public void test_validPokerHand(){
		Hand hand = new Hand();
		assertFalse(hand.validPokerHand());
	}
	
	public void test_validPokerHandWith5Cards(){
		Hand hand = new Hand();
		hand.add(new Card());
		hand.add(new Card());
		hand.add(new Card());
		hand.add(new Card());
		hand.add(new Card());
		assertTrue(hand.validPokerHand());
	}
	
	
	
	
	
	
	
	
	
	
	

}
