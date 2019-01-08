package ua.nure.balagura.practice3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Part5 {
	public static void main(String[] args) {
		for (int i = 1; i <= 100; i++) {
			System.out.print(i + " ====> ");
			System.out.print(decimal2Roman(i) + " ====> ");
			System.out.println(roman2Decimal(decimal2Roman(i)));
		}
	}

	public static String decimal2Roman(int input) {
		if (input < 1 || input > 3999)
			return "Invalid Roman Number Value";
		StringBuilder sb = new StringBuilder();
		while (input >= 1000) {
			sb.append("M");
			input -= 1000;
		}
		while (input >= 900) {
			sb.append("CM");
			input -= 900;
		}
		while (input >= 500) {
			sb.append("D");
			input -= 500;
		}
		while (input >= 400) {
			sb.append("CD");
			input -= 400;
		}
		while (input >= 100) {
			sb.append("C");
			input -= 100;
		}
		while (input >= 90) {
			sb.append("XC");
			input -= 90;
		}
		while (input >= 50) {
			sb.append("L");
			input -= 50;
		}
		while (input >= 40) {
			sb.append("XL");
			input -= 40;
		}
		while (input >= 10) {
			sb.append("X");
			input -= 10;
		}
		while (input >= 9) {
			sb.append("IX");
			input -= 9;
		}
		while (input >= 5) {
			sb.append("V");
			input -= 5;
		}
		while (input >= 4) {
			sb.append("IV");
			input -= 4;
		}
		while (input >= 1) {
			sb.append("I");
			input -= 1;
		}
		return sb.toString();
	}

	public static int roman2Decimal(String s) {
		Map<String, Integer> romanNumbers = new HashMap<String, Integer>();
		StringBuilder sb = new StringBuilder(s);
		Integer result = 0;
		Set<String> keySet = null;
		String inclusion = null;

		romanNumbers.put("I", 1);
		romanNumbers.put("IV", 4);
		romanNumbers.put("V", 5);
		romanNumbers.put("IX", 9);
		romanNumbers.put("X", 10);
		romanNumbers.put("XL", 40);
		romanNumbers.put("L", 50);
		romanNumbers.put("XC", 90);
		romanNumbers.put("C", 100);
		romanNumbers.put("CD", 400);
		romanNumbers.put("D", 500);
		romanNumbers.put("CM", 900);
		romanNumbers.put("M", 1000);

		keySet = romanNumbers.keySet();

		while (sb.length() != 0) {
			if ((sb.length() > 1) && keySet.contains(inclusion = sb.substring(0, 2))) {
				result += romanNumbers.get(inclusion);
				sb.delete(0, 2);
			} else if (keySet.contains(inclusion = sb.substring(0, 1))) {
				result += romanNumbers.get(inclusion);
				sb.deleteCharAt(0);
			} else {
				System.out.println("FAIL");
			}
		}
		return result;
	}
}