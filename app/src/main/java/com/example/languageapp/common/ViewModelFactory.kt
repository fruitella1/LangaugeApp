package com.example.languageapp.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.languageapp.language.LanguageViewModel

class ViewModelFactory(private val preferencesHelper: SharedPreferencesHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LanguageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LanguageViewModel(preferencesHelper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}