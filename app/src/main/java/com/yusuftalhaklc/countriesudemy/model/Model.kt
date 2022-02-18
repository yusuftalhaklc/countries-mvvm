package com.yusuftalhaklc.countriesudemy.model


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("capital")
    val capital: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String
)