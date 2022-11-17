package br.com.conversor;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Currency {
	private double price;
	private String date = "";
	private String time = "";
	private StringBuilder jsonString;
	private JSONObject json;

	public Currency(String userChoice) throws IOException, ParseException {		
		ConnectToApi conn = new ConnectToApi(userChoice);
		jsonString = new StringBuilder();
		Scanner sc2 = new Scanner(conn.getUrl().openStream());
		while(sc2.hasNext()) {
			jsonString.append(sc2.nextLine());
		}
		sc2.close();
		
		json = this.generateJson(jsonString);
	}

	public double getPrice() throws ParseException {
		this.price = calculatePrice(json);
		return price;
	}
	
	public String getDate() {
		generateDateFromString();
		return date;
	}
	
	public String getTime() {
		generateTimeFromString();
		return time;
	}
	
	public JSONObject generateJson(StringBuilder s) throws ParseException {
		// Convert the returned values to JSON object 
		JSONParser jParser = new JSONParser();
		JSONArray jArray = (JSONArray) jParser.parse(String.valueOf(s));
		JSONObject jObj = (JSONObject) jArray.get(0);
	
		return jObj;
	}
	
	public Double calculatePrice(JSONObject jObj) {
		// Get the value from the "bid" key, as explained in the API's documentation
		String priceString = jObj.get("bid").toString();
		
		// Convert the price from string to double format
		Double price = Double.valueOf(priceString);
		
		return price;
	}
	
	public String generateDateFromString() {
		this.date = json.get("create_date").toString();
		this.date = date.substring(0, 10);
		
		String reversedDay = "";
		String reversedMonth = "";
		String reversedYear = "";
		
		for (int i = 0; i < 4; i++) {
			Character c = date.charAt(i);
			reversedYear = reversedYear + c;
		}
		
		for (int j = 5; j < 7; j++) {
			Character c = date.charAt(j);
			reversedMonth = reversedMonth + c;
		}
		
		for (int k = 8; k < 10; k++) {
			Character c = date.charAt(k);
			reversedDay = reversedDay + c;
		}
		
		this.date = reversedDay + "/" + reversedMonth + "/" + reversedYear;
		
		return date;
	}
	
	public String generateTimeFromString() {
		this.time = json.get("create_date").toString();
		this.time = time.substring(11);
		
		return time;
	}
}
