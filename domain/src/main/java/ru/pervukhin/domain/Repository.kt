package ru.pervukhin.domain

interface Repository {

    suspend fun getOffers(): Resource<List<Offer>>

    suspend fun getTickets(): Resource<List<Ticket>>

    suspend fun getTicketOffers(): Resource<List<TicketOffer>>
}