package br.com.conversor;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		System.out.println("::::::::::::::::: CURRENCY CONVERTER :::::::::::::::::");
		System.out.println("Select a currency: [1] USD | [2] EUR");
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
		
		try {
			Currency currency = new Currency(userChoice);			
			
			System.out.println();
			System.out.print("Current Exchange Rate	:	");
			System.out.println("1 " + userChoice + " = " + currency.getPrice() + " BRL");
			System.out.print("Last Update		:	"); 
			System.out.println(currency.getDate() + "  " + currency.getTime());
			System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			System.out.print("[ERROR] ");
			System.out.println(e.getMessage());
		}
	}
}
