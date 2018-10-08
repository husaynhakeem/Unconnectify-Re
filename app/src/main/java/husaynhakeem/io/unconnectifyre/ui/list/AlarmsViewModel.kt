package husaynhakeem.io.unconnectifyre.ui.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import husaynhakeem.io.unconnectifyre.data.database.Alarm
import husaynhakeem.io.unconnectifyre.data.repository.AlarmsRepository


class AlarmsViewModel(private val alarmsRepository: AlarmsRepository) : ViewModel() {

    val alarms: LiveData<List<Alarm>> = alarmsRepository.getAllAlarms()
}