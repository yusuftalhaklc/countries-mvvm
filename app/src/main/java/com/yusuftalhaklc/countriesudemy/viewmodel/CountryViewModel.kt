package com.yusuftalhaklc.countriesudemy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusuftalhaklc.countriesudemy.model.Country

class CountryViewModel: ViewModel() {
    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(){

    }

}