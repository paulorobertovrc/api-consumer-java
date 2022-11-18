package br.com.conversor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectToApi {
	private String urlString = "";
	private URL url;

	ConnectToApi(String userChoice) throws IOException {
		// Consume public API
		urlString = "https://economia.awesomeapi.com.br/json/" + userChoice + "-BRL";

		try {
			url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.connect();			
		} catch (Exception e) {
			System.out.println("Please verify your connection and try again. If the problem persists, contact the system administrator.");
		}
	}
	
	public URL getUrl() {
		return this.url;
	}
}
