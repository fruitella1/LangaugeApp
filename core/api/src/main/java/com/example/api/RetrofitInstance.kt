package com.example.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val LANGUAGE_URL = "https://ws.detectlanguage.com"

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