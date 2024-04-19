package ru.pervukhin.data.model

import com.google.gson.annotations.SerializedName
import ru.pervukhin.domain.Price

data class OfferResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("town")
    val town: String,
    @SerializedName("price")
    val price: PriceResponse
)

data class OfferData(
    @SerializedName("offers")
    val data: List<OfferResponse>
)