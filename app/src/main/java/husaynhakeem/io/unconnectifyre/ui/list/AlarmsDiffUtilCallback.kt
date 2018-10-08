package husaynhakeem.io.unconnectifyre.ui.list

import android.support.v7.util.DiffUtil
import husaynhakeem.io.unconnectifyre.data.database.Alarm


class AlarmsDiffUtilCallback(
        private val oldItems: List<Alarm>,
        private val newItems: List<Alarm>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldItems[oldItemPosition].id == newItems[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldItems[oldItemPosition] == newItems[newItemPosition]
}