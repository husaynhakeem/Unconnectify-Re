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
                        connectivities = listOf(Connectivity.WIFI.value),
                        days = listOf(Day.SUNDAY.value))
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