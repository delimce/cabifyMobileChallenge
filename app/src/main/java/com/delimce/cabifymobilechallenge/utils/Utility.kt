package com.delimce.cabifymobilechallenge.utils

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat

class Utility {

    companion object {
        fun formatNumber(value: Number? = 0): String {
            return NumberFormat.getInstance().format(value)
        }

        fun getCurrency(value: Number? = 0, currency: String = "€"): String {
            return "${this.formatNumber(this.roundTo2Decimal(value))}$currency"
        }

        fun roundTo2Decimal(value: Number? = 0.0, n: Int = 2) =
            BigDecimal(value!!.toDouble()).setScale(n, RoundingMode.HALF_EVEN).toDouble()
    }
}