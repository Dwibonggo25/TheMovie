package com.example.jetpack.utils

import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun String?.getYearFromDate() : String? {

    val dateFormat = SimpleDateFormat("yyyy", Locale.US)
    try {
        val year = dateFormat.parse(this)
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(year.year)
    }catch (e: Exception) {

    }

    return null
}