package ua.nure.balagura.practice5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Part4Service {
	
	public static void main(String[] args) {
		System.out.println("create and write...");
		try {
			createAndWriteMatrix(5, 10, "part4.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int[][] arr = null;
		System.out.println("read...");
		try {
			arr = readMatrix("part4.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("result:");
		for (int[] i : arr) {
			System.out.println(Arrays.toString(i));
		}
	}

	public static int[][] readMatrix(String filename) throws IOException {
		int[][] result;
		ArrayList<String[]> arrList = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String ch;
		while ((ch = br.readLine()) != null) {
			arrList.add(ch.split("\\s"));
		}
		
		result = new int[arrList.size()][];
		for (int i = 0; i < result.length; i++) {
			result[i] = new int[arrList.get(i).length];
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = Integer.valueOf(arrList.get(i)[j]);
			}
		}
		return result;
	}

	public static int[][] createMatrix(int m, int n) {
		int[][] result = new int[m][];
		for (int i = 0; i < result.length; i++) {
			result[i] = new int[n];
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = (int) (Math.random() * 1000);
			}
		}
		return result;
	}

	public static void createAndWriteMatrix(int m, int n, String fileName) throws IOException {
		File file = new File(fileName);
		file.createNewFile();
		int[][] matrix = createMatrix(m, n);
		BufferedWriter bfw = new BufferedWriter(new FileWriter(file));
		
		for (int[] is : matrix) {
			for (int i : is) {
				bfw.write(Integer.toString(i));
				bfw.write(" ");
			}
			bfw.write(System.lineSeparator());
		}
		bfw.close();
	}
}
