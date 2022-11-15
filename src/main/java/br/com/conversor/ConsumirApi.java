package br.com.conversor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConsumirApi {
	public static void main(String[] args) throws IOException, ParseException {
		// Consume the public API 
		URL url = new URL("https://economia.awesomeapi.com.br/json/USD-BRL");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		
		// Read the values returned from the API
		StringBuilder s = new StringBuilder();
		Scanner sc = new Scanner(url.openStream());
		while(sc.hasNext()) {
			s.append(sc.nextLine());
		}
		sc.close();		
		
		// Convert the returned values to JSON object 
		JSONParser jParser = new JSONParser();
		JSONArray jArray = (JSONArray) jParser.parse(String.valueOf(s));
		JSONObject jObj = (JSONObject) jArray.get(0);
		
		// Get the value from the "bid" key, as explained in the API's documentation
		String priceString = jObj.get("bid").toString();
		
		// Convert the price from string to double format
		Double price = Double.valueOf(priceString);
		System.out.println(price);
	}
}
