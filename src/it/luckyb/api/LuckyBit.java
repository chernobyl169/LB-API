package it.luckyb.api;

import it.luckyb.api.kit.*;

import java.io.IOException;
import java.util.List;

/**
 * Lucky Bit API for Java. This source is released AS-IS with
 * no warranty or declaration of fitness for use.
 * 
 * Access the API by using the static methods of this class.
 * Each call returns Java objects representing the requested data.
 * If no data was found, null or an empty list will be returned.
 * All calls can throw an IOException.
 * 
 * Every call to this class produces a call to the Lucky Bit API server.
 * Please re-use your data whenever possible.
 * @author stingleword
 */
public class LuckyBit {
	private LuckyBit() {}
	
	/**
	 * Get a list of all active games
	 * @return
	 * A list of all active games
	 * @throws
	 * IOException if there was a communication error
	 */
	public static List<Game> getGames() throws IOException {
		return ObjectFactory.getGames(APIConnector.get("getgames"));
	}
	/**
	 * Get details about a game by name
	 * @param name
	 * The name of the game
	 * @return
	 * The requested game or null if that game does not exist
	 * @throws IOException
	 */
	public static Game getGameByName(String name) throws IOException {
		return ObjectFactory.getGame(APIConnector.get("getgamebyname", name), name);
	}
	/**
	 * Get recent bets by page.
	 * @param limit
	 * The number of bets per page
	 * @param page
	 * The page number to retrieve
	 * @return
	 * A list of the bets matching the request
	 * @throws IOException
	 */
	public static List<Bet> getBets(int limit, int page) throws IOException {
		return ObjectFactory.getBets(APIConnector.get("getbets", "", limit, page));
	}
	/**
	 * Get bets by date.
	 * @param yyyy
	 * The year of the date to query
	 * @param mm
	 * The numeric month of the date to query
	 * @param dd
	 * The numeric day of the date to query
	 * @param limit
	 * The number of bets per page
	 * @param page
	 * The page number requested
	 * @return
	 * A list of bets matching the query
	 * @throws IOException
	 */
	public static List<Bet> getBetsByDate(int yyyy, int mm, int dd, int limit, int page) throws IOException {
		return ObjectFactory.getBets(APIConnector.get("getbetsbydate", String.format("%4d%2d%2d", yyyy, mm, dd), limit, page));
	}
	/**
	 * Get bets placed from a given address.
	 * @param address
	 * The address bets were placed from
	 * @param limit
	 * The number of bets per page
	 * @param page
	 * The page number requested
	 * @return
	 * A list of bets matching the query
	 * @throws
	 * IOException if there was a communication error
	 */
	public static List<Bet> getBetsByAddress(String address, int limit, int page) throws IOException {
		return ObjectFactory.getBets(APIConnector.get("getbetsbyaddress", address, limit, page));
	}
	/**
	 * Get all bets placed in a given transaction
	 * @param txid
	 * The transaction ID the bets occupy on the blockchain
	 * @return
	 * A list of all bets contained in that transaction
	 * @throws
	 * IOException if there was a communication error
	 */
	public static List<Bet> getBetsByTXID(String txid) throws IOException {
		return ObjectFactory.getBets(APIConnector.get("getbetsbytxid", txid, 200, 1));
	}
	/**
	 * Get a single bet by transaction ID and VOut
	 * @param txid
	 * The transaction ID the bet was placed in
	 * @param vout
	 * The VOut within the transaction of the bet
	 * @return
	 * The requested bet or null if that output was not a bet
	 * @throws
	 * IOException if there was a communication error
	 */
	public static Bet getBetByTXIDVOut(String txid, int vout) throws IOException {
		String txidvout = txid + ":" + vout;
		return ObjectFactory.getBet(APIConnector.get("getbetbytxidvout", txidvout), txidvout);
	}
	/**
	 * Get today's secret seed hash
	 * @return
	 * The SHA256 of today's secret seed
	 * @throws
	 * IOException if there was a communication error
	 */
	public static Daily getCurrentHash() throws IOException {
		return ObjectFactory.getDaily(APIConnector.get("getcurrenthash"));
	}
	/**
	 * Get any secret seed hash, past or future
	 * @param year
	 * The year of the seed's active date
	 * @param month
	 * The month of the seed's active date
	 * @param day
	 * The day of the seed's active date
	 * @return
	 * The hash of the secret seed for the given date or null if the
	 * date was invalid
	 * @throws
	 * IOException if there was a communication error
	 */
	public static Daily getHashByDate(int year, int month, int day) throws IOException {
		return ObjectFactory.getDaily(APIConnector.get("gethashbydate", String.format("%4d-%2d-%2d", year, month, day)));
	}
	/**
	 * Get a past secret seed
	 * @param year
	 * The year of the seed's active date
	 * @param month
	 * The month of the seed's active date
	 * @param day
	 * The day of the seed's active date
	 * @return
	 * The secret seed for the given date, or null if the date was
	 * after yesterday or invalid
	 * @throws
	 * IOException if there was a communication error
	 */
	public static Daily getKeyByDate(int year, int month, int day) throws IOException {
		return ObjectFactory.getDaily(APIConnector.get("getkeybydate", String.format("%4d-%2d-%2d", year, month, day)));
	}
}
