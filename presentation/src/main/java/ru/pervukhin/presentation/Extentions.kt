package ru.pervukhin.presentation

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.util.DisplayMetrics
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import java.text.NumberFormat
import java.util.Locale

fun Int.px() =
    (this * Resources.getSystem().displayMetrics.density)

fun <T> ImageView.loadImage(model: T) {
    Glide.with(context)
        .load(model)
        .centerCrop()
        .into(this)
}

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