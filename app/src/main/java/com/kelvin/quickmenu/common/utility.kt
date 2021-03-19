package com.kelvin.quickmenu.common

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

class utility {
    companion object{
        var us:Locale= Locale.US
        var percentage=NumberFormat.getPercentInstance(us)
        var currencyFormat=NumberFormat.getCurrencyInstance(us)
        var numberFormat=NumberFormat.getNumberInstance(us)
        fun roundTwoDecimal(number:Double):BigDecimal{
            return BigDecimal.valueOf(number).setScale(2,RoundingMode.HALF_UP)
        }
    }
}