package husaynhakeem.io.unconnectifyre.connectivitymodels

import android.content.Context
import android.net.wifi.WifiManager

import cc.mvdan.accesspoint.WifiApControl


class HotspotController(context: Context) : ConnectivityController {

    private val wifiManager: WifiManager by lazy {
        context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    private val apControl: WifiApControl by lazy {
        WifiApControl.getInstance(context)
    }

    /**
     * Enables the default hotspot, which requires the wifi to be turned off
     */
    override fun enable() {
        if (wifiManager.isWifiEnabled) {
            wifiManager.isWifiEnabled = false
        }
        apControl.enable()
    }

    override fun disable() {
        apControl.disable()
    }
}
