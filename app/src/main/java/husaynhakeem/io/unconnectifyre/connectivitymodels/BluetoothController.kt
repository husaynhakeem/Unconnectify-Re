package husaynhakeem.io.unconnectifyre.connectivitymodels

import android.bluetooth.BluetoothAdapter


class BluetoothController : ConnectivityController {

    private val bluetoothAdapter: BluetoothAdapter by lazy {
        BluetoothAdapter.getDefaultAdapter()
    }

    override fun enable() {
        if (!bluetoothAdapter.isEnabled) {
            bluetoothAdapter.enable()
        }
    }

    override fun disable() {
        if (bluetoothAdapter.isEnabled) {
            bluetoothAdapter.disable()
        }
    }
}
