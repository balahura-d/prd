package ua.nure.balagura.practice4;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
	public static void main(String[] args) {
		final int COUNT = 10;
		final String FIRST_F = "part2.txt";
		final String SECOND_F = "part2_sorted.txt";
		final String ENCODING = "Cp1251";
		// ===================================================
		int[] arr1 = createArray(COUNT);
		FileInOut.write(arr1, FIRST_F, ENCODING);
		// ===================================================
		String s = FileInOut.read(FIRST_F, ENCODING);
		int[] arr2 = toArray(s);	
		sort(arr2);
		FileInOut.write(arr2, SECOND_F, ENCODING);
		// ===================================================
		System.out.println("input  ==> " + FileInOut.read(FIRST_F, ENCODING));
		System.out.println("output ==> " + FileInOut.read(SECOND_F, ENCODING));
	}
 
	public static void sort(int[] arr) {
		boolean isSorted = false;
		int bubble;
		while (!isSorted) {
			isSorted = true;
			for (int i = 0; i < arr.length - 1; i++) {
				if (arr[i] > arr[i + 1]) {
					isSorted = false;

					bubble = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = bubble;
				}
			}
		}
	}

	public static int[] createArray(int length) {
		int[] output = new int[length];
		for (int i = 0; i < output.length; i++) {
			output[i] = (int) (Math.random() * 50);
		}
		return output;
	}

	public static int[] toArray(String s) {
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(s);
		ArrayList<Integer> arr = new ArrayList<Integer>();

		while (m.find()) {
			arr.add(Integer.valueOf(m.group()));
		}

		int[] output = new int[arr.size()];
		for (int i = 0; i < output.length; i++) {
			output[i] = arr.get(i);
		}
		return output;
	}
}
