package husaynhakeem.io.unconnectifyre.ui.dialog

import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import husaynhakeem.io.unconnectifyre.R
import husaynhakeem.io.unconnectifyre.data.Connectivity
import husaynhakeem.io.unconnectifyre.data.Day
import husaynhakeem.io.unconnectifyre.data.database.Alarm
import husaynhakeem.io.unconnectifyre.extensions.addIf
import husaynhakeem.io.unconnectifyre.utilities.formatTimeForDisplay
import husaynhakeem.io.unconnectifyre.utilities.fromDisplayedTimeToTime
import husaynhakeem.io.unconnectifyre.utilities.toggleActivation
import husaynhakeem.io.unconnectifyre.utilities.toggleBackground
import kotlinx.android.synthetic.main.layout_alarm_form.*


abstract class AlarmFormDialog : DialogFragment() {

    protected lateinit var listener: Listener

    override fun getTheme(): Int {
        return R.style.AlarmFormDialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_alarm_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupListeners()
    }

    abstract fun setupView()

    private fun setupListeners() {
        alarmFormStartTimeValue.setOnClickListener {
            TimePickerDialog(context,
                    TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        alarmFormStartTimeValue.text = formatTimeForDisplay(context!!, hour, minute)
                    },
                    0,
                    0,
                    true).show()
        }
        alarmFormEndTimeValue.setOnClickListener {
            TimePickerDialog(context,
                    TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        alarmFormEndTimeValue.text = formatTimeForDisplay(context!!, hour, minute)
                    },
                    0,
                    0,
                    true)
                    .show()
        }

        alarmFormWifi.setOnClickListener { alarmFormWifi.toggleActivation() }
        alarmFormBluetooth.setOnClickListener { alarmFormBluetooth.toggleActivation() }
        alarmFormHotspot.setOnClickListener { alarmFormHotspot.toggleActivation() }

        alarmFormSunday.setOnClickListener { alarmFormSunday.toggleActivationAndUpdateBackground() }
        alarmFormMonday.setOnClickListener { alarmFormMonday.toggleActivationAndUpdateBackground() }
        alarmFormTuesday.setOnClickListener { alarmFormTuesday.toggleActivationAndUpdateBackground() }
        alarmFormWednesday.setOnClickListener { alarmFormWednesday.toggleActivationAndUpdateBackground() }
        alarmFormThursday.setOnClickListener { alarmFormThursday.toggleActivationAndUpdateBackground() }
        alarmFormFriday.setOnClickListener { alarmFormFriday.toggleActivationAndUpdateBackground() }
        alarmFormSaturday.setOnClickListener { alarmFormSaturday.toggleActivationAndUpdateBackground() }

        alarmFormNeutralButton.setOnClickListener { dismiss() }
    }

    private fun TextView.toggleActivationAndUpdateBackground() {
        this.toggleActivation()
        this.toggleBackground(this.isActivated)
    }

    protected fun areInputFieldsValid() = isStartTimeValid() && isEndTimeValid() &&
            isAConnectivitySelected() && isADaySelected()

    private fun isStartTimeValid() = alarmFormStartTimeValue.text.toString() != getString(R.string.disconnects_at_empty_value)

    private fun isEndTimeValid() = alarmFormEndTimeValue.text.toString() != getString(R.string.reconnects_at_empty_value)

    private fun isAConnectivitySelected() =
            alarmFormWifi.isEnabled || alarmFormBluetooth.isEnabled || alarmFormHotspot.isEnabled

    private fun isADaySelected() = alarmFormSunday.isEnabled || alarmFormMonday.isEnabled ||
            alarmFormTuesday.isEnabled || alarmFormWednesday.isEnabled || alarmFormThursday.isEnabled ||
            alarmFormFriday.isEnabled || alarmFormSaturday.isEnabled

    protected fun buildAlarm(): Alarm {
        val startTime = fromDisplayedTimeToTime(alarmFormStartTimeValue.text.toString())
        val endTime = fromDisplayedTimeToTime(alarmFormEndTimeValue.text.toString())
        val connectivities = mutableListOf<Int>().apply {
            addIf(Connectivity.WIFI.value, { alarmFormWifi.isActivated })
            addIf(Connectivity.BLUETOOTH.value, { alarmFormBluetooth.isActivated })
            addIf(Connectivity.HOTSPOT.value, { alarmFormHotspot.isActivated })
        }
        val days = mutableListOf<Int>().apply {
            addIf(Day.SUNDAY.value, { alarmFormSunday.isActivated })
            addIf(Day.MONDAY.value, { alarmFormMonday.isActivated })
            addIf(Day.TUESDAY.value, { alarmFormTuesday.isActivated })
            addIf(Day.WEDNESDAY.value, { alarmFormWednesday.isActivated })
            addIf(Day.THURSDAY.value, { alarmFormThursday.isActivated })
            addIf(Day.FRIDAY.value, { alarmFormFriday.isActivated })
            addIf(Day.SATURDAY.value, { alarmFormSaturday.isActivated })
        }
        return Alarm(startTime = startTime, endTime = endTime, connectivities = connectivities, days = days)
    }

    protected fun displayInputErrorMessage() {
        val context = context ?: return
        Toast.makeText(context, R.string.create_alarm_error, Toast.LENGTH_SHORT).show()
    }

    interface Listener {
        fun createAlarm(alarm: Alarm)
        fun updateAlarm(alarm: Alarm)
        fun deleteAlarm(alarmId: Int)
    }
}