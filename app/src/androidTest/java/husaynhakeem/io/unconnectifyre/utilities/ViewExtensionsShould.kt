package husaynhakeem.io.unconnectifyre.utilities

import android.support.test.InstrumentationRegistry
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import org.junit.Assert.*
import org.junit.Test


class ViewExtensionsShould {

    private val context = InstrumentationRegistry.getTargetContext()

    @Test
    fun toggleActivationToFalseWhenItIsTrue() {
        val view = View(context)
        view.isActivated = true

        view.toggleActivation()

        assertFalse(view.isActivated)
    }

    @Test
    fun toggleActivationToTrueWhenItIsFalse() {
        val view = View(context)
        view.isActivated = false

        view.toggleActivation()

        assertTrue(view.isActivated)
    }

    @Test
    fun addBackgroundToTextView() {
        val textView = TextView(context)

        textView.toggleBackground(true)

        assertNotNull(textView.background)
        assertEquals(ContextCompat.getColor(context, android.R.color.white), textView.currentTextColor)
    }

    @Test
    fun removeBackgroundFromTextView() {
        val textView = TextView(context)

        textView.toggleBackground(false)

        assertNull(textView.background)
        assertEquals(ContextCompat.getColor(context, android.R.color.black), textView.currentTextColor)
    }
}