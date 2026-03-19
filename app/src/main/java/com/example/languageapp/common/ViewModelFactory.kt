package com.example.languageapp.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.languageapp.language.LanguageViewModel
import com.example.languageapp.languageApi.RetrofitInstance

class ViewModelFactory(private val preferencesHelper: SharedPreferencesHelper, private val retrofitInstance: RetrofitInstance) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LanguageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LanguageViewModel(preferencesHelper, retrofitInstance) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}