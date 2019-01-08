package ua.nure.balagura.practice1;

public class Part6 {

	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		int[] arr = new int[num];
		int toArray = 2;

		for (int i = 0; i < arr.length;) {
			if (isPrime(toArray)) {
				arr[i] = toArray;
				i++;
			}
			toArray++;
		}
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	static boolean isPrime(int prime) {
		boolean result = true;
		for (int i = 2; i <= Math.sqrt(prime); i++) {
			if (prime % i == 0) {
				result = false;
				break;
			}
		}
		return result;
	}

}
