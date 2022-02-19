package com.yusuftalhaklc.countriesudemy.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yusuftalhaklc.countriesudemy.model.Country
// room için database objecsi oluşturduk
@Database(entities = [Country::class], version = 1)
abstract class CountryDatabase :RoomDatabase() {

    abstract fun countryDao(): CountryDAO

    //singleton (rxJava) -> tek obje mantığı

    // heryerde ulaşmak için
    companion object{

        //farklı threadlerde çağırabileceği için volatile kullandık
        @Volatile private var instance : CountryDatabase? = null

        private val lock = Any()

        //invoke başlatmak ateşlemek
        operator fun invoke(context:Context) = instance?: synchronized(lock){
            instance?: createDatabase(context).also {
                instance = it
            }
        }


        private fun createDatabase(context:Context) = Room.databaseBuilder(
            context.applicationContext,
            CountryDatabase::class.java,
            "CountryDatabase"
        ).build()

    }

}