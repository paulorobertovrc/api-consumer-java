package br.com.conversor;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		System.out.println("::::::::::::: CURRENCY CONVERTER :::::::::::::");
		System.out.println("Select the desired currency: [1] USD | [2] EUR");
		System.out.print("> ");
		
		Scanner sc = new Scanner(System.in);
		int selectCurrency = sc.nextInt();
		
		while(selectCurrency != 1 && selectCurrency != 2) {
			System.out.println("Invalid option.");
			System.out.println("Type 1 for USD or 2 for EUR");
			System.out.print("> ");
			
			selectCurrency = sc.nextInt();
		}
		sc.close();
		
		String userChoice;
		if (selectCurrency == 1 ) {
			userChoice = "USD";
		} else {
			userChoice = "EUR";
		}
		
		ConnectToApi conn = new ConnectToApi(selectCurrency);
		StringBuilder s = new StringBuilder();
		Scanner sc2 = new Scanner(conn.getUrl().openStream());
		while(sc2.hasNext()) {
			s.append(sc2.nextLine());
		}
		sc2.close();
		
		Double price = calculatePrice(s);
		
		System.out.println();
		System.out.println("Current Exchange Rate: 1 " + userChoice + " = " + price + " BRL");
	}

	private static Double calculatePrice(StringBuilder s) throws ParseException {
		// Convert the returned values to JSON object 
		JSONParser jParser = new JSONParser();
		JSONArray jArray = (JSONArray) jParser.parse(String.valueOf(s));
		JSONObject jObj = (JSONObject) jArray.get(0);
		
		// Get the value from the "bid" key, as explained in the API's documentation
		String priceString = jObj.get("bid").toString();
		
		// Convert the price from string to double format
		Double price = Double.valueOf(priceString);
	
		return price;
	}
}
