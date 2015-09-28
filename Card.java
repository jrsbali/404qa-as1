package com.poker;

public class Card implements Comparable<Card> {
	public enum Suit {
		Diamonds, Hearts, Spades, Clubs
	}

	public enum Rank {
		Ace(1), // ace is the highest card of its suit
		Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(
				10), Jack(11), Queen(12), King(13);

		private int val;

		Rank(int val) {
			this.val = val;
		}

		int getVal() {
			return val;
		}
	} // Rank definition

	private Rank rank;
	private Suit suit;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public String toString() {
		return rank.toString() + suit.toString();
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	@Override
	public boolean equals(Object other) {
		Card otherCard = (Card) other;

		if (this == otherCard)
			return true;
		if (!(other instanceof Card))
			return false;

		if (this.suit == otherCard.getSuit()
				&& this.rank == otherCard.getRank())
			return true;

		return false;
	}

	@Override
	public int hashCode() {
		return (13 * rank.getVal()) + (71 * suit.hashCode());
	}

	/**
	 * sorts cards in descending order
	 */
	public int compareTo(Card other) {
		int thisVal = (this.rank.getVal() == 1) ? 20 : this.rank.getVal();
		int otherVal = (other.getRank().getVal() == 1) ? 20 : other.getRank()
				.getVal();

		return otherVal - thisVal;
	}

}
