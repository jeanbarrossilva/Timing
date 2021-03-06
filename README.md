# Timing
Timing is a simple clock app for Android, designed for study purposes only.

## Functionalities
### Real-time time (what?) update
The [`TimeController`](https://github.com/jeanbarrossilva/Timing/blob/master/app/src/main/java/com/jeanbarrossilva/timing/time/TimeController.kt) displays the time by calling [`TimeModel`](https://github.com/jeanbarrossilva/Timing/blob/master/app/src/main/java/com/jeanbarrossilva/timing/time/TimeModel.kt) and its `time` function, which creates a `TimerTask` that updates the `hour`, `minute` and `second` values, obtained by creating a new instance of `Calendar`, as shown below.

```kotlin
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
```
