package ua.nure.balagura.practice1;

public class Demo {

	public static void main(String[] args) {
		System.out.println("~~~ Part1 ~~~ \"Hello, World\" ~~~");
		Part1.main(new String[] {});
		System.out.println("~~~ Part2 ~~~ sum of two numbers~~~");
		Part2.main(new String[] { "2", "3" });
		System.out.println("~~~ Part3 ~~~ output ~~~");
		Part3.main(new String[] { "a", "b", "c", "d", "34" });
		System.out.println("~~~ Part4 ~~~ greatest common divisor ~~~");
		Part4.main(new String[] { "25", "15" });
		System.out.println("~~~ Part5 ~~~ sum of digits of number ~~~");
		Part5.main(new String[] { "1234" });
		System.out.println("~~~ Part6 ~~~ array of prime numbers ~~~");
		Part6.main(new String[] { "7" });
		System.out.println("~~~ Part7 ~~~ chars to digits to chars ~~~");
		Part7.main(new String[] {});
	}
}
