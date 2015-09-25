package com.poker;

import junit.framework.TestCase;

public class TestPoker extends TestCase {
	
	public void test_RoundWithOver4Players(){
		Round round = new Round();
		round.submit(new Hand());
		round.submit(new Hand());
		round.submit(new Hand());
		round.submit(new Hand());
		round.submit(new Hand());
		
		assertFalse(round.validNumberOfPlayers());		
	}
	
	public void test_RoundWith1Player(){
		Round round = new Round();
		round.submit(new Hand());
		
		assertFalse(round.validNumberOfPlayers());
	}
	
	public void test_RoundWithNoPlayer(){
		Round round = new Round();
		assertFalse(round.validNumberOfPlayers());
	}
	
	
	public void test_validPokerHand(){
		Hand hand = new Hand();
		assertFalse(hand.validPokerHand());
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
