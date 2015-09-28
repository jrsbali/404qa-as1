package com.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Round {

	private List<Hand> hands;
	final private int MAX_HANDS = 4;
	final private int MIN_HANDS = 2;
	private Set<Card> cardsInPlay;

	public Round() {
		hands = new ArrayList<Hand>();
		cardsInPlay = new HashSet<Card>();
	}

	/**
	 * 
	 * Validates playerHands prior to ranking them
	 * 
	 * @param playerHand
	 * @throws DuplicateIDException
	 * @throws DuplicateCardsException
	 * @throws InvalidPokerHand
	 * @throws MaxHandsLimitException
	 */
	public void submit(Hand playerHand) throws DuplicateIDException,
			DuplicateCardsException, InvalidPokerHand, MaxHandsLimitException {

		if (hands.size() + 1 > MAX_HANDS)
			throw new MaxHandsLimitException();

		if (!playerHand.validPokerHand())
			throw new InvalidPokerHand();

		if (hasDuplicateCards(playerHand))
			throw new DuplicateCardsException();

		if (isUniqueID(playerHand.getPlayerID())) {
			hands.add(playerHand);
		} else
			throw new DuplicateIDException();
	} // end submit

	private boolean hasDuplicateCards(Hand playerHand) {
		for (Card c : playerHand.getCards()) {
			if (!cardsInPlay.add(c))
				return true;
		}
		return false;
	}

	private boolean isUniqueID(String playerID) {
		if (hands.isEmpty())
			return true;
		for (Hand h : hands)
			if (h.getPlayerID().equalsIgnoreCase(playerID))
				return false;

		return true;
	}

	public boolean validNumberOfPlayers() {
		int numberOfPlayers = hands.size();
		return numberOfPlayers >= 2 && numberOfPlayers <= 4;
	}

	/**
	 * returns a list of playerIDs listing them from highest to lowest ranking
	 * 
	 * @return
	 */
	public List<String> rankResults() throws MinimumHandsException {
		if (hands.size() < MIN_HANDS)
			throw new MinimumHandsException();
		List<String> rankings = new ArrayList<String>();
		for (Hand h : sortReceivedHands()) {
			rankings.add(h.getPlayerID());
		}
		return rankings;
	}

	/**
	 * sorts the hands in descending order by using their ranking type, also
	 * accounts for ranking DRAW hand types e.g.(highest) RoyalFlush,
	 * StraightFlush, Flush, HighCard (lowest)
	 * 
	 * @return
	 */
	private List<Hand> sortReceivedHands() {
		Collections.sort(hands);
		// turn into an array to easily swap between elements
		Hand[] cardsArr = new Hand[hands.size()];
		hands.toArray(cardsArr);
		Hand first = null;
		Hand second = null;
		Hand temp = null;
		for (int i = 0; i < hands.size() - 1; i++) {
			for (int j = i; j < hands.size() - 1; j++) {
				first = cardsArr[j];
				second = cardsArr[j + 1];
				if (hasSameRank(first, second)) {
					try {
						// if first is is ranked lower than second,
						// swap them, in order to maintain descending
						// order of the winner
						if (tieBreaker(first, second) == -1) {
							temp = cardsArr[j];
							cardsArr[j] = cardsArr[j + 1];
							cardsArr[j + 1] = temp;
						}
					} catch (InvalidHandsException ex) {
						ex.getMessage();
					}// end try-catch block

				}
			}
		}// end for-loop
		return new ArrayList<Hand>(Arrays.asList(cardsArr));
	}

	public List<String> rankResultsWithHandType() {
		List<String> results = new ArrayList<String>();
		for (Hand h : sortReceivedHands()) {
			results.add(h.getPlayerID() + " " + h.getHandRanking());
		}
		return results;
	}

	/**
	 *
	 * @param first
	 * @param second
	 * @return returns true if first and second are of the same handTypeRanking
	 *         e.g. RoyalFlush=RoyalFlush, HighCard=HighCard
	 */
	private boolean hasSameRank(Hand first, Hand second) {
		return first.getResult() == second.getResult();
	}

	/**
	 * Called when two hands are of the same type and must rank them according
	 * to their high cards
	 * 
	 * @param first
	 * @param second
	 * @return 1=first is ranked higher, -1=second is ranked higher, 0=both
	 *         equal
	 * @throws InvalidHandsException
	 */
	public int tieBreaker(Hand first, Hand second) throws InvalidHandsException {
		if (first.getResult() != second.getResult())
			throw new InvalidHandsException();

		int[] firstHand = first.cardsToNumericArray();
		int[] secondHand = second.cardsToNumericArray();

		for (int i = 0; i < firstHand.length; i++) {
			if (firstHand[i] > secondHand[i])
				return 1;
			if (firstHand[i] < secondHand[i])
				return -1;
		}

		return 0;
	}

}
