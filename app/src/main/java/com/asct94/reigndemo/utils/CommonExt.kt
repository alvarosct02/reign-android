package com.asct94.reigndemo.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
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

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    for (ni in connectivityManager!!.allNetworkInfo) {
        if (ni.typeName.equals("WIFI", ignoreCase = true) && ni.isConnected) return true
        if (ni.typeName.equals("MOBILE", ignoreCase = true) && ni.isConnected)  return true
    }
    return false

}