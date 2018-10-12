package husaynhakeem.io.unconnectifyre.ui.dialog

import android.support.test.rule.ActivityTestRule
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaPickerInteractions.setTimeOnPicker
import husaynhakeem.io.unconnectifyre.R
import husaynhakeem.io.unconnectifyre.TestActivity
import husaynhakeem.io.unconnectifyre.data.Connectivity
import husaynhakeem.io.unconnectifyre.data.Day
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class AlarmFormDialogShould {

    @get:Rule
    var rule = ActivityTestRule(TestActivity::class.java)

    class TestAlarmFormDialog : AlarmFormDialog() {
        override fun setupView() {
        }
    }

    private val dialog = TestAlarmFormDialog()

    @Before
    fun setUp() {
        dialog.show(rule.activity.supportFragmentManager, AlarmFormDialog::class.java.simpleName)
    }

    @Test
    fun updateAlarmInstance_whenStartTimeIsUpdated() {
        clickOn(R.id.alarmFormStartTimeValue)
        setTimeOnPicker(10, 0)

        val alarm = dialog.buildAlarm()

        assertEquals(10, alarm.startTime.hour)
        assertEquals(0, alarm.startTime.minute)
    }

    @Test
    fun updateAlarmInstance_whenEndTimeIsUpdated() {
        clickOn(R.id.alarmFormEndTimeValue)
        setTimeOnPicker(10, 30)

        val alarm = dialog.buildAlarm()

        assertEquals(10, alarm.endTime.hour)
        assertEquals(30, alarm.endTime.minute)
    }

    @Test
    fun updateAlarmInstance_whenConnectiviesAreUpdatedBySelectingWifi() {
        clickOn(R.id.alarmFormWifi)

        val alarm = dialog.buildAlarm()

        assertEquals(1, alarm.connectivities.size)
        assertTrue(alarm.connectivities.contains(Connectivity.WIFI.value))
    }

    @Test
    fun updateAlarmInstance_whenConnectiviesAreUpdatedBySelectingBluetooth() {
        clickOn(R.id.alarmFormBluetooth)

        val alarm = dialog.buildAlarm()

        assertEquals(1, alarm.connectivities.size)
        assertTrue(alarm.connectivities.contains(Connectivity.BLUETOOTH.value))
    }

    @Test
    fun updateAlarmInstance_whenConnectiviesAreUpdatedBySelectingHotspot() {
        clickOn(R.id.alarmFormHotspot)

        val alarm = dialog.buildAlarm()

        assertEquals(1, alarm.connectivities.size)
        assertTrue(alarm.connectivities.contains(Connectivity.HOTSPOT.value))
    }

    @Test
    fun updateAlarmInstance_whenConnectiviesAreUpdatedBySelectingWifiAndHotspot() {
        clickOn(R.id.alarmFormWifi)
        clickOn(R.id.alarmFormHotspot)

        val alarm = dialog.buildAlarm()

        assertEquals(2, alarm.connectivities.size)
        assertTrue(alarm.connectivities.containsAll(listOf(Connectivity.WIFI.value, Connectivity.HOTSPOT.value)))
    }

    @Test
    fun updateAlarmInstance_whenConnectiviesAreUpdatedBySelectingAllConnectivities() {
        clickOn(R.id.alarmFormWifi)
        clickOn(R.id.alarmFormBluetooth)
        clickOn(R.id.alarmFormHotspot)

        val alarm = dialog.buildAlarm()

        assertEquals(3, alarm.connectivities.size)
        assertTrue(alarm.connectivities.containsAll(listOf(Connectivity.WIFI.value, Connectivity.BLUETOOTH.value, Connectivity.HOTSPOT.value)))
    }

    @Test
    fun updateAlarmInstance_whenDaysAreUpdatedBySelectingSunday() {
        clickOn(R.id.alarmFormSunday)

        val alarm = dialog.buildAlarm()

        assertEquals(1, alarm.days.size)
        assertTrue(alarm.days.contains(Day.SUNDAY.value))
    }

    @Test
    fun updateAlarmInstance_whenDaysAreUpdatedBySelectingMonday() {
        clickOn(R.id.alarmFormMonday)

        val alarm = dialog.buildAlarm()

        assertEquals(1, alarm.days.size)
        assertTrue(alarm.days.contains(Day.MONDAY.value))
    }

    @Test
    fun updateAlarmInstance_whenDaysAreUpdatedBySelectingTuesday() {
        clickOn(R.id.alarmFormTuesday)

        val alarm = dialog.buildAlarm()

        assertEquals(1, alarm.days.size)
        assertTrue(alarm.days.contains(Day.TUESDAY.value))
    }

    @Test
    fun updateAlarmInstance_whenDaysAreUpdatedBySelectingWednesday() {
        clickOn(R.id.alarmFormWednesday)

        val alarm = dialog.buildAlarm()

        assertEquals(1, alarm.days.size)
        assertTrue(alarm.days.contains(Day.WEDNESDAY.value))
    }

    @Test
    fun updateAlarmInstance_whenDaysAreUpdatedBySelectingThursday() {
        clickOn(R.id.alarmFormThursday)

        val alarm = dialog.buildAlarm()

        assertEquals(1, alarm.days.size)
        assertTrue(alarm.days.contains(Day.THURSDAY.value))
    }

    @Test
    fun updateAlarmInstance_whenDaysAreUpdatedBySelectingFriday() {
        clickOn(R.id.alarmFormFriday)

        val alarm = dialog.buildAlarm()

        assertEquals(1, alarm.days.size)
        assertTrue(alarm.days.contains(Day.FRIDAY.value))
    }

    @Test
    fun updateAlarmInstance_whenDaysAreUpdatedBySelectingSaturday() {
        clickOn(R.id.alarmFormSaturday)

        val alarm = dialog.buildAlarm()

        assertEquals(1, alarm.days.size)
        assertTrue(alarm.days.contains(Day.SATURDAY.value))
    }

    @Test
    fun updateAlarmInstance_whenDaysAreUpdatedBySelectingAllDays() {
        clickOn(R.id.alarmFormSunday)
        clickOn(R.id.alarmFormMonday)
        clickOn(R.id.alarmFormTuesday)
        clickOn(R.id.alarmFormWednesday)
        clickOn(R.id.alarmFormThursday)
        clickOn(R.id.alarmFormFriday)
        clickOn(R.id.alarmFormSaturday)

        val alarm = dialog.buildAlarm()

        assertEquals(7, alarm.days.size)
        assertTrue(alarm.days.containsAll(listOf(Day.SUNDAY.value, Day.MONDAY.value, Day.TUESDAY.value, Day.WEDNESDAY.value, Day.THURSDAY.value, Day.FRIDAY.value, Day.SATURDAY.value)))
    }
}