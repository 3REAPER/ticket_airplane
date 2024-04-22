package ru.pervukhin.ticketairplane

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.pervukhin.data.ApiService
import ru.pervukhin.data.model.FlyInfoResponse
import ru.pervukhin.data.model.HandLuggageResponse
import ru.pervukhin.data.model.LuggageResponse
import ru.pervukhin.data.model.OfferResponse
import ru.pervukhin.data.model.PriceResponse
import ru.pervukhin.data.model.TicketOfferResponse
import ru.pervukhin.data.model.TicketResponse
import ru.pervukhin.domain.Offer

class RetrofitTest {
    lateinit var mockWebServer: MockWebServer
    lateinit var service: ApiService

    @Before
    fun before() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @After
    fun after() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetOffers() {
        val mockResponse = MockResponse()
        mockResponse.setBody(jsonOffers)
        mockWebServer.enqueue(mockResponse)

        val expected = OfferResponse(1, "Die Antwoord","Будапешт", PriceResponse(5000))
        runBlocking {
            val response = service.getOffers()
            mockWebServer.takeRequest()

            Assert.assertEquals(expected, response.body()?.data?.getOrNull(0))

        }
    }

    @Test
    fun testGetTickets() {
        val mockResponse = MockResponse()
        mockResponse.setBody(jsonTickets)
        mockWebServer.enqueue(mockResponse)

        val expected = TicketResponse(
            100,
            "Самый удобный",
            PriceResponse(1999),
            "На сайте Купибилет",
            "Якутия",
            FlyInfoResponse("Москва", "2024-02-23T03:15:00", "VKO"),
            FlyInfoResponse("Сочи", "2024-02-23T07:10:00", "AER"),
            false,
            false,
            LuggageResponse(false, PriceResponse(1082)),
            HandLuggageResponse(true, "55x20x40"),
            false,
            true
        )
        runBlocking {
            val response = service.getTicket()
            mockWebServer.takeRequest()

            Assert.assertEquals(expected, response.body()?.data?.getOrNull(0))

        }
    }

    @Test
    fun testGetTicketsOffer() {
        val mockResponse = MockResponse()
        mockResponse.setBody(jsonOfferTickets)
        mockWebServer.enqueue(mockResponse)

        val expected = TicketOfferResponse(
            1,
            "Уральские авиалинии",
            listOf("07:00","09:10","10:00","11:30","14:15","19:10","21:00","23:30"),
            PriceResponse(3999)
        )
        runBlocking {
            val response = service.getTicketOffers()
            mockWebServer.takeRequest()

            Assert.assertEquals(expected, response.body()?.data?.getOrNull(0))

        }
    }

    val jsonOffers = "{\"offers\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"title\": \"Die Antwoord\",\n" +
            "      \"town\": \"Будапешт\",\n" +
            "      \"price\": {\n" +
            "        \"value\": 5000\n" +
            "      }\n" +
            "    }]}"
    val jsonTickets = "{\"tickets\": [\n" +
            "    {\n" +
            "      \"id\": 100,\n" +
            "      \"badge\": \"Самый удобный\",\n" +
            "      \"price\": {\n" +
            "        \"value\": 1999\n" +
            "      },\n" +
            "      \"provider_name\": \"На сайте Купибилет\",\n" +
            "      \"company\": \"Якутия\",\n" +
            "      \"departure\": {\n" +
            "        \"town\": \"Москва\",\n" +
            "        \"date\": \"2024-02-23T03:15:00\",\n" +
            "        \"airport\": \"VKO\"\n" +
            "      },\n" +
            "      \"arrival\": {\n" +
            "        \"town\": \"Сочи\",\n" +
            "        \"date\": \"2024-02-23T07:10:00\",\n" +
            "        \"airport\": \"AER\"\n" +
            "      },\n" +
            "      \"has_transfer\": false,\n" +
            "      \"has_visa_transfer\": false,\n" +
            "      \"luggage\": {\n" +
            "        \"has_luggage\": false,\n" +
            "        \"price\": {\n" +
            "          \"value\": 1082\n" +
            "        }\n" +
            "      },\n" +
            "      \"hand_luggage\": {\n" +
            "        \"has_hand_luggage\": true,\n" +
            "        \"size\": \"55x20x40\"\n" +
            "      },\n" +
            "      \"is_returnable\": false,\n" +
            "      \"is_exchangable\": true\n" +
            "    }]}"
    val jsonOfferTickets = "{\"tickets_offers\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"title\": \"Уральские авиалинии\",\n" +
            "      \"time_range\": [\n" +
            "        \"07:00\",\n" +
            "        \"09:10\",\n" +
            "        \"10:00\",\n" +
            "        \"11:30\",\n" +
            "        \"14:15\",\n" +
            "        \"19:10\",\n" +
            "        \"21:00\",\n" +
            "        \"23:30\"\n" +
            "      ],\n" +
            "      \"price\": {\n" +
            "        \"value\": 3999\n" +
            "      }\n" +
            "    }]}"
}