package com.example.languageapp.translationApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TranslationRetrofitInstance {
    private const val TRANSLATION_URL = "https://api.langbly.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(TRANSLATION_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: TranslationApiTranslator by lazy {
        retrofit.create(TranslationApiTranslator::class.java)
    }
}