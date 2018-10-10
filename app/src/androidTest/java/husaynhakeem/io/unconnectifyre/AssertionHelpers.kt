package husaynhakeem.io.unconnectifyre

import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.v7.app.AppCompatActivity


fun assertToastIsDisplayedWithMessage(@StringRes resId: Int, activity: AppCompatActivity) {
    onView(withText(resId))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
}

fun assertToastIsDisplayedWithMessage(text: String, activity: AppCompatActivity) {
    onView(withText(text))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
}