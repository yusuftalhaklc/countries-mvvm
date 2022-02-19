package com.yusuftalhaklc.countriesudemy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusuftalhaklc.countriesudemy.R
import com.yusuftalhaklc.countriesudemy.databinding.ItemRowBinding
import com.yusuftalhaklc.countriesudemy.model.Country
import com.yusuftalhaklc.countriesudemy.util.downloadFormUrl
import com.yusuftalhaklc.countriesudemy.util.placeholderProgressBar
import com.yusuftalhaklc.countriesudemy.view.CountryDirections
import com.yusuftalhaklc.countriesudemy.view.FeedDirections
import kotlinx.android.synthetic.main.item_row.view.*

class CountryAdapter (private val countryList:ArrayList<Country>,val view: View) :RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryClickListener {

    class CountryViewHolder(var view: ItemRowBinding) :RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemRowBinding>(inflater,R.layout.item_row, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country = countryList[position]
        holder.view.listener = this

        /*
        holder.itemView.imageView.downloadFormUrl(
            countryList[position].flag,
            placeholderProgressBar(holder.itemView.context)
        )

        holder.itemView.name.text = countryList[position].name
        holder.itemView.region.text = countryList[position].region


        holder.itemView.setOnClickListener{
            val action = FeedDirections.actionFeedToCountry(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }*/
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val uuid = v.uuid.text.toString().toInt()
        val action = FeedDirections.actionFeedToCountry(uuid)
        Navigation.findNavController(v).navigate(action)
    }

}