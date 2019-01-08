package ua.nure.balagura.practice3;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

	public static void main(String[] args) {
		String input = Util.getInput("part1.txt");

		System.out.println(Part1.convert1(input));
		System.out.println(Part1.convert2(input));
		System.out.println(Part1.convert3(input));
		System.out.println(Part1.convert4(input));
	}

	public static String convert1(String input) {
		StringBuilder sb1 = new StringBuilder();
		Pattern p = Pattern.compile("(.+);(.+);(.+)");
		Matcher m = p.matcher(input);

		m.find();
		while (m.find()) {
			sb1.append(m.group(1)).append(" ==> ").append(m.group(3)).append(System.lineSeparator());
		}

		return sb1.toString();
	}

	public static String convert2(String input) {
		StringBuilder sb2 = new StringBuilder("");
		Pattern p = Pattern.compile("(?m)(.+?)$");
		Matcher m = p.matcher(input);
		m.find();
		m.usePattern(Pattern.compile("(?m)(.+?);(.+?)\\s(.+?);(.+)$"));
		while (m.find()) {
			sb2.append(m.group(3)).append(" ").append(m.group(2)).append(" (email: ").append(m.group(4)).append(")")
					.append(System.lineSeparator());
		}
		return sb2.toString();
	}

	public static String convert3(String input) {
		StringBuilder sb3 = new StringBuilder();
		Pattern p = Pattern.compile("(.+?);(.+)@(.+)");
		Matcher m = p.matcher(input);
		Map<String, LinkedHashSet<String>> domainAndLogins = new LinkedHashMap<String, LinkedHashSet<String>>();
		LinkedHashSet<String> logins = null;

		while (m.find()) {
			logins = domainAndLogins.get(m.group(3));
			if (logins == null) {
				logins = new LinkedHashSet<String>();
			}
			logins.add(m.group(1));
			domainAndLogins.put(m.group(3), logins);
		}

		for (Map.Entry<String, LinkedHashSet<String>> entry : domainAndLogins.entrySet()) {
			sb3.append(entry.getKey() + " ==> ");
			logins = entry.getValue();
			for (String login : logins) {
				sb3.append(login + ", ");
			}
			sb3.delete(sb3.length() - 2, sb3.length());
			sb3.append(System.lineSeparator());
		}

		return sb3.toString();
	}

	public static String convert4(String input) {
		StringBuilder sb4 = new StringBuilder();
		Pattern p = Pattern.compile("(?m)^(.+?);(.+?);(.+?)$");
		Matcher m = p.matcher(input);
		m.find();
		sb4.append(m.group()).append(";Password").append(System.lineSeparator());
		while (m.find()) {
			sb4.append(m.group()).append(";").append(random()).append(System.lineSeparator());
		}
		return sb4.toString();
	}

	public static int random() {
		int result;
		while (true) {
			result = (int) (Math.random() * 10000);
			if (result > 999)
				return result;
		}
	}
}
