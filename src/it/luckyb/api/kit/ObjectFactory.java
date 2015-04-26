package it.luckyb.api.kit;

import java.util.LinkedList;
import java.util.List;

import it.luckyb.api.*;

import org.json.JSONException;
import org.json.JSONObject;

public class ObjectFactory {
	private static ObjectFactory theFactory = new ObjectFactory();
	private ObjectFactory() {}
	private class BetImpl extends Bet {
		private double amount, result, payout;
		private String address, binary, txin, txout, timestamp, game;
		private int txinvout;
		private boolean isValid;
		private BetImpl() {}
		@Override
		public double amount() { return amount; }
		@Override
		public String rawBinary() { return binary; }
		@Override
		public String timestamp() { return timestamp; }
		@Override
		public String address() { return address; }
		@Override
		public double multiplier() { return result; }
		@Override
		public double payout() { return payout; }
		@Override
		public boolean isValid() { return isValid; }
		@Override
		public String txIn() { return txin; }
		@Override
		public int txInVOut() { return txinvout; }
		@Override
		public String txOut() { return txout; }
		@Override
		public String game() { return game; }
		@Override
		public String toString() {
			return String.format(
				"%s - %-5.3f x" +
					((result<2&&result!=1.0)?"%-1.1f":"%-1.0f") +
					" = %-1.3f (%s)",
				address, amount, result, payout, game);
		}
		@Override
		public int compareTo(Bet b) { return timestamp.compareTo(b.timestamp()); }
	}
	public static Bet getBet(JSONObject o, String txidvout) {
		if (o == null || o.length() == 0 || !o.has(txidvout)) return null;
		BetImpl bet = theFactory.new BetImpl();
		bet.isValid = false;
		JSONObject data = o.getJSONObject(txidvout);
		if (data.getString("type").equalsIgnoreCase("valid_bet"))
			bet.isValid = true;
		try {
			bet.address = data.getString("player_address");
			bet.amount = data.getDouble("bet_amount");
			bet.game = data.getString("game_name");
			bet.timestamp = data.getString("created_at");
			bet.txin = data.getString("txin_id");
			bet.txinvout = data.getInt("txin_vout");
			bet.binary = data.getString("binary_string");
			bet.result = data.getDouble("multiplier_obtained");
			bet.payout = data.getDouble("payout_amount");
			bet.txout = data.getString("txout_id");
		} catch(JSONException e) {
			// The bet was invalid, so some fields didn't exist.
			// That's okay; carry on!
		}
		return bet;
	}
	public static List<Bet> getBets(JSONObject o) {
		List<Bet> bets = new LinkedList<Bet>();
		if (o != null && o.length() > 0)
			for (String txv : o.keySet())
				bets.add(getBet(o, txv));
		return bets;
	}
	private class GameImpl extends Game {
		private String color, address;
		private double minbet, maxbet;
		private double[] payline = {0,0,0,0,0,0,0,0,0};
		private GameImpl() {}
		@Override
		public String color() { return color; }
		@Override
		public String address() { return address; }
		@Override
		public double maxBet() { return maxbet; }
		@Override
		public double minBet() { return minbet; }
		@Override
		public double[] payline() { return payline; }
		@Override
		public String toString() { return color; }
	}
	public static Game getGame(JSONObject o, String name) {
		if (o == null || o.length() == 0 || !o.has(name)) return null;
		GameImpl game = theFactory.new GameImpl();
		game.color = name;
		JSONObject data = o.getJSONObject(name);
		game.address = data.getString("address");
		game.maxbet = data.getDouble("max_amount");
		game.minbet = data.getDouble("min_amount");
		data = data.getJSONObject("multipliers");
		for (String i : data.keySet())
			game.payline[Integer.valueOf(i)] = data.getDouble(i);
		return game;
	}
	public static List<Game> getGames(JSONObject o) {
		List<Game> games = new LinkedList<Game>();
		if (o != null && o.length() > 0) 
			for (String name : o.keySet())
				games.add(getGame(o, name));
		return games;
	}
	private class DailyImpl extends Daily {
		private String date, data;
		private DailyImpl() {}
		@Override
		public String date() { return date; }
		@Override
		public String data() { return data; }
		@Override
		public String toString() { return String.format("%s: %s", date, data); }
	}
	public static Daily getDaily(JSONObject o) {
		if (o == null || o.length() == 0) return null;
		DailyImpl daily = theFactory.new DailyImpl();
		daily.date = o.keys().next();
		daily.data = o.getString(daily.date);
		return daily;
	}
}
