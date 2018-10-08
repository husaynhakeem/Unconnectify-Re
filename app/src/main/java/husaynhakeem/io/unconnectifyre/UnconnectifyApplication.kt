package husaynhakeem.io.unconnectifyre

import android.app.Application
import husaynhakeem.io.unconnectifyre.di.appModule
import org.koin.android.ext.android.startKoin


class UnconnectifyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin(this, listOf(appModule))
    }
}