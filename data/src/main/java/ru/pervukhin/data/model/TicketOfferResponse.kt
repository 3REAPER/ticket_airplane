package ru.pervukhin.data.model

import com.google.gson.annotations.SerializedName

data class TicketOfferResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("time_range")
    val timeRange: List<String>,
    @SerializedName("price")
    val price: PriceResponse
)

data class TicketOfferData(
    @SerializedName("tickets_offers")
    val data: List<TicketOfferResponse>
)