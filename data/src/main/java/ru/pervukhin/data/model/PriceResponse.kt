package ru.pervukhin.data.model

import com.google.gson.annotations.SerializedName

data class PriceResponse(
    @SerializedName("price")
    val price: Int
)