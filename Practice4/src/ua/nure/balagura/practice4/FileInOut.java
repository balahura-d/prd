package ua.nure.balagura.practice4;

import java.io.*;

public class FileInOut {
	
	public static String read(String pathToFile, String encoding) {
		StringBuilder sb = new StringBuilder();
		String ch;
		try (BufferedReader fileIn = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(pathToFile), encoding))) {
			while ((ch = fileIn.readLine()) != null) {
				sb.append(ch).append(System.lineSeparator());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static void write(int[] intArray, String pathToFile, String encoding) {
		try (BufferedWriter fileOut = new BufferedWriter(	
                new OutputStreamWriter(
                        new FileOutputStream(pathToFile), encoding))) {
			for (int i : intArray) {
				fileOut.write(Integer.toString(i));
				fileOut.write(" ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
