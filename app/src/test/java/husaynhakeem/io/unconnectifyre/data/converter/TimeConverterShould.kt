package husaynhakeem.io.unconnectifyre.data.converter

import husaynhakeem.io.unconnectifyre.data.Time
import org.junit.Assert.assertEquals
import org.junit.Test


class TimeConverterShould {

    private val timeConverter = TimeConverter()

    @Test
    fun convertFromTimeToString() {
        val time = Time(3, 0)

        val result = timeConverter.fromTimeToString(time)

        assertEquals("3:0", result);
    }

    @Test
    fun convertFromStringToTime() {
        val s = "3:0"

        val result = timeConverter.fromStringToTime(s)

        assertEquals(3, result.start);
        assertEquals(0, result.end);
    }

    @Test
    fun returnDefaultTime_whenConvertingFromInvalidStringToTime() {
        val s = "3,0"

        val result = timeConverter.fromStringToTime(s)

        assertEquals(0, result.start);
        assertEquals(0, result.end);
    }
}