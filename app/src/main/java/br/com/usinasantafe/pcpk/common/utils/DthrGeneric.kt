package br.com.usinasantafe.cmm.common.utils

import java.text.SimpleDateFormat
import java.util.*

fun dateNow(): Date{
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    return sdf.parse(sdf.format(Date()))!!
}
