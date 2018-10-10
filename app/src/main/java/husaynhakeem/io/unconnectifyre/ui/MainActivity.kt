package husaynhakeem.io.unconnectifyre.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import husaynhakeem.io.unconnectifyre.R
import husaynhakeem.io.unconnectifyre.ui.list.AlarmsFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView(savedInstanceState)
    }

    private fun setupView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.mainContent, AlarmsFragment())
                    .commit()
        }
    }
}