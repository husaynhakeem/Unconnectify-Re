package husaynhakeem.io.unconnectifyre.data.converter

import android.arch.persistence.room.TypeConverter
import android.util.Log
import husaynhakeem.io.unconnectifyre.data.database.Time
import husaynhakeem.io.unconnectifyre.extensions.TAG


class TimeConverter {

    @TypeConverter
    fun fromTimeToString(time: Time): String {
        return "${time.id}$TIME_SEPARATOR${time.hour}$TIME_SEPARATOR${time.minute}"
    }

    @TypeConverter
    fun fromStringToTime(s: String): Time {
        return try {
            val timeParts = s.split(TIME_SEPARATOR).map { it.toInt() }
            Time(timeParts[0], timeParts[1], timeParts[2])
        } catch (e: Exception) {
            Log.e(TimeConverter.TAG, "TimeConverter.fromStringToTime($s): Conversion error", e)
            Time(0, 0, 0)
        }
    }

    companion object {
        private const val TIME_SEPARATOR = ","
    }
}