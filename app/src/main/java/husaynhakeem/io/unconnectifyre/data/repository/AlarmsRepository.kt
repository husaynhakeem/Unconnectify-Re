package husaynhakeem.io.unconnectifyre.data.repository

import android.arch.lifecycle.LiveData
import husaynhakeem.io.unconnectifyre.data.database.Alarm
import husaynhakeem.io.unconnectifyre.data.database.AlarmDao
import husaynhakeem.io.unconnectifyre.utilities.runOnIoThread


class AlarmsRepository(private val alarmDao: AlarmDao) {

    fun createAlarm(alarm: Alarm) {
        runOnIoThread {
            alarmDao.createAlarm(alarm)
        }
    }

    fun updateAlarm(alarm: Alarm) {
        runOnIoThread {
            alarmDao.updateAlarm(alarm)
        }
    }

    fun deleteAlarm(alarmId: Int) {
        runOnIoThread {
            alarmDao.deleteAlarm(alarmId)
        }
    }

    fun getAllAlarms(): LiveData<List<Alarm>> = alarmDao.getAllAlarms()

    fun findAlarmById(alarmId: Int): LiveData<Alarm> = alarmDao.findAlarmById(alarmId)
}