package ru.pervukhin.presentation.search

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.pervukhin.presentation.home.HomeViewModel
import javax.inject.Inject

@HiltViewModel
class SearchBottomSheetViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    fun saveCityFrom(value: String){
        sharedPreferences.edit().putString(HomeViewModel.CITY_FROM, value).apply()
    }

}