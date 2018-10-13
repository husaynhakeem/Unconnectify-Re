package husaynhakeem.io.unconnectifyre.worker

import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import husaynhakeem.io.unconnectifyre.data.database.Alarm
import husaynhakeem.io.unconnectifyre.data.dayFrom
import husaynhakeem.io.unconnectifyre.utilities.CONNECTIVITY_KEY
import husaynhakeem.io.unconnectifyre.utilities.SHOULD_CONNECT_KEY
import husaynhakeem.io.unconnectifyre.utilities.computeTimeUntil
import husaynhakeem.io.unconnectifyre.utilities.runOnIoThread
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
            OneTimeWorkRequest.Builder(ToggleConnectivityWorker::class.java)
                    .addTag(it.id.toString())
                    .setInitialDelay(it.delay, TimeUnit.SECONDS)
                    .setInputData(buildInputData(it.connectivity, it.shouldConnect))
                    .build()
        }
    }

    private fun buildInputData(connectivity: Int, shouldConnect: Boolean) =
            Data.Builder()
                    .putInt(CONNECTIVITY_KEY, connectivity)
                    .putBoolean(SHOULD_CONNECT_KEY, shouldConnect)
                    .build()
}