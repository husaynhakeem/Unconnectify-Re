package husaynhakeem.io.unconnectifyre.worker

import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import husaynhakeem.io.unconnectifyre.data.database.Alarm
import husaynhakeem.io.unconnectifyre.data.dayFrom
import husaynhakeem.io.unconnectifyre.utilities.*
import java.util.concurrent.TimeUnit


class AlarmScheduler(private val workManager: WorkManager) {

    fun schedule(alarm: Alarm) {
        runOnIoThread {
            val jobRequests = buildWorkRequests(buildAlarmJobs(alarm))
            workManager.enqueue(jobRequests)
        }
    }

    fun update(alarm: Alarm) {
        cancel(alarm.id)
        schedule(alarm)
    }

    fun cancel(alarmId: Int) {
        workManager.cancelAllWorkByTag(alarmId.toString())
    }

    private fun buildAlarmJobs(alarm: Alarm): List<AlarmJob> = mutableListOf<AlarmJob>().apply {
        for (day in alarm.days) {
            for (connectivity in alarm.connectivities) {
                val delayUntilDisconnect = computeTimeUntil(dayFrom(day), alarm.startTime)
                add(AlarmJob(alarm.id, delayUntilDisconnect, connectivity, false))

                val delayUntilReconnect = computeTimeUntil(dayFrom(day), alarm.endTime)
                add(AlarmJob(alarm.id, delayUntilReconnect, connectivity, true))
            }
        }
    }

    private fun buildWorkRequests(alarmJobs: List<AlarmJob>): List<OneTimeWorkRequest> {
        return alarmJobs.map {
            OneTimeWorkRequest.Builder(ScheduleAlarmWorker::class.java)
                    .addTag(it.id.toString())
                    .setInitialDelay(it.delay, TimeUnit.SECONDS)
                    .setInputData(buildInputData(it))
                    .build()
        }
    }

    private fun buildInputData(alarmJob: AlarmJob) =
            Data.Builder()
                    .putInt(ALARM_ID_KEY, alarmJob.id)
                    .putInt(CONNECTIVITY_KEY, alarmJob.connectivity)
                    .putBoolean(SHOULD_CONNECT_KEY, alarmJob.shouldConnect)
                    .build()
}