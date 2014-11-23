package it.luckyb.api.kit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import org.json.JSONObject;

public class APIConnector {
	private APIConnector() {}

	public static String BASE = "http://luckyb.it/api";
	public static int LIMIT = 100;
	public static int PAGE = 1;
	
	public static JSONObject get(String method) throws IOException
		{ return get(method, ""); }
	public static JSONObject get(String method, String parameter) throws IOException
		{ return get(method, parameter, LIMIT, PAGE); }
	public static JSONObject get(String method, String parameter, int limit, int page) throws IOException {
		StringBuilder sb = new StringBuilder();
		String url = BASE + "/" + method.toLowerCase(Locale.US);
		if (!parameter.equals("")) url = url + "/" + parameter;
		if (limit != LIMIT || page != PAGE)
			url = url + "?limit=" + limit + "&page=" + page;
		HttpURLConnection conn = (HttpURLConnection)(new URL(url).openConnection());
		try {
			conn.connect();
			InputStream is = conn.getInputStream();
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String line = null;
				while ((line = reader.readLine()) != null) sb.append(line+"\n");
			} catch (IOException e) {
				is.close();
				throw e;
			}
			is.close();
			conn.disconnect();
		} catch (IOException e) {
			conn.disconnect();
			throw e;
		}
		return new JSONObject(sb.toString());			
	}
}
