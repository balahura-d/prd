package ua.nure.balagura.practice6;

import java.util.LinkedList;
import java.util.List;

public class Graph {

	private int vertecies;
	private List<Integer> ribBegin = new LinkedList<Integer>();
	private List<Integer> ribEnd = new LinkedList<Integer>();

	public Graph(int n) {
		vertecies = n;
	}

	public boolean addEdge(int a, int b) {
		verify(a, b);
		Integer A = a;
		Integer B = b;
		if (A > B) {
			Integer C = B;
			B = A;
			A = C;
		}
		for (int i = 0; i < ribBegin.size(); i++) {
			if ((ribBegin.get(i).equals(A)) && (ribEnd.get(i).equals(B)))
				return false;
		}
		ribBegin.add(A);
		ribEnd.add(B);
		return true;
	}

	private void verify(int a, int b) {
		if (a == b)
			throw new IllegalArgumentException("Rib must have different begin and end!");
		if ((a > vertecies) | (b > vertecies))
			throw new IllegalArgumentException("Graph don't have so many vertecies!");
	}

	public boolean removeEdge(int a, int b) {
		verify(a, b);
		Integer A = a;
		Integer B = b;
		for (int i = 0; i < ribBegin.size(); i++)
			if ((ribBegin.get(i).equals(A)) && (ribEnd.get(i).equals(B))) {
				ribBegin.remove(i);
				ribEnd.remove(i);
				return true;
			}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Graph have " + vertecies + " vertecies and following ribs: [");
		for (int i = 0; i < ribBegin.size(); i++) {
			sb.append("(" + ribBegin.get(i) + ", " + ribEnd.get(i) + "); ");
		}
		return sb.append(']').toString();
	}

	
}
