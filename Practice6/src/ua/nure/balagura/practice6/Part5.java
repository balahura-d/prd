package ua.nure.balagura.practice6;

public class Part5 {
	public static void main(String[] args) {

		Tree<Integer> tree = new Tree<>();
		System.out.println(tree.add(3));
		System.out.println(tree.add(-7));
		System.out.println(tree.add(3));
		System.out.println(tree.add(-7));
		System.out.println("~~~~~~~");
		tree.print();
		tree.add(new Integer[] { 1, 2, 5, 4, 6, 0, 999 });
		tree.print();
		System.out.println("~~~~~~~");
		System.out.println(tree.remove(999));
		System.out.println(tree.remove(999));
		System.out.println("~~~~~~~");
		tree.print();
	}
}
