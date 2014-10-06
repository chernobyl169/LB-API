package it.luckyb.api;

/**
 * A class representing a daily server seed or its hash.
 * @author stingleword
 */
public abstract class Daily {
	/**
	 * @return
	 * The date of this seed or hash, in YYYY-MM-DD format
	 */
	public abstract String date();
	/**
	 * @return
	 * The seed or hash of the seed
	 */
	public abstract String data();
}
