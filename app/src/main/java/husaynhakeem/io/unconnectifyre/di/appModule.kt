package husaynhakeem.io.unconnectifyre.di

import husaynhakeem.io.unconnectifyre.data.database.AlarmDao
import husaynhakeem.io.unconnectifyre.data.database.AlarmDatabase
import husaynhakeem.io.unconnectifyre.data.repository.AlarmsRepository
import husaynhakeem.io.unconnectifyre.ui.list.AlarmsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val appModule = module {
    viewModel { AlarmsViewModel(get()) }
    single<AlarmsRepository> { AlarmsRepository(get()) }
    single<AlarmDao> { AlarmDatabase.get(androidApplication()).alarmDao() }
}