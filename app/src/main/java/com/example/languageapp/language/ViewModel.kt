package com.example.languageapp.language

import android.view.translation.TranslationRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.languageapp.common.SharedPreferencesHelper
import com.example.languageapp.language.arch.LanguageAction
import com.example.languageapp.language.arch.LanguageItem
import com.example.languageapp.language.arch.LanguageState
import com.example.languageapp.languageApi.ApiTranslator
import com.example.languageapp.languageApi.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LanguageViewModel(
    private val preferencesHelper: SharedPreferencesHelper,
    private var instance: RetrofitInstance = RetrofitInstance
) : ViewModel() {

    private val _state: MutableStateFlow<LanguageState> =
        MutableStateFlow(
            LanguageState(
                text = "",
                languages = preferencesHelper.getLanguages().map {
                    LanguageItem(
                        language = it
                    )
                }
            )
        )
    val state: StateFlow<LanguageState> = _state.asStateFlow()

    init {
        languagesGet()
    }

    fun onAction(action: LanguageAction) {
        when (action) {
            is LanguageAction.LanguageValueChanged -> {
                val text = action.textChanged
                val filtered = if (text.isEmpty()) {
                    state.value.allLanguages
                } else {
                    state.value.allLanguages.filter {
                        it.language.contains(text, ignoreCase = true)
                    }
                }
                _state.value = _state.value.copy(
                    text = text, languages = filtered
                )
            }
        }
    }

    private fun languagesGet() {
        viewModelScope.launch {
            try {
                val apiLanguages = instance.api.getLanguages(
                    API_KEY
                )
                val newLanguageList = apiLanguages.map { LanguageItem(language = it.name) }.toMutableList()

                _state.value = _state.value.copy(
                    languages = newLanguageList,
                    allLanguages = newLanguageList
                )
            } catch (e: Exception) {
                val spLanguages = preferencesHelper.getLanguages().map { LanguageItem(language = it) }
                _state.value = _state.value.copy(
                    languages = spLanguages,
                    allLanguages = spLanguages
                )
            }
        }
    }
}

const val API_KEY = "Bearer ce1e879146cfcebb5f0b5478eefc0931"