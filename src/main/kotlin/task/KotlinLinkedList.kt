package task

class KotlinLinkedList<T> {
    class Node<T>(val value: T, var next: Node<T>? = null)

    private var head: Node<T>? = null

    fun append(newItem: T) {
        val newNode = Node(newItem)
        if (head == null) {
            head = newNode
            return
        }

        var cur = head
        while (cur?.next != null)
            cur = cur.next

        cur?.next = newNode
    }

    fun get(index: Int): T {
        var node = head
        for (i in 0 until index)
            node = node?.next

        return node?.value ?: throw IndexOutOfBoundsException("Index $index is out of range")
    }

    fun count(): Int = TODO()

    /*
	* Inserts an element into the list at the specified index.
	* For example:
	* [] and insert "a" at 0 = ["a"]
	* ["a"] and insert "b" at 0 = ["b", "a"]
	* ["b", "a"] and insert "c" at 1 = ["b", "c", "a"]
	* ["b", "c", "a"] and insert "d" at 3 = ["b", "c", "a", "d"]
	* */
    fun insert(index: Int, newItem: T): Unit = TODO()
}