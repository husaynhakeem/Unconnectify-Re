package husaynhakeem.io.unconnectifyre.worker

import android.util.Log
import androidx.work.Worker
import husaynhakeem.io.unconnectifyre.connectivitymodels.*
import husaynhakeem.io.unconnectifyre.data.Connectivity
import husaynhakeem.io.unconnectifyre.data.connectivityFrom
import husaynhakeem.io.unconnectifyre.utilities.CONNECTIVITY_KEY
import husaynhakeem.io.unconnectifyre.utilities.SHOULD_CONNECT_KEY
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class ToggleConnectivityWorker : Worker(), KoinComponent {

    private val bluetoothController: BluetoothController by inject()
    private val cellularDataController: CellularDataController by inject()
    private val hotspotController: HotspotController by inject()
    private val wifiController: WifiController by inject()

    override fun doWork(): WorkerResult {
        return try {
            Log.d(TAG, "ToggleConnectivityWorker.doWork(): Begin")
            val connectivity = connectivityFrom(inputData.getInt(CONNECTIVITY_KEY, 0))
            val shouldConnect = inputData.getBoolean(SHOULD_CONNECT_KEY, true)
            mapConnectivityToJob(connectivity, shouldConnect).invoke()
            Log.d(TAG, "ToggleConnectivityWorker.doWork(): End")
            WorkerResult.SUCCESS
        } catch (exception: Exception) {
            Log.e(TAG, "ToggleConnectivityWorker.doWork(): $exception")
            WorkerResult.FAILURE
        }
    }

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

    companion object {
        private val TAG = ToggleConnectivityWorker::class.java.simpleName
    }
}