package ru.pervukhin.presentation.home

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.pervukhin.domain.Offer
import ru.pervukhin.domain.Repository
import ru.pervukhin.domain.Resource
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    private val _offers = MutableSharedFlow<Resource<List<Offer>>>(replay = 1)
    val offers = _offers.asSharedFlow()

    fun getOffers(){
        viewModelScope.launch {
            _offers.emit(Resource.loading())
            _offers.emit(repository.getOffers())
        }
    }

    fun getCityFrom(): String?{
        return sharedPreferences.getString(CITY_FROM, null)
    }

    fun saveCityFrom(value: String){
        sharedPreferences.edit().putString(CITY_FROM, value).apply()
    }

    companion object{
        const val CITY_FROM = "CITY_FROM"
    }

}