package husaynhakeem.io.unconnectifyre.ui.list

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import husaynhakeem.io.unconnectifyre.R
import husaynhakeem.io.unconnectifyre.ui.createalarm.CreateAlarmFragment
import kotlinx.android.synthetic.main.fragment_alarms.*
import org.koin.android.viewmodel.ext.android.viewModel


class AlarmsFragment : Fragment() {

    private val viewModel: AlarmsViewModel by viewModel()
    private val adapter: AlarmsAdapter = AlarmsAdapter(mutableListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_alarms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        alarmsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@AlarmsFragment.adapter
            setHasFixedSize(true)
        }
        alarmsCreateButton.setOnClickListener {
            navigateToCreateAlarmScreen()
        }
    }

    private fun setupViewModel() {
        viewModel.alarms.observe(this, Observer { alarms ->
            alarms?.let {
                if (it.isEmpty()) {
                    alarmsRecyclerView.visibility = View.GONE
                    alarmsEmptyGroup.visibility = View.VISIBLE
                } else {
                    alarmsEmptyGroup.visibility = View.GONE
                    alarmsRecyclerView.visibility = View.VISIBLE
                    adapter.updateAlarms(it)
                }
            }
        })
    }

    private fun navigateToCreateAlarmScreen() {
        activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.mainContent, CreateAlarmFragment())
                ?.commit()
    }
}