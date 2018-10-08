package husaynhakeem.io.unconnectifyre.data.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
data class Alarm(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        @ColumnInfo(name = "start_time") val startTime: Time,
        @ColumnInfo(name = "end_time") val endTime: Time,
        val connectivities: List<Int>,
        val days: List<Int>)

@Entity
data class Time(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val hour: Int,
        val minute: Int)
