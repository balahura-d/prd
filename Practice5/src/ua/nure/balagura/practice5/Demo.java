package ua.nure.balagura.practice5;

import java.io.IOException;

public class Demo {

	public static void main(String[] args) {

		System.out.println("\\\\\\\\\\\\\\\\\\ PART 1 //////////////////");
		
		Part1.main(null);
		
		System.out.println("\\\\\\\\\\\\\\\\\\ PART 2 //////////////////");
		
		try {
			Part2.main(null);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("\\\\\\\\\\\\\\\\\\ PART 3 //////////////////");
		
		try {
			Part3.main(null);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("\\\\\\\\\\\\\\\\\\ PART 4 //////////////////");
		
		try {
			Part4Service.createAndWriteMatrix(4, 100, "part4.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Part4.main(null);

		System.out.println("\\\\\\\\\\\\\\\\\\ PART 4 //////////////////");
	}
}
