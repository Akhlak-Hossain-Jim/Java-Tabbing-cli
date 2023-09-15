package org.example;

import java.util.Scanner;

public class NumberInput {
	public static int isInt(Scanner inp) {
		try {
			String s = inp.nextLine();
			int x = Integer.parseInt(s);
			return x;
		} catch (Exception e) {
			System.err.println("\n\tShould be an Integer number.\n");
			return -1;
		}
	}

	public static Double isDouble(Scanner inp) {
		try {
			String s = inp.nextLine();
			Double x = Double.parseDouble(s);
			return x;
		} catch (Exception e) {
			System.err.println("\n\tShould be a Decimal number.\n");
			return -1.0;
		}
	}
}
