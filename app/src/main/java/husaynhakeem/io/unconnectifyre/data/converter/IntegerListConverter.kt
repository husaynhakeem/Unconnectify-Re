package husaynhakeem.io.unconnectifyre.data.converter

import android.arch.persistence.room.TypeConverter


class IntegerListConverter {

    @TypeConverter
    fun fromIntegerListToString(values: List<Int>): String = with(StringBuilder()) {
        values.forEachIndexed { index, value ->
            append(value)
            if (index < values.size - 1) {
                append(INTEGERS_SEPARATOR)
            }
        }
        toString()
    }

    @TypeConverter
    fun fromStringToIntegerList(s: String): List<Int> {
        if (s.isBlank()) {
            return emptyList()
        }
        return s.split(INTEGERS_SEPARATOR)
                .map { it.toInt() }
                .toList()
    }

    companion object {
        private const val INTEGERS_SEPARATOR = ","
    }
}