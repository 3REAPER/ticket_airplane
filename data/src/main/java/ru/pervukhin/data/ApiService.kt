package ru.pervukhin.data

import retrofit2.Response
import retrofit2.http.GET
import ru.pervukhin.data.model.OfferData
import ru.pervukhin.data.model.OfferResponse
import ru.pervukhin.data.model.TicketData
import ru.pervukhin.data.model.TicketOfferData
import ru.pervukhin.data.model.TicketOfferResponse
import ru.pervukhin.data.model.TicketResponse
import ru.pervukhin.domain.Offer
import ru.pervukhin.domain.Ticket
import ru.pervukhin.domain.TicketOffer

interface ApiService {

    @GET("offers.json")
    suspend fun getOffers(): Response<OfferData>

    @GET("tickets.json")
    suspend fun getTicket(): Response<TicketData>

    @GET("offers_tickets.json")
    suspend fun getTicketOffers(): Response<TicketOfferData>

}