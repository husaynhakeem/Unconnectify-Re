package husaynhakeem.io.unconnectifyre.data


enum class Connectivity(val value: Int) {
    WIFI(1),
    BLUETOOTH(2),
    HOTSPOT(3),
    CELLULAR_DATA(4)
}

fun connectivityFrom(value: Int): Connectivity = when (value) {
    1 -> Connectivity.WIFI
    2 -> Connectivity.BLUETOOTH
    3 -> Connectivity.HOTSPOT
    4 -> Connectivity.CELLULAR_DATA
    else -> throw IllegalArgumentException("connectivityFrom($value): Invalid input")
}

enum class Day(val value: Int) {
    SUNDAY(0),
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
}

fun dayFrom(value: Int): Day = when (value) {
    0 -> Day.SUNDAY
    1 -> Day.MONDAY
    2 -> Day.TUESDAY
    3 -> Day.WEDNESDAY
    4 -> Day.THURSDAY
    5 -> Day.FRIDAY
    6 -> Day.SATURDAY
    else -> throw IllegalArgumentException("dayFrom($value): Invalid input")
}