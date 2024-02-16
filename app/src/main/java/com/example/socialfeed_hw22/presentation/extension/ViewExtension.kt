package com.example.socialfeed_hw22.presentation.extension

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView

fun View.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatTextView.resetMarginStart() {
    (this.layoutParams as ViewGroup.MarginLayoutParams).apply {
        marginStart = 0
    }
}