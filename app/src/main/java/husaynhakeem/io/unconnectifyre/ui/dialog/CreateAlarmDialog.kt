package husaynhakeem.io.unconnectifyre.ui.dialog

import android.annotation.SuppressLint
import android.view.View
import husaynhakeem.io.unconnectifyre.R
import kotlinx.android.synthetic.main.layout_alarm_form.*


class CreateAlarmDialog : AlarmFormDialog() {

    @SuppressLint("VisibleForTests")
    override fun setupView() {
        alarmFormPositiveButton.apply {
            setText(R.string.create_alarm_button)
            setOnClickListener {
                if (areInputFieldsValid()) {
                    listener.createAlarm(buildAlarm())
                    dismiss()
                } else {
                    displayInputErrorMessage()
                }
            }
        }
        alarmFormNegativeButton.apply {
            setOnClickListener(null)
            visibility = View.GONE
        }
    }

    companion object {
        val TAG = CreateAlarmDialog::class.java.simpleName
        fun newInstance(listener: Listener): CreateAlarmDialog {
            val fragment = CreateAlarmDialog()
            fragment.listener = listener
            return fragment
        }
    }
}