package com.poker;

public class InvalidHandsException extends Exception {
	public InvalidHandsException() {
		super("Both hands must be of equal ranking type");
	}

}
