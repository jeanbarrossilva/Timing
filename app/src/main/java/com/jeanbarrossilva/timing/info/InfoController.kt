package com.jeanbarrossilva.timing.info

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.jeanbarrossilva.timing.R

class InfoController : Fragment(R.layout.fragment_info) {
    private val infoModel = InfoModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view) {
            // It shouldn't be a requirement to add "as TextView" after "findViewById(...)", but even if we do, Android Lint returns a warning. 🤷🏽‍♂️
            setLinkFrom(
                ("https://twitter.com/jeanbarrossilva" to "@jeanbarrossilva") to findViewById(R.id.credits) as TextView,
                ("https://github.com/jeanbarrossilva/Timing" to "GitHub") to findViewById(R.id.availability_source_code) as TextView
            )
        }
    }

    private fun setLinkFrom(vararg sets: Pair<Pair<String, String>, TextView>) {
        for (set in sets) with(set.first) {
            with(infoModel) {
                set.second.asLink(activity, highlight = second, url = Uri.parse(first))
            }
        }
    }
}