package husaynhakeem.io.unconnectifyre.data

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import husaynhakeem.io.unconnectifyre.data.database.Alarm
import husaynhakeem.io.unconnectifyre.data.database.AlarmDao
import husaynhakeem.io.unconnectifyre.data.database.AlarmDatabase
import husaynhakeem.io.unconnectifyre.data.database.Time
import husaynhakeem.io.unconnectifyre.utilities.getValue
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AlarmDatabaseShould {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var database: AlarmDatabase
    private lateinit var dao: AlarmDao

    private val alarmA = Alarm(1,
            Time(10, 10, 0),
            Time(11, 10, 30),
            listOf(Connectivity.WIFI.value),
            listOf(Day.SATURDAY.value, Day.SUNDAY.value))

    private val alarmB = Alarm(2,
            Time(12, 11, 0),
            Time(13, 12, 0),
            listOf(Connectivity.HOTSPOT.value, Connectivity.BLUETOOTH.value),
            listOf(Day.MONDAY.value))

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, AlarmDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        dao = database.alarmDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun createAnAlarm() {
        dao.createAlarm(alarmA)

        val result = dao.getAllAlarms()

        assertEquals(1, getValue(result).size)
        assertEquals(alarmA.id, getValue(result)[0].id)
    }

    @Test
    fun createMultipleAlarms() {
        dao.createAlarm(alarmA)
        dao.createAlarm(alarmB)

        val result = dao.getAllAlarms()

        assertEquals(2, getValue(result).size)
        assertTrue(getValue(result).containsAll(listOf(alarmA, alarmB)))
    }

    @Test
    fun deleteAnExistingAlarm() {
        dao.createAlarm(alarmA)
        dao.deleteAlarm(alarmA.id)

        val result = dao.getAllAlarms()

        assertEquals(0, getValue(result).size)
    }

    @Test
    fun notDeleteANonExistingAlarm() {
        dao.createAlarm(alarmA)
        dao.deleteAlarm(alarmB.id)

        val result = dao.getAllAlarms()

        assertEquals(1, getValue(result).size)
    }

    @Test
    fun findAnExistingAlarmByItsId() {
        dao.createAlarm(alarmA)
        dao.createAlarm(alarmB)

        val result = dao.findAlarmById(alarmA.id)

        assertEquals(alarmA, getValue(result))
    }

    @Test
    fun returnNullWhenFindingANonExistingAlarmByItsId() {
        dao.createAlarm(alarmA)

        val result = dao.findAlarmById(alarmB.id)

        assertNull(getValue(result))
    }

    @Test
    fun updateAnExistingAlarm() {
        dao.createAlarm(alarmA)

        dao.updateAlarm(alarmA.copy(startTime = Time(20, 2, 30)))

        val result = dao.findAlarmById(alarmA.id)
        assertEquals(Time(20, 2, 30), getValue(result).startTime)
    }
}