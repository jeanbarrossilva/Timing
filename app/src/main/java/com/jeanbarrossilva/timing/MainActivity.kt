package com.jeanbarrossilva.timing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jeanbarrossilva.timing.info.InfoController
import com.jeanbarrossilva.timing.time.TimeController

class MainActivity : AppCompatActivity() {
    private fun FragmentContainerView.replaceBy(fragment: Fragment) = supportFragmentManager.beginTransaction().replace(id, fragment).commit()

    private val timeController = TimeController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        val container: FragmentContainerView           = findViewById(R.id.container)

        with(container) {
            configNavigationTabs(bottomNavigationView, container)
            replaceBy(timeController)
        }
    }

    private fun configNavigationTabs(view: BottomNavigationView, container: FragmentContainerView) {
        view.setOnNavigationItemSelectedListener { item ->
            container.replaceBy(
                when (item.itemId) {
                    R.id.time -> timeController
                    R.id.info -> InfoController()
                    else -> Fragment()
                }
            )

            true
        }
    }
}