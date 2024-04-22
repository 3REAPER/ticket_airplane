package ru.pervukhin.presentation.searchTickets

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.pervukhin.domain.Repository
import ru.pervukhin.domain.Resource
import ru.pervukhin.domain.TicketOffer
import ru.pervukhin.presentation.home.HomeViewModel
import javax.inject.Inject

@HiltViewModel
class SearchTicketsViewModel @Inject constructor(
    private val repository: Repository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _ticketOffers = MutableSharedFlow<Resource<List<TicketOffer>>>(replay = 1)
    val ticketOffer = _ticketOffers.asSharedFlow()

    fun getTicketOffers(){
        viewModelScope.launch {
            _ticketOffers.emit(Resource.loading())
            _ticketOffers.emit(repository.getTicketOffers())
        }
    }

    fun saveCityFrom(value: String){
        sharedPreferences.edit().putString(HomeViewModel.CITY_FROM, value).apply()
    }
}