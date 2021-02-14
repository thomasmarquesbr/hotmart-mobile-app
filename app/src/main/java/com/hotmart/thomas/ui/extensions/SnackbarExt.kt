package com.hotmart.thomas.ui.extensions

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.hotmart.thomas.R


fun Snackbar.showError() {
    view.run {
        setBackgroundColor(ContextCompat.getColor(context, R.color.red))
        findViewById<TextView>(com.google.android.material.R.id.snackbar_text).run {
            setTextColor(ContextCompat.getColor(context, R.color.white))
            setTypeface(typeface, Typeface.BOLD)
            textSize = 16F
            gravity = Gravity.CENTER
            textAlignment = View.TEXT_ALIGNMENT_CENTER
        }
    }
    show()
}