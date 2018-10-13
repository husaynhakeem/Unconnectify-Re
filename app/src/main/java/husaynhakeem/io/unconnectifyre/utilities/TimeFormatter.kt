package husaynhakeem.io.unconnectifyre.utilities

import android.content.Context
import husaynhakeem.io.unconnectifyre.R
import husaynhakeem.io.unconnectifyre.data.database.Time


fun formatTimeForDisplay(context: Context, hour: Int, minute: Int): String {
    val formattedHour = if (hour == 0) "00" else hour.toString()
    val formattedMinute = if (minute < 10) "0$minute" else minute.toString()
    return context.getString(R.string.time_format, formattedHour, COLON, formattedMinute)
}

/**
 * @displayedTime Time in the format hh:mm
 */
fun fromDisplayedTimeToTime(displayedTime: String): Time {
    return try {
        val timeParts = displayedTime.split(COLON).map { it.toInt() }
        Time(hour = timeParts[0], minute = timeParts[1])
    } catch (e: Exception) {
        Time(0, 0, 0)
    }
}