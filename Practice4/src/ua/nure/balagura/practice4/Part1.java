package ua.nure.balagura.practice4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

	static String PATH_TO_FILE = "part1.txt";
	static final String ENCODING = "windows-1251";
	static final int WORD_LENGTH_FROM = 4;

	public static void main(String[] args) {
		
		String input = FileInOut.read(PATH_TO_FILE, ENCODING);
		
		
		System.out.print(capitalize(input, WORD_LENGTH_FROM));
	}

	static String capitalize(String input, int wordLengthFrom) {
		if (input == null) {
			System.out.println("There is no text to work with!");
			throw new IllegalArgumentException();
		}

		Pattern p = Pattern.compile("(?U)[\\w]{" + wordLengthFrom + ",}");
		Matcher m = p.matcher(input);

		StringBuilder sb = new StringBuilder(input);

		while (m.find()) {
			sb.replace(m.start(), m.end(), (m.group()).toUpperCase());
		}
		return sb.toString();
	}
}