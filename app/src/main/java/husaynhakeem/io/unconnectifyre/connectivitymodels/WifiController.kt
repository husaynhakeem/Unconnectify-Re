package husaynhakeem.io.unconnectifyre.connectivitymodels

import android.content.Context
import android.net.wifi.WifiManager


class WifiController(context: Context) : ConnectivityController {

    private val wifiManager: WifiManager by lazy {
        context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    override fun enable() {
        if (!wifiManager.isWifiEnabled) {
            wifiManager.isWifiEnabled = true
        }
    }

    override fun disable() {
        if (wifiManager.isWifiEnabled) {
            wifiManager.isWifiEnabled = false
        }
    }
}
