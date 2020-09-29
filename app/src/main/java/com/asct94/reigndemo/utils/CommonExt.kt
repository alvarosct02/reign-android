package com.asct94.reigndemo.utils

import java.text.SimpleDateFormat
import java.util.*


fun Calendar.format(out: String = Constants.DATE_FORMAT_DATETIME_API): String {
    return SimpleDateFormat(out, Locale.getDefault()).format(this.time)
}

fun String.toCalendar(format: String = Constants.DATE_FORMAT_DATETIME_API): Calendar {

    val df = SimpleDateFormat(format, Locale.getDefault())
        .apply { timeZone = TimeZone.getTimeZone("UTC") }

    return Calendar.getInstance().also {
        it.time = try {
            df.parse(this)!!
        } catch (e: Exception) {
            Date()
        }
    }
}