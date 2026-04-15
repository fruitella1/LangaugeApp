package com.example.languageapp.languageApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TRANSLATION_URL = "https://api.langbly.com/"
private const val LANGUAGE_URL = "https://ws.detectlanguage.com/"

object RetrofitInstance {

    private val retrofit = retrofitFactory(LANGUAGE_URL)

    val Api : ApiRequests by lazy {
        retrofit.create(ApiRequests::class.java)
    }
}

fun retrofitFactory(url: String): Retrofit{
    return Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}