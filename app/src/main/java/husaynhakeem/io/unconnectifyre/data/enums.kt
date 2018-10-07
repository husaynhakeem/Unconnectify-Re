package husaynhakeem.io.unconnectifyre.data


enum class Connectivity(val value: Int) {
    WIFI(1),
    BLUETOOTH(2),
    HOTSPOT(3),
    CELLULAR_DATA(4)
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