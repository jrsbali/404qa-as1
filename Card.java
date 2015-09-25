package com.poker;

public class Card {
	public enum Suit { Diamonds, Hearts, Spades, Clubs}
	public enum Rank {
		Ace(100), // ace is the highest card of its suit
		Two(2), Three(3),
		Four(4), Five(5), Six(6),
		Seven(7), Eight(8), Nine(9),
		Ten(10), Jack(11), Queen(12),
		King(13);
		
		private int val;
		
		Rank(int val){
			this.val = val;
		}
		
		int getVal(){
			return val;
		}

	}
	
	
	Rank rank;
	Suit suit;
	public Card() {
	}
	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}	

	public String toString() {
		return rank.toString()+suit.toString();
	}
	
	public Suit getSuit(){
		return suit;
	}
	
	public Rank getRank(){
		return rank;
	}
	
}
