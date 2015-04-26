package it.luckyb.api;

/**
 * A class representing a single bet placed on Lucky Bit.
 * @author stingleword
 */
public abstract class Bet implements Comparable<Bet> {
	/**
	 * @return
	 * The amount of bitcoin wagered
	 */
	public abstract double amount();
	/**
	 * @return
	 * The raw binary of the result
	 */
	public abstract String rawBinary();
	/**
	 * @return
	 * The timestamp of the game
	 */
	public abstract String timestamp();
	/**
	 * @return
	 * The player address
	 */
	public abstract String address();
	/**
	 * @return
	 * The multiplier achieved
	 */
	public abstract double multiplier();
	/**
	 * @return
	 * The amount of bitcoin won
	 */
	public abstract double payout();
	/**
	 * @return
	 * Whether the bet was valid, i.e. between min and max
	 */
	public abstract boolean isValid();
	/**
	 * @return
	 * The transaction ID of the bet
	 */
	public abstract String txIn();
	/**
	 * @return
	 * The transaction output number (vout) of the bet
	 */
	public abstract int txInVOut();
	/**
	 * @return
	 * The transaction ID of the payment
	 */
	public abstract String txOut();
	/**
	 * @return
	 * The color played
	 */
	public abstract String game();
}
