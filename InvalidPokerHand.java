package com.poker;

public class InvalidPokerHand extends Exception {
	public InvalidPokerHand() {
		super("Must have a poker hand of FIVE cards");
	}

}
