package ua.nure.balagura.practice6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Part2 {

	public static final int N = 20000;
	public static final int K = 7;

	public static void main(String[] args) {
		List<Object> arrayList = new ArrayList<Object>();
		List<Object> linkedList = new LinkedList<Object>();

		init(arrayList, N);
		long begin = System.currentTimeMillis();
		remove(arrayList, K);
		System.out.printf("Removing all Integers except %d on ArrayList with indexes takes %d millis%n",
				arrayList.get(0), (System.currentTimeMillis() - begin));

		init(linkedList, N);
		begin = System.currentTimeMillis();
		remove(linkedList, K);
		System.out.printf("Removing all Integers except %d on LinkedList with indexes takes %d millis%n",
				linkedList.get(0), (System.currentTimeMillis() - begin));

		init(arrayList, N);
		begin = System.currentTimeMillis();
		remove2(arrayList, K);
		System.out.printf("Removing all Integers except %d on ArrayList with iterator takes %d millis%n", arrayList.get(0),
				(System.currentTimeMillis() - begin));

		init(linkedList, N);
		begin = System.currentTimeMillis();
		remove2(linkedList, K);
		System.out.printf("Removing all Integers except %d on LinkedList with iterator takes %d millis%n", linkedList.get(0),
				(System.currentTimeMillis() - begin));
	}

	private static void remove(List<Object> list, int k) {
		int target = 0;
		while (list.size() > 1) {
			int size = list.size();
			target += k;
			while (target >= size) {
				target -= size;
			}
			list.remove(target);
		}
	}

	private static void remove2(List<Object> list, int k) {
		Iterator<Object> it = list.iterator();
		while (list.size() > 1) {
			for (int i = 0; i <= k; i++) {
				if (it.hasNext()) {
					it.next();
				} else {
					it = list.iterator();
					it.next();
				}
			}
			it.remove();
		}
	}

	private static void init(List<Object> list, int n) {
		list.clear();
		for (int i = 0; i < n; i++) {
			list.add(i);
		}
	}

}
