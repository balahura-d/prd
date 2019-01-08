package ua.nure.balagura.practice6;

public class Part3 {

	public static void main(String[] args) {
		Parking p = new Parking(5);
		p.status();
		System.out.println("Arriving AAA to 2 place: " + p.arrive("AAA", 2));
		System.out.println("Arriving BBB to 2 place: " + p.arrive("BBB", 2));
		System.out.println("Arriving CCC to 2 place: " + p.arrive("CCC", 2));
		System.out.println("Arriving DDD to 2 place: " + p.arrive("DDD", 2));
		p.status();
		try {
			System.out.println("Arriving BBB to 2 place: " + p.arrive("BBB", 2));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Departing BBB :" + p.depart("BBB"));
		System.out.println("Departing CCC :" + p.depart("CCC"));
		System.out.println("Departing EEE :" + p.depart("EEE"));
		p.status();
	}
}
