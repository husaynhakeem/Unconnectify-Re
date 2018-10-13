package husaynhakeem.io.unconnectifyre.di

import androidx.work.WorkManager
import husaynhakeem.io.unconnectifyre.connectivitymodels.BluetoothController
import husaynhakeem.io.unconnectifyre.connectivitymodels.CellularDataController
import husaynhakeem.io.unconnectifyre.connectivitymodels.HotspotController
import husaynhakeem.io.unconnectifyre.connectivitymodels.WifiController
import husaynhakeem.io.unconnectifyre.data.database.AlarmDao
import husaynhakeem.io.unconnectifyre.data.database.AlarmDatabase
import husaynhakeem.io.unconnectifyre.data.repository.AlarmsRepository
import husaynhakeem.io.unconnectifyre.ui.list.AlarmsViewModel
import husaynhakeem.io.unconnectifyre.worker.AlarmScheduler
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val appModule = module {
    viewModel { AlarmsViewModel(get()) }
    single<AlarmsRepository> { AlarmsRepository(get()) }
    single<AlarmDao> { AlarmDatabase.get(androidApplication()).alarmDao() }

    single<AlarmScheduler> { AlarmScheduler(get()) }
    single<WorkManager> { WorkManager.getInstance() }

    single<WifiController> { WifiController(androidApplication()) }
    single<CellularDataController> { CellularDataController(androidApplication()) }
    single<HotspotController> { HotspotController(androidApplication()) }
    single<BluetoothController> { BluetoothController() }
}