package ru.pervukhin.data

import ru.pervukhin.data.Mapper.Companion.toOffer
import ru.pervukhin.data.Mapper.Companion.toTicket
import ru.pervukhin.data.Mapper.Companion.toTicketOffer
import ru.pervukhin.domain.Offer
import ru.pervukhin.domain.Repository
import ru.pervukhin.domain.Resource
import ru.pervukhin.domain.Ticket
import ru.pervukhin.domain.TicketOffer
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val service: ApiService
) : Repository {

    override suspend fun getOffers(): Resource<List<Offer>> {
        return try {
            val response = service.getOffers()

            if (response.isSuccessful) {
                Resource.success(response.body()?.data?.map { it.toOffer() })
            } else {
                Resource.error()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.error()
        }
    }

    override suspend fun getTickets(): Resource<List<Ticket>> {
        return try {
            val response = service.getTicket()

            if (response.isSuccessful) {
                Resource.success(response.body()?.data?.map { it.toTicket() })
            } else {
                Resource.error()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error()
        }
    }

    override suspend fun getTicketOffers(): Resource<List<TicketOffer>> {
        return try {
            val response = service.getTicketOffers()

            if (response.isSuccessful) {
                Resource.success(response.body()?.data?.map { it.toTicketOffer() })
            } else {
                Resource.error()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error()
        }
    }
}