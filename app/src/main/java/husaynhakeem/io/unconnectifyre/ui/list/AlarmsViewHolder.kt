package husaynhakeem.io.unconnectifyre.ui.list

import android.support.v7.widget.RecyclerView
import android.view.View
import husaynhakeem.io.unconnectifyre.data.Connectivity
import husaynhakeem.io.unconnectifyre.data.Day
import husaynhakeem.io.unconnectifyre.data.database.Alarm
import husaynhakeem.io.unconnectifyre.utilities.toggleBackground
import kotlinx.android.synthetic.main.item_alarm.view.*


class AlarmsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(alarm: Alarm) {
        itemView.itemAlarmStartTimeValue.text = alarm.startTime.toString()
        itemView.itemAlarmEndTimeValue.text = alarm.endTime.toString()

        itemView.itemAlarmWifi.isActivated = alarm.connectivities.contains(Connectivity.WIFI.value)
        itemView.itemAlarmBluetooth.isActivated = alarm.connectivities.contains(Connectivity.BLUETOOTH.value)
        itemView.itemAlarmHotspot.isActivated = alarm.connectivities.contains(Connectivity.HOTSPOT.value)

        itemView.itemAlarmSunday.toggleBackground(alarm.days.contains(Day.SUNDAY.value))
        itemView.itemAlarmMonday.toggleBackground(alarm.days.contains(Day.MONDAY.value))
        itemView.itemAlarmTuesday.toggleBackground(alarm.days.contains(Day.TUESDAY.value))
        itemView.itemAlarmWednesday.toggleBackground(alarm.days.contains(Day.WEDNESDAY.value))
        itemView.itemAlarmThursday.toggleBackground(alarm.days.contains(Day.THURSDAY.value))
        itemView.itemAlarmFriday.toggleBackground(alarm.days.contains(Day.FRIDAY.value))
        itemView.itemAlarmSaturday.toggleBackground(alarm.days.contains(Day.SATURDAY.value))
    }

    fun setOnClickListener(block: () -> Unit) {
        itemView.itemAlarmContainer.setOnClickListener { block.invoke() }
    }
}