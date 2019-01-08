package ua.nure.balagura.practice2;

import java.util.Iterator;

public class MyListImpl implements MyList, ListIterable {

	private Object[] container = new Object[] {};
	int size = 0;

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("[");
		for (Object object : container) {
			sb.append(object/* .toString() */ + ", ");
		}
		if (size != 0) {
			sb.delete(sb.length() - 2, sb.length());
		}
		sb.append("]");
		return sb.toString();

	}

	@Override
	public void add(Object e) {
		size++;
		Object[] temp = new Object[size];
		System.arraycopy(container, 0, temp, 0, size - 1);
		temp[size - 1] = e;
		container = temp;
	}

	@Override
	public void clear() {
		container = new Object[] {};
		size = 0;
	}

	@Override
	public boolean remove(Object o) {
		int i;
		if (o == null) {
			for (i = 0; i < size; i++) {
				if (container[i] == null) {
					removeAt(i);
					return true;
				}
			}
		} else {
			for (i = 0; i < size; i++) {
				if ((container[i] != null) && o.equals(container[i])) {
					removeAt(i);
					return true;
				}
			}
		}
		return false;
	}

	public boolean removeAt(int index) {
		Object[] temp = new Object[size - 1];
		System.arraycopy(container, 0, temp, 0, index);
		System.arraycopy(container, index + 1, temp, index, size - index - 1);
		container = temp;
		size--;
		return true;
	}

	@Override
	public Object[] toArray() {
		return container;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (container[i] == null)
					return true;
			}
		} else {
			for (int i = 0; i < size; i++) {
				if ((container[i] != null) && o.equals(container[i]))
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(MyList c) {
		Object[] arr = c.toArray();
		for (int i = 0; i < c.size(); i++) {
			if (!contains(arr[i]))
				return false;
		}
		return true;
	}

	@Override
	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}

	class IteratorImpl implements Iterator<Object> {

		protected int iter = 0;
		protected int checked = -1;

		@Override
		public boolean hasNext() {
			if (iter < size) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public Object next() {
			checked = iter;
			return container[iter++];
		}

		public void remove() {
			if (checked < 0)
				throw new IllegalStateException();
			checked = -1;
			removeAt(--iter);
			return;
		}
	}

	@Override
	public ListIterator listIterator() {
		return new ListIteratorImpl();
	}

	private class ListIteratorImpl extends IteratorImpl implements ListIterator {

		@Override
		public boolean hasPrevious() {
			if (iter > 0)
				return true;
			return false;
		}

		@Override
		public Object previous() {
			checked = --iter;
			return container[iter];
		}

		@Override
		public void set(Object e) {
			if (checked < 0)
				throw new IllegalStateException();
			checked = -1;
			container[iter] = e;
		}
	}

}
