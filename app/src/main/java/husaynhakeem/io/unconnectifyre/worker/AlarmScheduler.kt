package husaynhakeem.io.unconnectifyre.worker

import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import husaynhakeem.io.unconnectifyre.connectivitymodels.*
import husaynhakeem.io.unconnectifyre.data.Connectivity
import husaynhakeem.io.unconnectifyre.data.connectivityFrom
import husaynhakeem.io.unconnectifyre.data.database.Alarm
import husaynhakeem.io.unconnectifyre.data.dayFrom
import husaynhakeem.io.unconnectifyre.utilities.JOB_KEY
import husaynhakeem.io.unconnectifyre.utilities.computeTimeUntil
import husaynhakeem.io.unconnectifyre.utilities.runOnIoThread
import java.util.concurrent.TimeUnit


class AlarmScheduler(
        private val bluetoothController: BluetoothController,
        private val cellularDataController: CellularDataController,
        private val hotspotController: HotspotController,
        private val wifiController: WifiController,
        private val workManager: WorkManager) {

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
                val conn = connectivityFrom(connectivity)

                val delayUntilDisconnect = computeTimeUntil(dayFrom(day), alarm.startTime)
                add(AlarmJob(alarm.id, delayUntilDisconnect, mapConnectivityToJob(conn, false)))

                val delayUntilReconnect = computeTimeUntil(dayFrom(day), alarm.endTime)
                add(AlarmJob(alarm.id, delayUntilReconnect, mapConnectivityToJob(conn, true)))
            }
        }
    }

    private fun buildWorkRequests(alarmJobs: List<AlarmJob>): List<OneTimeWorkRequest> {
        return alarmJobs.map {
            OneTimeWorkRequest.Builder(ToggleConnectivityWorker::class.java)
                    .addTag(it.id.toString())
                    .setInitialDelay(it.delay, TimeUnit.SECONDS)
                    .setInputData(buildInputData(it.job))
                    .build()
        }
    }

    private fun buildInputData(job: () -> Unit) =
            Data.Builder().putAll(mapOf(Pair(JOB_KEY, job))).build()

    private fun mapConnectivityToJob(connectivity: Connectivity, shouldConnect: Boolean): (() -> Unit) {
        val controller: ConnectivityController = when (connectivity) {
            Connectivity.BLUETOOTH -> bluetoothController
            Connectivity.CELLULAR_DATA -> cellularDataController
            Connectivity.HOTSPOT -> hotspotController
            Connectivity.WIFI -> wifiController
        }

        if (shouldConnect) {
            return { controller.enable() }
        }
        return { controller.disable() }
    }
}