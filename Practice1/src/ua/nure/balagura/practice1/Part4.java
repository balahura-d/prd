package ua.nure.balagura.practice1;

public class Part4 {

	public static void main(String[] args) {
		int num1 = Integer.parseInt(args[0]);
		int num2 = Integer.parseInt(args[1]);

		while ((num1 != 0) & (num2 != 0)) {
			if (num1 > num2) {
				num1 %= num2;
			} else {
				num2 %= num1;
			}
		}

		System.out.println(num1 + num2);
	}

}
