package it.luckyb.api;

/**
 * A class representing a game on Lucky Bit.
 * @author stingleword
 */
public abstract class Game {
	/**
	 * @return
	 * The color of the game
	 */
	public abstract String color();
	/**
	 * @return
	 * The bitcoin address of the game
	 */
	public abstract String address();
	/**
	 * @return
	 * The maximum bet of the game
	 */
	public abstract double maxBet();
	/**
	 * @return
	 * The minimum bet of the game
	 */
	public abstract double minBet();
	/**
	 * @return
	 * An array of nine multiplier values, where the first is the center
	 * and the last is the outermost edge
	 */
	public abstract double[] payline();
}
