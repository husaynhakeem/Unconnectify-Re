package husaynhakeem.io.unconnectifyre.worker

import android.util.Log
import androidx.work.Worker
import husaynhakeem.io.unconnectifyre.utilities.JOB_KEY


class ToggleConnectivityWorker : Worker() {

    @Suppress("unchecked_cast")
    override fun doWork(): WorkerResult {
        return try {
            val job = inputData.keyValueMap[JOB_KEY] as (() -> Unit)
            job.invoke()
            WorkerResult.SUCCESS
        } catch (exception: Exception) {
            Log.e(TAG, "ToggleConnectivityWorker.doWork(): $exception")
            WorkerResult.FAILURE
        }
    }

    companion object {
        private val TAG = ToggleConnectivityWorker::class.java.simpleName
    }
}