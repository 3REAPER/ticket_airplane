package ru.pervukhin.presentation.allTickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.pervukhin.domain.Repository
import ru.pervukhin.domain.Ticket
import javax.inject.Inject

@HiltViewModel
class AllTicketsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _tickets = MutableSharedFlow<List<Ticket>?>()
    val tickets = _tickets.asSharedFlow()

    fun getTickets(){
        viewModelScope.launch {
            _tickets.emit(repository.getTickets())
        }
    }
}