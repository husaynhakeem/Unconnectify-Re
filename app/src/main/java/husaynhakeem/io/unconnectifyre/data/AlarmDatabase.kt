package husaynhakeem.io.unconnectifyre.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import husaynhakeem.io.unconnectifyre.data.converter.IntegerListConverter
import husaynhakeem.io.unconnectifyre.data.converter.TimeConverter


@Database(entities = [Alarm::class, Time::class], version = 1, exportSchema = false)
@TypeConverters(TimeConverter::class, IntegerListConverter::class)
abstract class AlarmDatabase : RoomDatabase() {

    abstract fun alarmDao(): AlarmDao

    companion object {
        private const val DATABASE_NAME = "alarm_database"

        @Volatile
        private var database: AlarmDatabase? = null

        fun get(context: Context): AlarmDatabase {
            if (database == null) {
                synchronized(AlarmDatabase::class.java) {
                    if (database == null) {
                        database = Room.databaseBuilder(
                                context,
                                AlarmDatabase::class.java,
                                DATABASE_NAME)
                                .build()
                    }
                }
            }
            return database!!
        }
    }
}