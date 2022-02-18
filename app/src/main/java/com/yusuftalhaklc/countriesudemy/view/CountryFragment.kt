package com.yusuftalhaklc.countriesudemy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.yusuftalhaklc.countriesudemy.R
import com.yusuftalhaklc.countriesudemy.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.*
import kotlinx.android.synthetic.main.item_row.view.*

class Country : Fragment() {

    private lateinit var viewModel: CountryViewModel
    private var countryUUID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // ID = R.layout.fragment_name
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom()


        arguments?.let{
            countryUUID = CountryArgs.fromBundle(it).uuid
        }
        observeLiveData()

    }
    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer{
            Glide.with(this)
                .load(it.flag)
                .centerCrop()
                .into(countryImage)

            countryName.text = it.name
            countryCapital.text = it.capital
            countryRegion.text = it.region
            countryCurrency.text = it.currency
            countryLanguage.text = it.language
        })
    }

}