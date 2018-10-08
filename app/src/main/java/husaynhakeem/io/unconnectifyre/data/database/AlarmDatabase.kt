package husaynhakeem.io.unconnectifyre.data.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import husaynhakeem.io.unconnectifyre.data.Connectivity
import husaynhakeem.io.unconnectifyre.data.Day
import husaynhakeem.io.unconnectifyre.data.converter.IntegerListConverter
import husaynhakeem.io.unconnectifyre.data.converter.TimeConverter
import java.util.concurrent.Executors


@Database(entities = [Alarm::class, Time::class], version = 1, exportSchema = false)
@TypeConverters(TimeConverter::class, IntegerListConverter::class)
abstract class AlarmDatabase : RoomDatabase() {

    abstract fun alarmDao(): AlarmDao

    companion object {
        private const val DATABASE_NAME = "alarm_database"

        private val dummyAlarms = arrayOf(
                Alarm(startTime = Time(hour = 10, minute = 0),
                        endTime = Time(hour = 10, minute = 45),
                        connectivities = listOf(Connectivity.WIFI.value, Connectivity.HOTSPOT.value),
                        days = listOf(Day.SUNDAY.value)),
                Alarm(startTime = Time(hour = 11, minute = 0),
                        endTime = Time(hour = 11, minute = 45),
                        connectivities = listOf(Connectivity.BLUETOOTH.value, Connectivity.HOTSPOT.value),
                        days = listOf(Day.MONDAY.value)),
                Alarm(startTime = Time(hour = 12, minute = 0),
                        endTime = Time(hour = 12, minute = 45),
                        connectivities = listOf(Connectivity.WIFI.value),
                        days = listOf(Day.TUESDAY.value)),
                Alarm(startTime = Time(hour = 13, minute = 0),
                        endTime = Time(hour = 13, minute = 45),
                        connectivities = listOf(Connectivity.HOTSPOT.value),
                        days = listOf(Day.WEDNESDAY.value)),
                Alarm(startTime = Time(hour = 14, minute = 0),
                        endTime = Time(hour = 14, minute = 45),
                        connectivities = listOf(Connectivity.BLUETOOTH.value),
                        days = listOf(Day.THURSDAY.value)),
                Alarm(startTime = Time(hour = 15, minute = 0),
                        endTime = Time(hour = 15, minute = 45),
                        connectivities = listOf(Connectivity.WIFI.value, Connectivity.HOTSPOT.value),
                        days = listOf(Day.FRIDAY.value)),
                Alarm(startTime = Time(hour = 16, minute = 0),
                        endTime = Time(hour = 16, minute = 45),
                        connectivities = listOf(Connectivity.WIFI.value),
                        days = listOf(Day.MONDAY.value, Day.TUESDAY.value, Day.WEDNESDAY.value, Day.THURSDAY.value, Day.FRIDAY.value))
        )

        @Volatile
        private var database: AlarmDatabase? = null

        fun get(context: Context): AlarmDatabase {
            if (database == null) {
                synchronized(AlarmDatabase::class.java) {
                    if (database == null) {
                        database = Room.databaseBuilder(context, AlarmDatabase::class.java, DATABASE_NAME)
                                .addCallback(object : Callback() {
                                    override fun onCreate(db: SupportSQLiteDatabase) {
                                        super.onCreate(db)
                                        Executors.newSingleThreadExecutor()
                                                .execute {
                                                    dummyAlarms.forEach { get(context).alarmDao().createAlarm(it) }
                                                }
                                    }
                                })
                                .build()
                    }
                }
            }
            return database!!
        }
    }
}