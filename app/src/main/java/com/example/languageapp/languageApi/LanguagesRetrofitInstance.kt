package com.example.languageapp.languageApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LanguagesRetrofitInstance {
    private const val LANGUAGE_URL = "https://ws.detectlanguage.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(LANGUAGE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api : LanguagesApiTranslator by lazy {
        retrofit.create(LanguagesApiTranslator::class.java)
    }
}