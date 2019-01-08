package ua.nure.balagura.practice4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Part5 {

	private static final String ENCODING = "Cp1251";

	public static void main(String[] args) {
		String input;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in, ENCODING))) {
			while ((input = br.readLine()) != null) {
				if (input.equals("stop")) break;
				System.out.println(getLineToPrint(input.split("\\s")));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private static String getLineToPrint(String... keyAndLocal) {
		String output = "";
		if (keyAndLocal.length == 2) {
			Locale locale = new Locale(keyAndLocal[1]);
			ResourceBundle rb = ResourceBundle.getBundle("resources", locale);
			output = rb.getString(keyAndLocal[0]);
		}
		return output;
	}
}
