package ru.pervukhin.presentation

import android.content.Context
import android.view.View
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.getStringByFormat(format: String) =
    SimpleDateFormat(format).format(this)


fun Float.toCurrencyString(context: Context): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.FRANCE)
    if ((this % 1) != 0f) {
        numberFormat.minimumFractionDigits = 2
    } else {
        numberFormat.maximumFractionDigits = 0
    }
    val priceString = numberFormat.format(this)
    val currencySymbol: String = context.getString(R.string.currency_symbol)
    return String.format(
        context.getString(R.string.currency_symbol),
        priceString,
        currencySymbol
    )
}

fun View.gone(){
    visibility = View.GONE
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}