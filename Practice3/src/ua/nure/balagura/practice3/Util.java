package ua.nure.balagura.practice3;

import java.io.*;
import java.util.Scanner;

public class Util {
	public static String getInput(String fileName) {
		StringBuilder sb = new StringBuilder();
		try {
			Scanner scanner = new Scanner(new File(fileName), "cp1251");
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine()).append(System.lineSeparator());
			}
			scanner.close();
			return sb.toString().trim();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String input = Util.getInput("part1.txt");
		System.out.println(input);
	}
}