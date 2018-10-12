package husaynhakeem.io.unconnectifyre.ui.dialog

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.ImageView
import com.schibsted.spain.barista.assertion.BaristaAssertions.assertAny
import com.schibsted.spain.barista.assertion.BaristaBackgroundAssertions.assertHasBackground
import com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertHasDrawable
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import husaynhakeem.io.unconnectifyre.R
import husaynhakeem.io.unconnectifyre.TestActivity
import husaynhakeem.io.unconnectifyre.data.Connectivity
import husaynhakeem.io.unconnectifyre.data.Day
import husaynhakeem.io.unconnectifyre.data.database.Alarm
import husaynhakeem.io.unconnectifyre.data.database.Time
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UpdateAlarmDialogShould {

    @get:Rule
    val rule = ActivityTestRule(TestActivity::class.java)

    private val listener = object : AlarmFormDialog.Listener {
        override fun createAlarm(alarm: Alarm) {}
        override fun updateAlarm(alarm: Alarm) {}
        override fun deleteAlarm(alarmId: Int) {}
    }
    private val alarm = Alarm(
            id = 1,
            startTime = Time(id = 2, hour = 10, minute = 0),
            endTime = Time(id = 3, hour = 10, minute = 30),
            connectivities = listOf(Connectivity.WIFI.value),
            days = listOf(Day.SATURDAY.value, Day.SUNDAY.value))
    private val dialog = UpdateAlarmDialog.newInstance(listener, alarm)

    @Before
    fun setUp() {
        dialog.show(rule.activity.supportFragmentManager, UpdateAlarmDialog::class.java.simpleName)
    }

    @Test
    fun displayAlarmToUpdate() {
        assertDisplayed(R.id.alarmFormStartTimeValue, alarm.startTime.toString())
        assertDisplayed(R.id.alarmFormEndTimeValue, alarm.endTime.toString())

        assertAny<ImageView>(R.id.alarmFormWifi) { it.isActivated }
        assertAny<ImageView>(R.id.alarmFormBluetooth) { !it.isActivated }
        assertAny<ImageView>(R.id.alarmFormHotspot) { !it.isActivated }

        assertHasDrawable(R.id.alarmFormWifi, R.drawable.ic_wifi_activated)
        assertHasDrawable(R.id.alarmFormBluetooth, R.drawable.ic_bluetooth_deactivated)
        assertHasDrawable(R.id.alarmFormHotspot, R.drawable.ic_hotspot_deactivated)

        assertHasBackground(R.id.alarmFormSaturday, R.drawable.background_activated)
        assertHasBackground(R.id.alarmFormSunday, R.drawable.background_activated)
    }

    @Test
    fun dismissDialog_whenPositiveButtonIsClicked() {
        clickOn(R.id.alarmFormPositiveButton)

        assertNotExist(R.string.disconnects_at_label)
    }

    @Test
    fun dismissDialog_whenNeutralButtonIsClicked() {
        clickOn(R.id.alarmFormNeutralButton)

        assertNotExist(R.string.disconnects_at_label)
    }

    @Test
    fun dismissDialog_whenNegativeButtonIsClicked() {
        clickOn(R.id.alarmFormNegativeButton)

        assertNotExist(R.string.disconnects_at_label)
    }
}