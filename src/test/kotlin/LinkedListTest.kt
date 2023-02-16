package task

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

private data class InsertTestData<T>(
    val initialItems: List<T>,
    val insertParams: Pair<Int, T>,
    val expectedItems: List<T>
)

typealias ListToVerify<T> = JavaLinkedList<T>

class LinkedListTest {

    private fun <T> create(vararg elements: T) = ListToVerify<T>().also { elements.forEach(it::append) }

    private fun <T> assertContentEqual(expected: List<T>, actual: ListToVerify<T>) {
        (0 until actual.count()).forEach {
            assertEquals(expected[it], actual.get(it))
        }
    }

    @Test
    fun count() {
        assertEquals(0, create<Int>().count())
        assertEquals(1, create(1).count())
        assertEquals(3, create(1, 1, 1).count())
    }

    @Test
    fun insert() {
        listOf(
            InsertTestData(emptyList(), 0 to 1, listOf(1)),
            InsertTestData(listOf(1), 0 to 2, listOf(2, 1)),
            InsertTestData(listOf(1), 1 to 2, listOf(1, 2)),
            InsertTestData(listOf(1, 2, 3), 2 to 4, listOf(1, 2, 4, 3)),
            InsertTestData(listOf(1, 2, 3), 3 to 4, listOf(1, 2, 3, 4)),
            InsertTestData(listOf(1, 2, 3), 1 to 4, listOf(1, 4, 2, 3)),
        ).forEach { (initialItems, insertParams, expectedItems) ->
            val actualList = create(*initialItems.toTypedArray())
            val (index, item) = insertParams
            actualList.insert(index, item)
            assertContentEqual(expectedItems, actualList)
        }
    }

//    @Test
//    fun reverse() {
//        listOf(
//            listOf(),
//            listOf(1),
//            listOf(1, 2),
//            listOf(1, 2, 3),
//            listOf(1, 2, 3, 4),
//        ).forEach { input ->
//            val actualList = create(*input.toTypedArray())
//            actualList.reverse()
//            assertContentEqual(input.reversed(), actualList)
//        }
//    }
}