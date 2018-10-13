package husaynhakeem.io.unconnectifyre.connectivitymodels

import android.content.Context
import android.telephony.TelephonyManager
import android.util.Log


class CellularDataController(context: Context) : ConnectivityController {

    private val telephonyManager: TelephonyManager by lazy {
        context.applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    }

    var mobileDataState: Boolean
        get() {
            try {
                val getMobileDataEnabledMethod = telephonyManager.javaClass.getDeclaredMethod("getDataEnabled")
                if (getMobileDataEnabledMethod != null) {
                    return getMobileDataEnabledMethod.invoke(telephonyManager) as Boolean
                }
            } catch (ex: Exception) {
                Log.e(TAG, "Error getting mobile data state", ex)
            }
            return false
        }
        set(isMobileDataEnabled) {
            try {
                val setMobileDataEnabledMethod = telephonyManager.javaClass.getDeclaredMethod("setDataEnabled", Boolean::class.javaPrimitiveType)
                setMobileDataEnabledMethod?.invoke(telephonyManager, isMobileDataEnabled)
            } catch (ex: Exception) {
                Log.e(TAG, "Error setting mobile data state", ex)
            }
        }

    override fun enable() {}

    override fun disable() {}

    companion object {
        private val TAG = CellularDataController::class.java.simpleName
    }
}
