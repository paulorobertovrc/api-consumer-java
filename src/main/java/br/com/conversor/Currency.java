package br.com.conversor;

public class Currency {
	private String name;
	private double price;
	private String dateTime;
	

	public Currency(String name, double price, String dateTime) {
		this.name = name;
		this.price = price;
		this.dateTime = dateTime;
		
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
	
	public String getDate(String dateTime) {
		String date = dateTime.substring(0, 11);
		String reversedDate = "";
		
		for (int i = date.length(); i > 0; i--) {
			Character c = date.charAt(i);
			reversedDate = reversedDate + c;
		}
		
		return reversedDate;
	}
}
