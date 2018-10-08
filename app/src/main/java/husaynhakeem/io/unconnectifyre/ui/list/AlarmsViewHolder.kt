package husaynhakeem.io.unconnectifyre.ui.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import husaynhakeem.io.unconnectifyre.data.Connectivity
import husaynhakeem.io.unconnectifyre.data.Day
import husaynhakeem.io.unconnectifyre.data.database.Alarm
import kotlinx.android.synthetic.main.item_alarm.view.*


class AlarmsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(alarm: Alarm) {
        itemView.itemAlarmDisconnectsAtValiue.text = alarm.startTime.toString()
        itemView.itemAlarmReconnectsAtValiue.text = alarm.endTime.toString()

        itemView.itemAlarmWifi.enable(alarm.connectivities.contains(Connectivity.WIFI.value))
        itemView.itemAlarmBluetooth.enable(alarm.connectivities.contains(Connectivity.BLUETOOTH.value))
        itemView.itemAlarmHotspot.enable(alarm.connectivities.contains(Connectivity.HOTSPOT.value))

        itemView.itemAlarmSunday.isEnabled = alarm.days.contains(Day.SUNDAY.value)
        itemView.itemAlarmMonday.isEnabled = alarm.days.contains(Day.MONDAY.value)
        itemView.itemAlarmTuesday.isEnabled = alarm.days.contains(Day.TUESDAY.value)
        itemView.itemAlarmWednesday.isEnabled = alarm.days.contains(Day.WEDNESDAY.value)
        itemView.itemAlarmThursday.isEnabled = alarm.days.contains(Day.THURSDAY.value)
        itemView.itemAlarmFriday.isEnabled = alarm.days.contains(Day.FRIDAY.value)
        itemView.itemAlarmSaturday.isEnabled = alarm.days.contains(Day.SATURDAY.value)
    }

    private fun ImageView.enable(isEnabled: Boolean) {
        alpha = if (isEnabled) 1F else 0.1F
    }
}