package com.example.languageapp.languageApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val LANGUAGE_URL = "https://ws.detectlanguage.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(LANGUAGE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api : ApiTranslator by lazy {
        retrofit.create(ApiTranslator::class.java)
    }
}