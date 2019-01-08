package ua.nure.balagura.practice1;

public class Part7 {

	public static void main(String[] args) {
		String[] outputArr = { "A", "B", "Z", "AA", "AZ", "BA", "ZZ", "AAA" };
		for (String string : outputArr) {
			System.out.println(string + " ==> " 
					   + str2int(string) + " ==> "
					   + int2str(str2int(string)));
		}
		// System.out.println("A ==> " + chars2digits("A") + " ==> " +
		// digits2chars(chars2digits("A")));
		// System.out.println("B ==> " + chars2digits("B") + " ==> " +
		// digits2chars(chars2digits("B")));
		// System.out.println("Z ==> " + chars2digits("Z") + " ==> " +
		// digits2chars(chars2digits("Z")));
		// System.out.println("AA ==> " + chars2digits("AA") + " ==> " +
		// digits2chars(chars2digits("AA")));
		// System.out.println("AZ ==> " + chars2digits("AZ") + " ==> " +
		// digits2chars(chars2digits("AZ")));
		// System.out.println("BA ==> " + chars2digits("BA") + " ==> " +
		// digits2chars(chars2digits("BA")));
		// System.out.println("ZZ ==> " + chars2digits("ZZ") + " ==> " +
		// digits2chars(chars2digits("ZZ")));
		// System.out.println("AAA ==> " + chars2digits("AAA") + " ==> "
		// + digits2chars(chars2digits("AAA")));
	}

	public static int str2int(String number) {
		int digits = 0;
		int diff = 'A' - 1;
		for (int i = 0; i < number.length(); i++) {
			digits += (number.charAt(i) - diff) * (int) Math.pow(26, (number.length() - (i + 1)));
			// int step1 = (number.charAt(i) - diff);
			// int step2 = number.length() - (i + 1);
			// int step3 = (int)Math.pow(26, step2);
			// int step4 = step1 * step3;
			// digits += step4;
		}
		return digits;
	}

	public static String int2str(int number) {
		StringBuilder chars = new StringBuilder();
		int diff = 'A' - 1;
		while (number > 26) {
			int step1 = number % 26;
			if (step1 == 0) {
				step1 = 26;
				number -= 26;
			}
			chars.append((char) (step1 + diff));
			number /= 26;
		}
		chars.append((char) (number + diff));
		return chars.reverse().toString();
	}

	public static String rightColumn(String number) {
		return int2str(str2int(number) + 1);
	}
}