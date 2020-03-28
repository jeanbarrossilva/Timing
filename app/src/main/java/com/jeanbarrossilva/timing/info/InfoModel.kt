package com.jeanbarrossilva.timing.info

import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import com.jeanbarrossilva.timing.R

class InfoModel {
    fun TextView.asLink(activity: Activity?, highlight: String, url: Uri) {
        activity?.let {
            with(it) {
                setSpannableFactory(
                    Spannable.Factory().apply {
                        setText(
                            SpannableString(
                                newSpannable(text).apply {
                                    setSpan(
                                        ClickableSpan.wrap(object: ClickableSpan() {
                                            override fun onClick(widget: View) {

                                            }

                                            override fun updateDrawState(ds: TextPaint) {
                                                super.updateDrawState(ds)

                                                with(ds) {
                                                    isUnderlineText = false
                                                    color = resources.getColor(R.color.colorAccent, theme)
                                                }
                                            }
                                        }), text.indexOf(highlight), with(highlight) { text.indexOf(this) + length }, SPAN_EXCLUSIVE_EXCLUSIVE
                                    )
                                }
                            ), TextView.BufferType.SPANNABLE
                        )
                    }
                )

                setOnClickListener {
                    startActivity(Intent(ACTION_VIEW, url))
                    TODO("Use ClickableSpan.onClick instead")
                }
            }
        }

        movementMethod = LinkMovementMethod.getInstance()
    }
}