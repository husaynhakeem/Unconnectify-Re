package husaynhakeem.io.unconnectifyre.data.converter

import android.arch.persistence.room.TypeConverter
import husaynhakeem.io.unconnectifyre.utilities.COMMA


class IntegerListConverter {

    @TypeConverter
    fun fromIntegerListToString(values: List<Int>): String = with(StringBuilder()) {
        values.forEachIndexed { index, value ->
            append(value)
            if (index < values.size - 1) {
                append(COMMA)
            }
        }
        toString()
    }

    @TypeConverter
    fun fromStringToIntegerList(s: String): List<Int> {
        if (s.isBlank()) {
            return emptyList()
        }
        return s.split(COMMA)
                .map { it.toInt() }
                .toList()
    }
}