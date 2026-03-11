package com.example.languageapp.language

import android.content.Context
import android.hardware.biometrics.BiometricManager
import androidx.lifecycle.ViewModel
import com.example.languageapp.common.SharedPreferencesHelper
import com.example.languageapp.language.arch.LanguageAction
import com.example.languageapp.language.arch.LanguageItem
import com.example.languageapp.language.arch.LanguageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LanguageViewModel(private val preferencesHelper: SharedPreferencesHelper) : ViewModel() {

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

    fun onAction(action: LanguageAction) {
        when (action) {
            is LanguageAction.LanguageValueChanged -> {
                val text = action.textChanged
                val filtered = if (text.isEmpty()) {
                    preferencesHelper.getLanguages().map { LanguageItem(language = it) }
                } else {
                    _state.value.languages.filter {
                        it.language.contains(text, ignoreCase = true)
                    }.map { LanguageItem(language = it.language) }
                }
                _state.value = LanguageState(text = text, languages = filtered)
            }
        }
    }
}