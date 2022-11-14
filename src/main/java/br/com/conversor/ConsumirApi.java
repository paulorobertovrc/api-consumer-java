package br.com.conversor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConsumirApi {
	public static void main(String[] args) throws IOException, ParseException {
		URL url = new URL("https://economia.awesomeapi.com.br/json/USD-BRL");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		
		StringBuilder s = new StringBuilder();
		Scanner sc = new Scanner(url.openStream());
		while(sc.hasNext()) {
			s.append(sc.nextLine());
		}
		sc.close();		
		
		JSONParser parser = new JSONParser();
		JSONArray json = (JSONArray) parser.parse(String.valueOf(s));
		JSONObject cotacao = (JSONObject) json.get(0);
		
		System.out.println(cotacao.get("bid"));
	}

}
