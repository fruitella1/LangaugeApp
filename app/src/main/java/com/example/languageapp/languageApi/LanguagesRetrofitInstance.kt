package com.example.languageapp.languageApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TRANSLATION_URL = "https://api.langbly.com/"
private const val LANGUAGE_URL = "https://ws.detectlanguage.com/"

object LanguagesRetrofitInstance {

    private val retrofit = retrofitFactory(LANGUAGE_URL)

    val languagesApi : ApiRequests by lazy {
        retrofit.create(ApiRequests::class.java)
    }
//    private val translationRetrofit = retrofitFactory(TRANSLATION_URL)
//
//    val translationApi : TranslationApiTranslator by lazy {
//        translationRetrofit.create(TranslationApiTranslator::class.java)
//    }
}

fun retrofitFactory(url: String): Retrofit{
    return Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}