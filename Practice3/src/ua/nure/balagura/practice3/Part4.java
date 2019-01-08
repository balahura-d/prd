package ua.nure.balagura.practice3;

import java.security.*;

public class Part4 {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(hash("password", "SHA-256"));
		System.out.println(hash("passwort", "SHA-256"));
	}

	public static String hash(String input, String algorithm)
			throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.update(input.getBytes());
		byte[] hash = digest.digest();

		StringBuilder sb = new StringBuilder("");
		for (byte b : hash) {
			sb.append(byteToHexString(b).toUpperCase());
		}
		return sb.toString();
	}

	private static String byteToHexString(final byte input) {
		String first, second;
		second = Integer.toHexString(input & 0xF);
		first = Integer.toHexString((input & 0xF0) >>> 4);
		return first + second;
	}
}