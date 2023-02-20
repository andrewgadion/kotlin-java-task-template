package task

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import java.util.stream.Stream


typealias ActualList<T> = JavaLinkedList<T>

class LinkedListTest {

    private fun <T> create(elements: List<T>) = ActualList<T>().also { elements.forEach(it::append) }

    private fun <T> assertContentEquals(actual: ActualList<T>, expected: List<T>) {
        try {
            (0 until actual.count()).forEach {
                assertEquals(expected[it], actual.get(it))
            }
        } catch (error: AssertionError) {
            printItems(actual, expected)
            throw error
        }
    }

    private fun <T> printItems(actual: ActualList<T>, expected: List<T>) {
        var actualItemsString = ""

        (0 until actual.count()).forEach {
            actualItemsString += "${actual.get(it)}, "
        }

        println("Actual:   [${actualItemsString.removeSuffix(", ")}]\nExpected: [${expected.joinToString()}]")
    }

    companion object {
        @JvmStatic
        fun giveDataForCountTest() = Stream.of(listOf(), listOf(1), listOf(1, 2, 3))

        @JvmStatic
        fun giveDataForInsertTest() = Stream.of(
            Arguments.of(emptyList<String>(), "a" to 0, listOf("a")),
            Arguments.of(listOf("a"), "b" to 0, listOf("b", "a")),
            Arguments.of(listOf("a"), "b" to 1, listOf("a", "b")),
            Arguments.of(listOf("a", "b", "c"), "d" to 2, listOf("a", "b", "d", "c")),
            Arguments.of(listOf("a", "b", "c"), "d" to 3, listOf("a", "b", "c", "d")),
            Arguments.of(listOf("a", "b", "c"), "d" to 1, listOf("a", "d", "b", "c"))
        )

        @JvmStatic
        fun giveDataForReverseTest() = Stream.of(listOf(), listOf(1), listOf(1, 2), listOf(1, 2, 3), listOf(1, 2, 3, 4))
    }

    @ParameterizedTest
    @MethodSource("giveDataForCountTest")
    fun count(initList: List<Int>) {
        assertEquals(initList.size, create(initList).count())
    }

    @ParameterizedTest
    @MethodSource("giveDataForInsertTest")
    fun insert(initItems: List<String>, insertParams: Pair<String, Int>, expectedItems: List<String>) {
        val actualList = create(initItems)
        val (item, index) = insertParams
        actualList.insert(index, item)
        assertContentEquals(actualList, expectedItems)
    }

    @Test
    fun insertError() {
        assertThrows(Throwable::class.java) {
            val initList = listOf(1, 2, 3)
            val actualList = create(initList)
            actualList.insert(initList.size, 4)
        }
    }

//    @ParameterizedTest
//    @MethodSource("giveDataForReverseTest")
//    fun reverse(initList: List<Int>) {
//        assertContentEquals(create(initList).also(ActualList<Int>::reverse), initList.reversed())
//    }
}