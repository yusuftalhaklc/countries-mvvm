package com.yusuftalhaklc.countriesudemy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusuftalhaklc.countriesudemy.model.Country

class FeedViewModel : ViewModel() {
    //değiştirilebilir -> mutable
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val country = Country(
            "Turkey",
            "Ankara",
            "Asian",
            "TRY",
            "",
            "Turkish"
        )
        val country2 = Country(
            "France",
            "Paris",
            "Europe",
            "EUR",
            "",
            "French"
        )
        val country3 = Country(
            "Germany",
            "Berlin",
            "Europe",
            "EUR",
            "",
            "German"
        )
        val countryList = arrayListOf<Country>(country,country2,country3)
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }

}