package ru.pervukhin.data

import ru.pervukhin.data.Mapper.Companion.toOffer
import ru.pervukhin.data.Mapper.Companion.toTicket
import ru.pervukhin.data.Mapper.Companion.toTicketOffer
import ru.pervukhin.domain.Offer
import ru.pervukhin.domain.Repository
import ru.pervukhin.domain.Ticket
import ru.pervukhin.domain.TicketOffer
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val service: ApiService
): Repository {

    override suspend fun getOffers(): List<Offer>? {
        val response = service.getOffers()

        return response.body()?.data?.map { it.toOffer() }
    }

    override suspend fun getTickets(): List<Ticket>? {
        val response = service.getTicket()

        return response.body()?.data?.map { it.toTicket() }
    }

    override suspend fun getTicketOffers(): List<TicketOffer>? {
        val response = service.getTicketOffers()

        return response.body()?.data?.map { it.toTicketOffer() }
    }
}