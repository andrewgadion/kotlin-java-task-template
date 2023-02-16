package task;

import kotlin.NotImplementedError;

public class JavaLinkedList<T> {
	private static class Node<T> {
		Node<T> next = null;
		T value;
		Node(T value) {
			this.value = value;
		}
	}

	private Node<T> head = null;

	public void append(T newItem) {
		var newNode = new Node<>(newItem);
		if (head == null) {
			head = newNode;
			return;
		}

		var cur = head;
		while (cur.next != null)
			cur = cur.next;

		cur.next = newNode;
	}

	public T get(int index) {
		if (head == null)
			throw new IndexOutOfBoundsException(String.format("Index %d is out of range", index));

		var node = head;
		for (int i = 0; i < index; i++)
		{
			node = node.next;
			if (node == null)
				throw new IndexOutOfBoundsException(String.format("Index %d is out of range", index));
		}

		return node.value;
	}

	public int count() {
		throw new NotImplementedError();
	}

	public int insert(int index, T newItem) {
		throw new NotImplementedError();
	}
}
