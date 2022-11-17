package br.com.conversor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectToApi {
	private String urlString = "";
	private URL url;

	ConnectToApi(int currency) throws IOException {
		// Consume public API
		if (currency == 1) {
			urlString = "https://economia.awesomeapi.com.br/json/USD-BRL";
		} else {
			urlString = "https://economia.awesomeapi.com.br/json/EUR-BRL";
		}
		
		url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
	}
	
	public URL getUrl() {
		return this.url;
	}
}
