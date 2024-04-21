package ru.pervukhin.presentation.searchTickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.pervukhin.domain.Repository
import ru.pervukhin.domain.TicketOffer
import javax.inject.Inject

@HiltViewModel
class SearchTicketsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _ticketOffers = MutableSharedFlow<List<TicketOffer>?>()
    val ticketOffer = _ticketOffers.asSharedFlow()

    fun getTicketOffers(){
        viewModelScope.launch {
            _ticketOffers.emit(repository.getTicketOffers())
        }
    }
}