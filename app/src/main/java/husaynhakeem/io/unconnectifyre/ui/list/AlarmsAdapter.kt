package husaynhakeem.io.unconnectifyre.ui.list

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import husaynhakeem.io.unconnectifyre.R
import husaynhakeem.io.unconnectifyre.data.database.Alarm


class AlarmsAdapter(private val listener: Listener, private val alarms: MutableList<Alarm>) : RecyclerView.Adapter<AlarmsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_alarm, parent, false)
        return AlarmsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlarmsViewHolder, position: Int) {
        holder.bind(alarms[position])
        holder.setOnClickListener { listener.onClick(alarms[holder.adapterPosition]) }
    }

    override fun getItemCount() = alarms.size

    fun updateAlarms(alarms: List<Alarm>) {
        val diffResult = DiffUtil.calculateDiff(AlarmsDiffUtilCallback(this.alarms, alarms))
        this.alarms.clear()
        this.alarms.addAll(alarms)
        diffResult.dispatchUpdatesTo(this)
    }

    interface Listener {
        fun onClick(alarm: Alarm)
    }
}