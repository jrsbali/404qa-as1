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
		
		assertFalse(false, round.validNumberOfPlayers());
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
