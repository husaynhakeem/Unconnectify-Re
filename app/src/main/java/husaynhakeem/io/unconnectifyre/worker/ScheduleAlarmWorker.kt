package husaynhakeem.io.unconnectifyre.worker

import android.util.Log
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import husaynhakeem.io.unconnectifyre.utilities.ALARM_ID_KEY
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.concurrent.TimeUnit


class ScheduleAlarmWorker : Worker(), KoinComponent {

    private val workManager: WorkManager by inject()

    override fun doWork(): WorkerResult {
        return try {
            Log.d(TAG, "ScheduleAlarmWorker.doWork(): Begin")

            val alarmId = inputData.getInt(ALARM_ID_KEY, 0)
            val request = PeriodicWorkRequest.Builder(ToggleConnectivityWorker::class.java, 7, TimeUnit.DAYS)
                    .addTag(alarmId.toString())
                    .setInputData(inputData)
                    .build()
            workManager.enqueue(request)

            Log.d(TAG, "ScheduleAlarmWorker.doWork(): End")
            WorkerResult.SUCCESS
        } catch (exception: Exception) {
            Log.e(TAG, "ScheduleAlarmWorker.doWork(): $exception")
            WorkerResult.FAILURE
        }
    }

    companion object {
        private val TAG = ScheduleAlarmWorker::class.java.simpleName
    }
}