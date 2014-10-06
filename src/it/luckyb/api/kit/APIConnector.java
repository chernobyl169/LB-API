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
	
	public static JSONObject get(String method) throws IOException { return get(method, ""); }
	public static JSONObject get(String method, String parameter) throws IOException {
		StringBuilder sb = new StringBuilder();
		String url = BASE + "/" + method.toLowerCase(Locale.US);
		if (!parameter.equals("")) url = url + "/" + parameter;
		HttpURLConnection conn = (HttpURLConnection)(new URL(url).openConnection());
		conn.connect();
		InputStream is = conn.getInputStream();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = reader.readLine()) != null) sb.append(line+"\n");
		} catch (IOException e) {
			is.close();
			conn.disconnect();
			throw e;
		}
		is.close();
		conn.disconnect();
		return new JSONObject(sb.toString());			
	}
}
