package com.yusuftalhaklc.countriesudemy.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusuftalhaklc.countriesudemy.R
import com.yusuftalhaklc.countriesudemy.adapter.CountryAdapter
import com.yusuftalhaklc.countriesudemy.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

class Feed : Fragment() {
    private lateinit var viewModel:FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // ID = R.layout.fragment_name
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()
        // alt alta göstermemize olanak sağlıyacak.
        countryList.layoutManager = LinearLayoutManager(context)
        countryList.adapter = countryAdapter


    /*
        button.setOnClickListener{
            val uuid = 556677
            val action = FeedDirections.actionFeedToCountry(uuid)
            Navigation.findNavController(it).navigate(action)
        }*/
        observeLiveData()
    }

    private fun observeLiveData(){

        viewModel.countries.observe(viewLifecycleOwner, Observer {
            it?.let {
                countryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(it)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    countryError.visibility = View.VISIBLE
                    countryList.visibility = View.GONE
                }
                else {
                    countryError.visibility = View.GONE
                }
            }
        })
        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    countryLoading.visibility = View.VISIBLE
                    countryList.visibility = View.GONE
                    countryError.visibility = View.GONE
                }
                else {
                    countryLoading.visibility = View.GONE
                }
            }
        })

    }
}