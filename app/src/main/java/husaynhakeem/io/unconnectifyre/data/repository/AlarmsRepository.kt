package husaynhakeem.io.unconnectifyre.data.repository

import android.arch.lifecycle.LiveData
import husaynhakeem.io.unconnectifyre.data.database.Alarm
import husaynhakeem.io.unconnectifyre.data.database.AlarmDao


class AlarmsRepository(val alarmDao: AlarmDao) {

    fun createAlarm(alarm: Alarm) {
        alarmDao.createAlarm(alarm)
    }

    fun updateAlarm(alarm: Alarm) {
        alarmDao.updateAlarm(alarm)
    }

    fun deleteAlarm(alarmId: Int) {
        alarmDao.deleteAlarm(alarmId)
    }

    fun getAllAlarms(): LiveData<List<Alarm>> = alarmDao.getAllAlarms()

    fun findAlarmById(alarmId: Int): LiveData<Alarm> = alarmDao.findAlarmById(alarmId)
}