package husaynhakeem.io.unconnectifyre.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
class Alarm(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "start_time") val startTime: Time,
        @ColumnInfo(name = "end_time") val endTime: Time,
        val connectivities: List<Int>,
        val days: List<Int>)

@Entity
class Time(
        val start: Int,
        val end: Int,
        @PrimaryKey val id: Int = 0)
