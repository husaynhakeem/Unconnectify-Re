package husaynhakeem.io.unconnectifyre.utilities

import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import husaynhakeem.io.unconnectifyre.R


fun View.toggleActivation() {
    this.isActivated = !this.isActivated
}

fun TextView.toggleBackground(addBackground: Boolean) {
    if (addBackground) {
        background = ContextCompat.getDrawable(context, R.drawable.background_activated)
        setTextColor(ContextCompat.getColor(context, android.R.color.white))
    } else {
        setTextColor(ContextCompat.getColor(context, android.R.color.black))
        background = null
    }
}