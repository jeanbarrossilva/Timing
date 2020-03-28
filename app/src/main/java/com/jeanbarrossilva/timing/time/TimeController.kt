package com.jeanbarrossilva.timing.time

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.jeanbarrossilva.timing.R

class TimeController : Fragment(R.layout.fragment_time) {
    private val timeModel = TimeModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view) {
            updateTime(standardView = findViewById(R.id.standard), secondView = findViewById(R.id.second))
        }
    }

    private fun updateTime(standardView: TextView, secondView: TextView) = with(timeModel) {
        time(activity) { hour, minute, second ->
            standardView.text = try { getString(R.string.placeholder_time).format(hour.asTime, minute.asTime) } catch (e: Exception) { "" }
            secondView.text   = second.asTime
        }
    }
}