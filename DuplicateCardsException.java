package com.poker;

public class DuplicateCardsException extends Exception{
	
	public DuplicateCardsException() {
		super("Duplicate cards detected!");
	}

}
