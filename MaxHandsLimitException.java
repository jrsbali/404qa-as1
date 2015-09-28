package com.poker;

public class MaxHandsLimitException extends Exception{
	
	public MaxHandsLimitException() {
		super("Maximium of FOUR hands has been submitted!");
	}

}
