package ua.nure.balagura.practice1;

public class Part5 {

	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		int result = 0;

		while (num != 0) {
			result += num % 10;
			num /= 10;
		}

		System.out.println(result);

	}

}
