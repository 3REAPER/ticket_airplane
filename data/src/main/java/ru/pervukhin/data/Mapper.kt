package ru.pervukhin.data

import ru.pervukhin.data.model.FlyInfoResponse
import ru.pervukhin.data.model.HandLuggageResponse
import ru.pervukhin.data.model.LuggageResponse
import ru.pervukhin.data.model.OfferResponse
import ru.pervukhin.data.model.PriceResponse
import ru.pervukhin.data.model.TicketOfferResponse
import ru.pervukhin.data.model.TicketResponse
import ru.pervukhin.domain.FlyInfo
import ru.pervukhin.domain.HandLuggage
import ru.pervukhin.domain.Luggage
import ru.pervukhin.domain.Offer
import ru.pervukhin.domain.Price
import ru.pervukhin.domain.Ticket
import ru.pervukhin.domain.TicketOffer
import java.text.SimpleDateFormat

class Mapper {

    companion object {
        fun OfferResponse.toOffer() = Offer(
            id,
            title,
            town,
            price.toPrice()
        )

        fun TicketOfferResponse.toTicketOffer() = TicketOffer(
            id,
            title,
            timeRange,
            price.toPrice()
        )

        fun TicketResponse.toTicket() = Ticket(
            id,
            badge,
            price.toPrice(),
            providerName,
            company,
            departure.toFlyInfo(),
            arrival.toFlyInfo(),
            hasTransfer,
            hasVisaTransfer,
            luggage.toLuggage(),
            handLuggage.toHandLuggage(),
            isReturnable,
            isExchangable
        )

        private fun PriceResponse.toPrice() = Price(
            price
        )

        private fun FlyInfoResponse.toFlyInfo() = FlyInfo(
            town,
            SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss").parse(date),
            airport
        )

        private fun LuggageResponse.toLuggage() = Luggage(
            hasLuggage,
            price?.toPrice()
        )

        private fun HandLuggageResponse.toHandLuggage() = HandLuggage(
            hasHandLuggage,
            size
        )


    }
}