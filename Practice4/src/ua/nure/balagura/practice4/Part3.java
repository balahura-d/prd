package ua.nure.balagura.practice4;

import java.util.Scanner;

public class Part3 {

	final static String INPUT_FILE = "part3.txt";
	static final String ENCODING = "Cp1251";

	public static void main(String[] args) {

		try (Scanner input = new Scanner(System.in);) {
			String in = null;
			while (((in = input.nextLine())) != "stop") {
				switch (in) {
				case "char":
					printValuesOfType("(?U)[\\D&&\\w]{1}");
					break;
				case "String":
					printValuesOfType("(?U)[\\D&&\\w]{2,}");
					break;
				case "int":
					printValuesOfType("(?<!\\.)\\d+");
					break;
				case "double":
					printValuesOfType("\\d*\\.\\d*");
					break;
				case "stop":
					return;
				default:
					System.out.println(in + " is invalid input!");
				}
			}
		}
	}

	public static void printValuesOfType(String regex) {

		String s = FileInOut.read(INPUT_FILE, ENCODING);
		String[] values = s.split("\\s");
		for (String string : values) {
			if (string.matches(regex)) {
				System.out.print(string + " ");
			}
		}
		System.out.println();
	}
}