package ua.nure.balagura.practice3;

import java.util.LinkedHashSet;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

	public static void main(String[] args) {
		String input = Util.getInput("part2.txt");

		System.out.println(Part2.convert(input));
	}

	public static String convert(String input) {
		StringBuilder sb = new StringBuilder();
		Pattern p = Pattern.compile("(?U)[\\w&&\\D]+");
		Matcher m = p.matcher(input);
		TreeMap<Integer, LinkedHashSet<String>> treeMap = new TreeMap<Integer, LinkedHashSet<String>>();
		LinkedHashSet<String> linkedHashSet = null;

		while (m.find()) {
			int begin = m.start();
			int end = m.end();
			int length = end - begin;
			linkedHashSet = treeMap.get(length);
			if (linkedHashSet == null) {
				linkedHashSet = new LinkedHashSet<String>();
			}
			linkedHashSet.add(m.group());
			treeMap.put(length, linkedHashSet);
		}

		java.util.Map.Entry<Integer, LinkedHashSet<String>> entry = treeMap
				.firstEntry();
		sb.append("Min: ");
		for (String word : entry.getValue()) {
			sb.append(word + ", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(System.lineSeparator());

		entry = treeMap.lastEntry();
		sb.append("Max: ");
		for (String word : entry.getValue()) {
			sb.append(word + ", ");
		}
		sb.delete(sb.length() - 2, sb.length());

		return sb.toString();
	}
}