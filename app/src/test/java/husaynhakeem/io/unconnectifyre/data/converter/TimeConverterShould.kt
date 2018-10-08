package husaynhakeem.io.unconnectifyre.data.converter

import husaynhakeem.io.unconnectifyre.data.database.Time
import org.junit.Assert.assertEquals
import org.junit.Test


class TimeConverterShould {

    private val timeConverter = TimeConverter()

    @Test
    fun convertFromTimeToString() {
        val time = Time(10, hour = 3, minute = 0)

        val result = timeConverter.fromTimeToString(time)

        assertEquals("10,3,0", result);
    }

    @Test
    fun convertFromStringToTime() {
        val s = "10,3,0"

        val result = timeConverter.fromStringToTime(s)

        assertEquals(10, result.id);
        assertEquals(3, result.hour);
        assertEquals(0, result.minute);
    }

    @Test
    fun returnDefaultTime_whenConvertingFromInvalidStringToTime() {
        val s = "10,3:0"

        val result = timeConverter.fromStringToTime(s)

        assertEquals(0, result.id);
        assertEquals(0, result.hour);
        assertEquals(0, result.minute);
    }
}