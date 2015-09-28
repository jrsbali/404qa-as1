package com.poker;

public class MaxCardsLimitException extends Exception {
	public MaxCardsLimitException() {
		super("Maximum of 5 cards have been reached for a hand");
	}

}
