package husaynhakeem.io.unconnectifyre.data.converter

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test


class IntegerListConverterShould {

    private val integerListConverter = IntegerListConverter()

    @Test
    fun convertFromEmptyIntegerListToString() {
        val values = emptyList<Int>()

        val result = integerListConverter.fromIntegerListToString(values)

        assertEquals("", result)
    }

    @Test
    fun convertFromNonEmptyIntegerListToString() {
        val values = listOf(1, 2, 3, 4, 5)

        val result = integerListConverter.fromIntegerListToString(values)

        assertEquals("1,2,3,4,5", result)
    }

    @Test
    fun convertFromEmptyStringToIntegerList() {
        val s = ""

        val result = integerListConverter.fromStringToIntegerList(s)

        assertTrue(result.isEmpty())
    }

    @Test
    fun convertFromNonEmptyStringToIntegerList() {
        val s = "1,2,3,4,5"

        val result = integerListConverter.fromStringToIntegerList(s)

        assertEquals(5, result.size)
        assertEquals(1, result[0])
        assertEquals(2, result[1])
        assertEquals(3, result[2])
        assertEquals(4, result[3])
        assertEquals(5, result[4])
    }
}