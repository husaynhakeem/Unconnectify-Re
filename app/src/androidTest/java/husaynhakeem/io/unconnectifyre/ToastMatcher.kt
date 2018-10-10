package husaynhakeem.io.unconnectifyre

import android.support.test.espresso.Root
import android.view.WindowManager
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher


class ToastMatcher : TypeSafeMatcher<Root>() {

    override fun describeTo(description: Description) {
        description.appendText("Is toast")
    }

    override fun matchesSafely(root: Root): Boolean {
        val type = root.windowLayoutParams.get().type
        if (type == WindowManager.LayoutParams.TYPE_TOAST) {
            val windowToken = root.decorView.windowToken
            val appToken = root.decorView.applicationWindowToken
            if (windowToken === appToken) {
                return true
            }
        }
        return false
    }
}