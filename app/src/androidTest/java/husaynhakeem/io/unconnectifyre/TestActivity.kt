package husaynhakeem.io.unconnectifyre

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout


class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(FrameLayout(this))
    }
}