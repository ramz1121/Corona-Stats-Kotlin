package com.corona.java.coronastatskotlin.util

import java.text.DecimalFormat

object Common {
    fun formatNumber(cases: Double?): String {
        var formatter = DecimalFormat("#,###,###")
        var formattedString: String = formatter.format(cases)

        return formattedString

    }
}