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
		url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
	}
	
	public URL getUrl() {
		return this.url;
	}
}
