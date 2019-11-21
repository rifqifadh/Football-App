package com.example.rifqi.footballapp.utils

import android.net.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateTime {

    private fun setDate(date: String, format: String, isDate: Boolean): String {
        var result = ""
        val before = SimpleDateFormat(if (isDate) "yyyy-MM-dd"
        else "HH:mm:ss", Locale.getDefault())
        before.timeZone = TimeZone.getTimeZone("UTC")

        try {
            val beforeDate = before.parse(date)
            val newDate = SimpleDateFormat(format, Locale.getDefault())

            result = newDate.format(beforeDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return result
    }

    fun dateFormat(date: String?): String {
        return setDate(date.toString(), "EEE, dd MMMM yyyy", true)
    }

    fun timeFormat(time: String?): String {
        return setDate(time.toString(), "HH:mm", false)
    }
}