package husaynhakeem.io.unconnectifyre.utilities

import android.support.test.InstrumentationRegistry
import husaynhakeem.io.unconnectifyre.data.database.Time
import org.junit.Assert.assertEquals
import org.junit.Test


class TimeFormatterShould {

    private val context = InstrumentationRegistry.getTargetContext()

    @Test
    fun formatTimeForDisplay_whenHourAndMinuteAreSmallerThanTen() {
        val hour = 8
        val minute = 6

        val result = formatTimeForDisplay(context, hour, minute)

        assertEquals("8:06", result)
    }

    @Test
    fun formatTimeForDisplay_whenOnlyMinuteIsSmallerThanTen() {
        val hour = 17
        val minute = 8

        val result = formatTimeForDisplay(context, hour, minute)

        assertEquals("17:08", result)
    }

    @Test
    fun formatTimeForDisplay_whenHourAndMinuteAreBiggerThanTen() {
        val hour = 23
        val minute = 52

        val result = formatTimeForDisplay(context, hour, minute)

        assertEquals("23:52", result)
    }

    @Test
    fun formatTimeForDisplay_whenHourIsMidnight() {
        val hour = 0
        val minute = 37

        val result = formatTimeForDisplay(context, hour, minute)

        assertEquals("00:37", result)
    }

    @Test
    fun convertValidTimeStringToTimeInstance() {
        val displayedTime = "00:08"

        val result = fromDisplayedTimeToTime(displayedTime)

        assertEquals(0, result.hour)
        assertEquals(8, result.minute)
    }

    @Test
    fun convertInvalidTimeStringToTimeInstance() {
        val displayedTime = "00,08"

        val result = fromDisplayedTimeToTime(displayedTime)

        assertEquals(Time(0, 0, 0), result)
    }
}