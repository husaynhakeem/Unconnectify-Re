package husaynhakeem.io.unconnectifyre.ui.dialog

import android.annotation.SuppressLint
import android.widget.TextView
import husaynhakeem.io.unconnectifyre.R
import husaynhakeem.io.unconnectifyre.data.Connectivity
import husaynhakeem.io.unconnectifyre.data.Day
import husaynhakeem.io.unconnectifyre.data.database.Alarm
import husaynhakeem.io.unconnectifyre.utilities.formatTimeForDisplay
import husaynhakeem.io.unconnectifyre.utilities.toggleBackground
import kotlinx.android.synthetic.main.layout_alarm_form.*


class UpdateAlarmDialog : AlarmFormDialog() {

    private lateinit var alarm: Alarm

    @SuppressLint("VisibleForTests")
    override fun setupView() {
        alarmFormStartTimeValue.text = formatTimeForDisplay(context!!, alarm.startTime.hour, alarm.startTime.minute)
        alarmFormEndTimeValue.text = formatTimeForDisplay(context!!, alarm.endTime.hour, alarm.endTime.minute)

        alarmFormWifi.isActivated = alarm.connectivities.contains(Connectivity.WIFI.value)
        alarmFormBluetooth.isActivated = alarm.connectivities.contains(Connectivity.BLUETOOTH.value)
        alarmFormHotspot.isActivated = alarm.connectivities.contains(Connectivity.HOTSPOT.value)

        alarmFormSunday.setActivatedAndUpdateBackground(alarm.days.contains(Day.SUNDAY.value))
        alarmFormMonday.setActivatedAndUpdateBackground(alarm.days.contains(Day.MONDAY.value))
        alarmFormTuesday.setActivatedAndUpdateBackground(alarm.days.contains(Day.TUESDAY.value))
        alarmFormWednesday.setActivatedAndUpdateBackground(alarm.days.contains(Day.WEDNESDAY.value))
        alarmFormThursday.setActivatedAndUpdateBackground(alarm.days.contains(Day.THURSDAY.value))
        alarmFormFriday.setActivatedAndUpdateBackground(alarm.days.contains(Day.FRIDAY.value))
        alarmFormSaturday.setActivatedAndUpdateBackground(alarm.days.contains(Day.SATURDAY.value))

        alarmFormPositiveButton.apply {
            setText(R.string.update_alarm_button)
            setOnClickListener {
                val newAlarm = buildAlarm()
                newAlarm.id = alarm.id
                newAlarm.startTime.id = alarm.startTime.id
                newAlarm.endTime.id = alarm.endTime.id

                if (alarm != newAlarm) {
                    listener.updateAlarm(newAlarm)
                }
                dismiss()
            }
        }
        alarmFormNegativeButton.setOnClickListener {
            listener.deleteAlarm(alarm.id)
            dismiss()
        }
    }

    private fun TextView.setActivatedAndUpdateBackground(isActivated: Boolean) {
        this.isActivated = isActivated
        this.toggleBackground(isActivated)
    }

    companion object {
        val TAG = UpdateAlarmDialog::class.java.simpleName
        fun newInstance(listener: Listener, alarm: Alarm): UpdateAlarmDialog {
            val fragment = UpdateAlarmDialog()
            fragment.listener = listener
            fragment.alarm = alarm
            return fragment
        }
    }
}