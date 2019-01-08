package ua.nure.balagura.practice6;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

public class Part1 {
	public static final String ENCODING = "cp1251";

	public static void main(String[] args) {
		String example = "asd asd qwer   qwer qwer 123 \nzxc zxc stop asd asd asd asd asd asd";
		try {
			System.setIn(new ByteArrayInputStream(example.getBytes(ENCODING)));
			WordContainer.main(null);
			System.setIn(System.in);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
