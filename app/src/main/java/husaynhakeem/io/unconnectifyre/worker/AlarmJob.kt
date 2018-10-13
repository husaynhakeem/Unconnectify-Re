package husaynhakeem.io.unconnectifyre.worker


data class AlarmJob(
        val id: Int,
        val delay: Long,
        val connectivity: Int,
        val shouldConnect: Boolean)