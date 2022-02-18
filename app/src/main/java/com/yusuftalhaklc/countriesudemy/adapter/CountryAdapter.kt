package com.yusuftalhaklc.countriesudemy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusuftalhaklc.countriesudemy.R
import com.yusuftalhaklc.countriesudemy.model.Country
import com.yusuftalhaklc.countriesudemy.util.downloadFormUrl
import com.yusuftalhaklc.countriesudemy.util.placeholderProgressBar
import com.yusuftalhaklc.countriesudemy.view.CountryDirections
import com.yusuftalhaklc.countriesudemy.view.FeedDirections
import kotlinx.android.synthetic.main.item_row.view.*

class CountryAdapter (private val countryList:ArrayList<Country>,val view: View) :RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(view: View) :RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_row, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.itemView.imageView.downloadFormUrl(
            countryList[position].flag,
            placeholderProgressBar(holder.itemView.context)
        )
        holder.itemView.name.text = countryList[position].name
        holder.itemView.region.text = countryList[position].region


        holder.itemView.setOnClickListener{
            val action = FeedDirections.actionFeedToCountry()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}