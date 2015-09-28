package com.poker;

public class MinimumHandsException extends Exception{
	
	public MinimumHandsException() {
		super("Must have at least 2 submitted hands to get rankings");
	}

}
