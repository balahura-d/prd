package ua.nure.balagura.practice6;

public class Part4 {
	public static void main(String[] args) {
		Graph g = new Graph(7);
		
		System.out.println("g.addEdge(1, 3) " + g.addEdge(1, 3));
		System.out.println("g.addEdge(1, 4) " + g.addEdge(1, 4));
		System.out.println("g.addEdge(1, 6) " + g.addEdge(1, 6));
		System.out.println("g.addEdge(3, 4) " + g.addEdge(3, 4));
		System.out.println("g.addEdge(4, 3) " + g.addEdge(4, 3));
		System.out.println("g.addEdge(5, 3) " + g.addEdge(5, 3));
		try {
			g.addEdge(2, 8);
		} catch (Exception e) {
		System.out.println("g.addEdge(2, 8) " + e.getMessage());}
		try {
			g.addEdge(2, 2);
		} catch (Exception e) {
			System.out.println("g.addEdge(2, 2) " + e.getMessage());}
		System.out.println(g);
		System.out.println("g.removeEdge(1, 4) " + g.removeEdge(1, 4));
		System.out.println("g.removeEdge(1, 4) " + g.removeEdge(1, 4));
		System.out.println("g.removeEdge(1, 6) " + g.removeEdge(1, 6));
		System.out.println(g);
	}
}
