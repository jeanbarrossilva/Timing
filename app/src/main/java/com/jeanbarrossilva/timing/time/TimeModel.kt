package com.jeanbarrossilva.timing.time

import android.app.Activity
import java.text.NumberFormat
import java.util.*
import java.util.Calendar.HOUR_OF_DAY
import java.util.Calendar.MINUTE
import java.util.Calendar.SECOND
import kotlin.concurrent.timerTask

class TimeModel {
    val Number.asTime: String
        get() {
            NumberFormat.getNumberInstance().apply {
                minimumIntegerDigits = 2
                return format(this@asTime).toString()
            }
        }

    val timer = Timer()
    
    inline fun time(
        activity: Activity?,
        crossinline values: (
            hour: Int,
            minute: Int,
            second: Int
        ) -> Unit = { _, _, _ -> }
    ) {
        activity?.let {
            timer.schedule(
                timerTask {
                    it.runOnUiThread {
                        with(Calendar.getInstance()) {
                            values.invoke(get(HOUR_OF_DAY), get(MINUTE), get(SECOND))
                        }
                    }
                }, 0, 1
            )
        }
    }
}