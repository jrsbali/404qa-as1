package com.poker;

public class Card {
	String rank;
	String suit;
	public Card() {
	}
	
	public Card(String rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}	

	public String toString() {
		return rank+suit;
	};
	
}
