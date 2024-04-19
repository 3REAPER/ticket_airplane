package ru.pervukhin.domain

interface Repository {

    suspend fun getOffers(): List<Offer>?

    suspend fun getTickets(): List<Ticket>?

    suspend fun getTicketOffers(): List<TicketOffer>?
}