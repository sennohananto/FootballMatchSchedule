package com.sennohananto.footballmatchschedule

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun toDateIndo(date:String, pattern:String): String? {
    val localindo = Locale("in","ID","ID")
    val inputFormat = SimpleDateFormat(pattern, localindo)
    val outputFormat = SimpleDateFormat("E, dd MMM yyyy", localindo)

    return outputFormat.format(inputFormat.parse(date))
}