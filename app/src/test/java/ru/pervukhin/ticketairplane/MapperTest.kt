package ru.pervukhin.ticketairplane

import org.junit.Test

import org.junit.Assert.*
import ru.pervukhin.data.Mapper
import ru.pervukhin.data.Mapper.Companion.toOffer
import ru.pervukhin.data.Mapper.Companion.toTicket
import ru.pervukhin.data.Mapper.Companion.toTicketOffer
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
import java.util.Calendar

class MapperTest {
    @Test
    fun testTicketToDomain() {
        val ticketResponse =
            TicketResponse(
                1,
                "2",
                PriceResponse(100),
                "3",
                "4",
                FlyInfoResponse("5", "2024-06-07T03:23:01", "6"),
                FlyInfoResponse("7", "2025-07-08T04:24:02", "8"),
                true,
                false,
                LuggageResponse(true, PriceResponse(200)),
                HandLuggageResponse(false, "24x23"),
                true,
                false
            )
        val test = ticketResponse.toTicket()
        println(test)
        val date1 = Calendar.getInstance()
        date1.set(Calendar.YEAR, 2024)
        date1.set(Calendar.MONTH, 6 - 1)
        date1.set(Calendar.DAY_OF_MONTH, 7)
        date1.set(Calendar.HOUR, 3)
        date1.set(Calendar.MINUTE, 23)
        date1.set(Calendar.SECOND, 1)
        date1.set(Calendar.MILLISECOND, 0)
        val date2 = Calendar.getInstance()
        date2.set(Calendar.YEAR, 2025)
        date2.set(Calendar.MONTH, 7 - 1)
        date2.set(Calendar.DAY_OF_MONTH, 8)
        date2.set(Calendar.HOUR, 4)
        date2.set(Calendar.MINUTE, 24)
        date2.set(Calendar.SECOND, 2)
        date2.set(Calendar.MILLISECOND, 0)
        val ticketDomain =
            Ticket(
                1,
                "2",
                Price(100),
                "3",
                "4",
                FlyInfo("5", date1.time, "6"),
                FlyInfo("7", date2.time, "8"),
                true,
                false,
                Luggage(true, Price(200)),
                HandLuggage(false, "24x23"),
                true,
                false
            )
        //Ticket(id=1, badge=2, price=Price(value=100), providedName=3, company=4, departure=FlyInfo(town=5, date=Sun Jul 07 03:23:01 OMST 2024, airport=6), arrival=FlyInfo(town=7, date=Sun Jul 07 03:23:01 OMST 2024, airport=8), hasTransfer=true, hasVisaTransfer=false, luggage=Luggage(hasLuggage=true, price=Price(value=200)), handLuggage=HandLuggage(hasHandLuggage=false, size=24x23), isReturnable=true, isExchangable=false)
        //Ticket(id=1, badge=2, price=Price(value=100), providedName=3, company=4, departure=FlyInfo(town=5, date=Sun Jan 07 03:23:01 OMST 2024, airport=6), arrival=FlyInfo(town=7, date=Wed Jan 08 04:24:02 OMST 2025, airport=8), hasTransfer=true, hasVisaTransfer=false, luggage=Luggage(hasLuggage=true, price=Price(value=200)), handLuggage=HandLuggage(hasHandLuggage=false, size=24x23), isReturnable=true, isExchangable=false)
        assertEquals(ticketDomain, ticketResponse.toTicket())
    }

    @Test
    fun testOfferToDomain(){
        val offerResponse = OfferResponse(
            1,
            "2",
            "3",
            PriceResponse(4)
        )
        val offer = Offer(
            1,
            "2",
            "3",
            Price(4)
        )
        assertEquals(offer, offerResponse.toOffer())
    }

    @Test
    fun testTicketOffer(){
        val ticketOfferResponse = TicketOfferResponse(
            1,
            "2",
            listOf("3","4","5"),
            PriceResponse(6)
        )
        val ticketOffer = TicketOffer(
            1,
            "2",
            listOf("3","4","5"),
            Price(6)
        )
        assertEquals(ticketOffer, ticketOfferResponse.toTicketOffer())
    }
}