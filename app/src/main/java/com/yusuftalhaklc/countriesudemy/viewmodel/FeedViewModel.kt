package com.yusuftalhaklc.countriesudemy.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusuftalhaklc.countriesudemy.model.Country
import com.yusuftalhaklc.countriesudemy.service.CountryAPIService
import com.yusuftalhaklc.countriesudemy.service.CountryDatabase
import com.yusuftalhaklc.countriesudemy.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application) {
    private val countryAPIService = CountryAPIService()
    private val disposable = CompositeDisposable()
    private var customPreferences = CustomSharedPreferences(getApplication())
    private var refreshTİme = 10 * 60 * 1000 * 1000 * 1000L

    //değiştirilebilir -> mutable
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){

        val updateTime = customPreferences.getTime()
        if (updateTime !=null && updateTime !=0L && System.nanoTime() - updateTime < refreshTİme){
            getDataFromROOM()
        }
        else {
            getDataFromAPI()
        }
    }
    fun refreshFromApi(){
        getDataFromAPI()
    }

    private fun getDataFromROOM(){
        countryLoading.value = true
        launch {
            val countries = CountryDatabase(getApplication()).countryDao().getAllCountries()
            show(countries)
            Toast.makeText(getApplication(),"Countries From Room",Toast.LENGTH_LONG)
        }
    }

    private fun getDataFromAPI(){
        countryLoading.value = true

        disposable.add(
            countryAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()) // ui
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        storeInRoom(t)
                        Toast.makeText(getApplication(),"Countries From Api",Toast.LENGTH_LONG)
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                    }
                })
        )
    }

    private fun show(t:List<Country>){
        countryLoading.value = false
        countryError.value = false
        countries.value = t
    }

    private fun storeInRoom(t:List<Country>){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listLong = dao.insertAll( *t.toTypedArray() ) // -> individual
            var i = 0
            while (i<t.size){
                t[i].uuid = listLong[i].toInt()
                i++
            }
            show(t)
        }
        // hasssas -> nanoTime
        customPreferences.saveTime(System.nanoTime())

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}