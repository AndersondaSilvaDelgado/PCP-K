package br.com.usinasantafe.pcpk.common.utils

import java.util.Calendar
import java.util.Date

fun dateToDelete(): Date {
    val c: Calendar = Calendar.getInstance()
    c.time = Date()
    c.add(Calendar.DATE, -3)
    return c.time
}