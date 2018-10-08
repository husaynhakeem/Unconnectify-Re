package husaynhakeem.io.unconnectifyre.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update


@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarm")
    fun getAllAlarms(): LiveData<List<Alarm>>

    @Query("SELECT * FROM alarm WHERE id=:id")
    fun findAlarmById(id: Int): LiveData<Alarm>

    @Insert
    fun createAlarm(alarms: Alarm)

    @Update
    fun updateAlarm(alarm: Alarm)

    @Query("DELETE FROM alarm WHERE id=:id")
    fun deleteAlarm(id: Int)
}