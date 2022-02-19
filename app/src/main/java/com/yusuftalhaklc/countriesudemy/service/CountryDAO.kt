package com.yusuftalhaklc.countriesudemy.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import com.yusuftalhaklc.countriesudemy.model.Country

@Dao
interface CountryDAO {

    @Insert
    suspend fun insertAll(vararg countries:Country) : List<Long>
    // vararg -> multiple country object
    // list long -> primary keys

    @Query("SELECT * FROM country")
    suspend fun getAllCountries() : List<Country>

    @Query("SELECT * FROM country WHERE uuid = :id ")
    suspend fun getCountry(id:Int):Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()

}