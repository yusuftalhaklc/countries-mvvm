package com.yusuftalhaklc.countriesudemy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusuftalhaklc.countriesudemy.model.Country
import com.yusuftalhaklc.countriesudemy.service.CountryAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {
    private val countryAPIService = CountryAPIService()
    private val disposable = CompositeDisposable()

    //değiştirilebilir -> mutable
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){
        countryLoading.value = true

        disposable.add(
            countryAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()) // ui
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        countryLoading.value = false
                        countryError.value = false
                        countries.value = t
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                    }
                })
        )
    }

}