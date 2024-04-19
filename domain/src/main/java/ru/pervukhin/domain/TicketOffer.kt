package ru.pervukhin.domain

data class TicketOffer(
    val id: Int,
    val tittle: String,
    val timeRange: List<String>,
    val price: Price
)