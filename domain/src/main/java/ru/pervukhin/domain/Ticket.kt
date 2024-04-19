package ru.pervukhin.domain

import java.util.Date

data class Ticket(
    val id: Int,
    val badge: String,
    val price: Price,
    val providedName: String,
    val company: String,
    val departure: FlyInfo,
    val arrival: FlyInfo,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: Luggage,
    val handLuggage: HandLuggage,
    val isReturnable: Boolean,
    val isExchangable: Boolean
)

data class FlyInfo(
    val town: String,
    val date: Date,
    val airport: String
)

data class Luggage(
    val hasLuggage: Boolean,
    val price: Price
)

data class HandLuggage(
    val hasHandLuggage: Boolean,
    val size: String
)