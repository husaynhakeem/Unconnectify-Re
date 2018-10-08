package husaynhakeem.io.unconnectifyre.ui.createalarm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import husaynhakeem.io.unconnectifyre.R


class CreateAlarmFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_alarm, container, false)
    }
}