package husaynhakeem.io.unconnectifyre.utilities

import husaynhakeem.io.unconnectifyre.data.Day
import husaynhakeem.io.unconnectifyre.data.database.Time
import java.util.*


private const val SECOND_IN_MS = 1_000


/**
 * @return Number of seconds from current time until @time on @day
 */
fun computeTimeUntil(day: Day, time: Time): Long {

    val nowTimeInMillis = System.currentTimeMillis()

    val now = Calendar.getInstance()
    val nowDayInWeek = now.get(Calendar.DAY_OF_WEEK)
    val nowHourOfDay = now.get(Calendar.HOUR_OF_DAY)
    val nowMinute = now.get(Calendar.MINUTE)

    var daysDifference = (day.value + 1) - nowDayInWeek
    if (daysDifference == 0) {
        val hoursDifference = time.hour - nowHourOfDay
        if (hoursDifference == 0) {
            val minutesDifference = time.minute - nowMinute
            if (minutesDifference < 0) {
                daysDifference = 7
            }
        } else if (hoursDifference < 0) {
            daysDifference = 7
        }
    } else if (daysDifference < 0) {
        daysDifference += 7
    }

    now.add(Calendar.DAY_OF_YEAR, daysDifference)
    now.set(Calendar.HOUR_OF_DAY, time.hour)
    now.set(Calendar.MINUTE, time.minute)

    return (now.timeInMillis - nowTimeInMillis) / SECOND_IN_MS
}