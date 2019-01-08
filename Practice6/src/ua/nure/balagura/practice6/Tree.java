package ua.nure.balagura.practice6;

public class Tree<E extends Comparable<E>> {
	int elements = 0;
	private Node<E> root = null;

	// ��������� ������� � ���������
	// ���� � ���������� ���� ������� ������ �� compareTo ������������,
	// �� ���������� �� ���������� � ����� ���������� false
	// � ��������� ������ ������� �������� � ��������� � ����� ���������� true
	// ������ ����������� ������� ���������� ������ ������
	public boolean add(E element) {
		if (element == null)
			throw new IllegalArgumentException();
		if (root == null) {
			root = new Node<E>(element);
			elements++;
			return true;
		} else
			return add(element, root);
	}

	private boolean add(E element, Node<E> node) {
		// if (node.data == null) {
		// node.data = element;
		// return true;
		// } else
		if ((node.data.compareTo(element)) < 0) {
			if (node.right == null) {
				node.right = new Node<E>(element);
				node.right.parent = node;
				elements++;
				return true;
			} else
				return add(element, node.right);
		} else if ((node.data.compareTo(element)) > 0) {
			if (node.left == null) {
				node.left = new Node<E>(element);
				node.left.parent = node;
				elements++;
				return true;
			} else
				return add(element, node.left);
		} else
			return false;

	}

	// ��������� ��� �������� �� ������� � ��������� (����� � ����� ������ add, ��.
	// ����)
	public void add(E[] elements) {
		for (int i = 0; i < elements.length; i++) {
			add(elements[i]);
		}
	}

	// ������� ������� �� ����������
	// ���� ���������� �������� � ���������� ���, �� ���������� false
	// � ��������� ������ ������� ������� � ���������� true
	// �����! ��� �������� �������� ������ �� ������ �������� ��������
	// ��������� ������ ������

	public boolean remove(E element) {
		if (elements == 0)
			return false;
		else if (elements == 1) {
			root = null;
			return true;
		}
		return remove(element, root);
	}

	private boolean remove(E element, Node<E> node) {
		if (element.compareTo(node.data) == 0) {
			removeNode(node);
			return true;
		} else if (element.compareTo(node.data) > 0)
			return (node.right == null) ? (false) : (remove(element, node.right));
		else
			return (node.left == null) ? (false) : (remove(element, node.left));
	}

	private void removeNode(Node<E> node) {
		boolean leftNull = (node.left == null);
		boolean rightNull = (node.right == null);
		if (leftNull & rightNull) { // no children
			if (node.equals(node.parent.left))
				node.parent.left = null;
			else
				node.parent.right = null;
		} else if (leftNull ^ rightNull) { // one child
			if (leftNull) {
				if (node.equals(node.parent.left)) {
					node.parent.left = node.right;
				} else {
					node.parent.right = node.right;
				}
			} else {
				if (node.equals(node.parent.left)) {
					node.parent.left = node.left;
				} else {
					node.parent.right = node.left;
				}
			}
		} else { // two child
			
		}
		elements--;
	}

	// ������������� ������, ��� ����� ���� ����� ��� ����������� ���������,
	// ��. ���� ������

	public void print() {
		System.out.println(elements);
		return;
	}

	public Node<E> find(E e) {
		return null;
	}

	// ��������� �����, ������� ����� ������ ���������� ������
	private static class Node<E> {
		private E data = null;
		private Node<E> left = null;
		private Node<E> right = null;
		private Node<E> parent = null;

		private Node(E e) {
			data = e;
		}
	}
}
