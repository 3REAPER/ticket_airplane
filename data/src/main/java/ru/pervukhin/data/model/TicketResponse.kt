package ru.pervukhin.data.model

import com.google.gson.annotations.SerializedName

data class TicketResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("badge")
    val badge: String?,
    @SerializedName("price")
    val price: PriceResponse,
    @SerializedName("provider_name")
    val providerName: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("departure")
    val departure: FlyInfoResponse,
    @SerializedName("arrival")
    val arrival: FlyInfoResponse,
    @SerializedName("has_transfer")
    val hasTransfer: Boolean,
    @SerializedName("has_visa_transfer")
    val hasVisaTransfer: Boolean,
    @SerializedName("luggage")
    val luggage: LuggageResponse,
    @SerializedName("hand_luggage")
    val handLuggage: HandLuggageResponse,
    @SerializedName("is_returnable")
    val isReturnable: Boolean,
    @SerializedName("is_exchangable")
    val isExchangable: Boolean
)

data class TicketData(
    @SerializedName("tickets")
    val data: List<TicketResponse>
)

data class FlyInfoResponse(
    @SerializedName("town")
    val town: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("airport")
    val airport: String
)

data class LuggageResponse(
    @SerializedName("has_luggage")
    val hasLuggage: Boolean,
    @SerializedName("price")
    val price: PriceResponse?
)

data class HandLuggageResponse(
    @SerializedName("has_hand_luggage")
    val hasHandLuggage: Boolean,
    @SerializedName("size")
    val size: String?
)