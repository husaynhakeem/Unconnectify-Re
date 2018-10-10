package husaynhakeem.io.unconnectifyre.ui.dialog

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.ImageButton
import android.widget.TextView
import com.schibsted.spain.barista.assertion.BaristaAssertions.assertAny
import com.schibsted.spain.barista.assertion.BaristaBackgroundAssertions.assertHasBackground
import com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertHasDrawable
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertTextColorIs
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickBack
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaPickerInteractions.setTimeOnPicker
import husaynhakeem.io.unconnectifyre.R
import husaynhakeem.io.unconnectifyre.TestActivity
import husaynhakeem.io.unconnectifyre.assertToastIsDisplayedWithMessage
import husaynhakeem.io.unconnectifyre.data.Connectivity
import husaynhakeem.io.unconnectifyre.data.Day
import husaynhakeem.io.unconnectifyre.data.database.Alarm
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CreateAlarmDialogShould {

    @get:Rule
    val rule = ActivityTestRule(TestActivity::class.java)

    private val listener = object : AlarmFormDialog.Listener {
        override fun createAlarm(alarm: Alarm) {

        }

        override fun updateAlarm(alarm: Alarm) {
        }

        override fun deleteAlarm(alarmId: Int) {
        }
    }
    private val dialog = CreateAlarmDialog.newInstance(listener)

    @Before
    fun setUp() {
        val fragmentManager = rule.activity.supportFragmentManager
        dialog.show(fragmentManager, CreateAlarmDialog::class.java.simpleName)
    }

    @Test
    fun initialiseViews() {
        assertDisplayed(R.id.alarmFormStartTimeValue, R.string.disconnects_at_empty_value)
        assertDisplayed(R.id.alarmFormEndTimeValue, R.string.reconnects_at_empty_value)

        assertAny<ImageButton>(R.id.alarmFormWifi) { !it.isActivated }
        assertAny<ImageButton>(R.id.alarmFormBluetooth) { !it.isActivated }
        assertAny<ImageButton>(R.id.alarmFormHotspot) { !it.isActivated }

        assertHasDrawable(R.id.alarmFormWifi, R.drawable.ic_wifi_deactivated)
        assertHasDrawable(R.id.alarmFormBluetooth, R.drawable.ic_bluetooth_deactivated)
        assertHasDrawable(R.id.alarmFormHotspot, R.drawable.ic_hotspot_deactivated)

        assertAny<TextView>(R.id.alarmFormSunday) { !it.isActivated }
        assertAny<TextView>(R.id.alarmFormMonday) { !it.isActivated }
        assertAny<TextView>(R.id.alarmFormTuesday) { !it.isActivated }
        assertAny<TextView>(R.id.alarmFormWednesday) { !it.isActivated }
        assertAny<TextView>(R.id.alarmFormThursday) { !it.isActivated }
        assertAny<TextView>(R.id.alarmFormFriday) { !it.isActivated }
        assertAny<TextView>(R.id.alarmFormSaturday) { !it.isActivated }
    }

    @Test
    fun setStartTime() {
        clickOn(R.id.alarmFormStartTimeValue)

        setTimeOnPicker(10, 0)

        assertDisplayed(R.id.alarmFormStartTimeValue, "10:00")
    }

    @Test
    fun setEndTime() {
        clickOn(R.id.alarmFormEndTimeValue)

        setTimeOnPicker(10, 30)

        assertDisplayed(R.id.alarmFormEndTimeValue, "10:30")
    }

    @Test
    fun selectWifiConnectivity() {
        clickOn(R.id.alarmFormWifi)

        assertAny<ImageButton>(R.id.alarmFormWifi) { it.isActivated }
        assertHasDrawable(R.id.alarmFormWifi, R.drawable.ic_wifi_activated)
    }

    @Test
    fun selectThenUnselectWifiConnectivity() {
        clickOn(R.id.alarmFormWifi)
        clickOn(R.id.alarmFormWifi)

        assertAny<ImageButton>(R.id.alarmFormWifi) { !it.isActivated }
        assertHasDrawable(R.id.alarmFormWifi, R.drawable.ic_wifi_deactivated)
    }

    @Test
    fun selectWifiAndBluetoothConnectivities() {
        clickOn(R.id.alarmFormWifi)
        clickOn(R.id.alarmFormBluetooth)

        assertAny<ImageButton>(R.id.alarmFormWifi) { it.isActivated }
        assertAny<ImageButton>(R.id.alarmFormBluetooth) { it.isActivated }
        assertHasDrawable(R.id.alarmFormWifi, R.drawable.ic_wifi_activated)
        assertHasDrawable(R.id.alarmFormBluetooth, R.drawable.ic_bluetooth_activated)
    }

    @Test
    fun selectAllConnectivities() {
        clickOn(R.id.alarmFormWifi)
        clickOn(R.id.alarmFormBluetooth)
        clickOn(R.id.alarmFormHotspot)

        assertAny<ImageButton>(R.id.alarmFormWifi) { it.isActivated }
        assertAny<ImageButton>(R.id.alarmFormBluetooth) { it.isActivated }
        assertAny<ImageButton>(R.id.alarmFormHotspot) { it.isActivated }
        assertHasDrawable(R.id.alarmFormWifi, R.drawable.ic_wifi_activated)
        assertHasDrawable(R.id.alarmFormBluetooth, R.drawable.ic_bluetooth_activated)
        assertHasDrawable(R.id.alarmFormHotspot, R.drawable.ic_hotspot_activated)
    }

    @Test
    fun selectSunday() {
        clickOn(R.id.alarmFormSunday)

        assertAny<TextView>(R.id.alarmFormSunday) { it.isActivated }
        assertTextColorIs(R.id.alarmFormSunday, android.R.color.white)
        assertHasBackground(R.id.alarmFormSunday, R.drawable.background_activated)
    }

    @Test
    fun selectMultipleDays() {
        clickOn(R.id.alarmFormMonday)
        clickOn(R.id.alarmFormWednesday)
        clickOn(R.id.alarmFormFriday)

        assertAny<TextView>(R.id.alarmFormMonday) { it.isActivated }
        assertAny<TextView>(R.id.alarmFormWednesday) { it.isActivated }
        assertAny<TextView>(R.id.alarmFormFriday) { it.isActivated }

        assertTextColorIs(R.id.alarmFormMonday, android.R.color.white)
        assertTextColorIs(R.id.alarmFormWednesday, android.R.color.white)
        assertTextColorIs(R.id.alarmFormFriday, android.R.color.white)

        assertHasBackground(R.id.alarmFormMonday, R.drawable.background_activated)
        assertHasBackground(R.id.alarmFormWednesday, R.drawable.background_activated)
        assertHasBackground(R.id.alarmFormFriday, R.drawable.background_activated)
    }

    @Test
    fun dismissDialog_whenNeutralDialogButtonIsClicked() {
        clickOn(R.id.alarmFormNeutralButton)

        assertNotExist(R.string.disconnects_at_label)
    }

    @Test
    fun testAlarmCreation_whenPositiveButtonIsClicked() {
        clickOn(R.id.alarmFormStartTimeValue)
        setTimeOnPicker(10, 0)
        clickOn(R.id.alarmFormEndTimeValue)
        setTimeOnPicker(10, 30)
        clickOn(R.id.alarmFormWifi)
        clickOn(R.id.alarmFormSaturday)
        clickOn(R.id.alarmFormSunday)

        val alarm = dialog.buildAlarm()

        assertEquals(10, alarm.startTime.hour)
        assertEquals(0, alarm.startTime.minute)
        assertEquals(10, alarm.endTime.hour)
        assertEquals(30, alarm.endTime.minute)
        assertEquals(1, alarm.connectivities.size)
        assertTrue(alarm.connectivities.contains(Connectivity.WIFI.value))
        assertEquals(2, alarm.days.size)
        assertTrue(alarm.days.contains(Day.SATURDAY.value))
        assertTrue(alarm.days.contains(Day.SUNDAY.value))
    }

    @Test
    fun displayError_whenNeitherStartOrEndTimeAreSetAndPositiveButtonIsClicked() {
        clickOn(R.id.alarmFormPositiveButton)

        assertToastIsDisplayedWithMessage(R.string.create_alarm_error, rule.activity)
    }

    @Test
    fun displayError_whenStartTimeIsNotSetAndPositiveButtonIsClicked() {
        clickOn(R.id.alarmFormEndTimeValue)
        setTimeOnPicker(10, 30)

        clickOn(R.id.alarmFormPositiveButton)

        assertToastIsDisplayedWithMessage(R.string.create_alarm_error, rule.activity)
    }

    @Test
    fun displayError_whenEndTimeIsNotSetAndPositiveButtonIsClicked() {
        clickOn(R.id.alarmFormStartTimeValue)
        setTimeOnPicker(10, 0)

        clickOn(R.id.alarmFormPositiveButton)

        assertToastIsDisplayedWithMessage(R.string.create_alarm_error, rule.activity)
    }

    @Test
    fun dismissDialog_whenBackIsPressed() {
        clickBack()

        assertNotExist(R.string.disconnects_at_label)
    }

    @Test
    fun dismissDialog_whenFieldsAreValidAndPositiveButtonIsClicked() {
        clickOn(R.id.alarmFormStartTimeValue)
        setTimeOnPicker(10, 0)
        clickOn(R.id.alarmFormEndTimeValue)
        setTimeOnPicker(10, 30)

        clickOn(R.id.alarmFormPositiveButton)

        assertNotExist(R.string.disconnects_at_label)
    }
}