package ua.nure.balagura.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
	public static void main(String[] args) {
		String input = Util.getInput("part3.txt");

		System.out.println(Part3.convert(input));
	}

	private static String convert(String input) {
		StringBuilder sb = new StringBuilder(input);
		Pattern p = Pattern.compile("(?U)[\\w&&\\D]+");
		Matcher m = p.matcher(input);

		while (m.find()) {
			if ((m.end() - m.start()) > 2) {
				sb.replace(m.start(), m.start() + 1,
						(sb.substring(m.start(), m.start() + 1)).toUpperCase());
			}
		}

		return sb.toString();
	}
}
