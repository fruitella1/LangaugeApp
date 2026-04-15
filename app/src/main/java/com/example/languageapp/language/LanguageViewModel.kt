package com.example.languageapp.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.languageapp.common.SharedPreferencesHelper
import com.example.languageapp.language.arch.LanguageAction
import com.example.languageapp.language.arch.LanguageItem
import com.example.languageapp.language.arch.LanguageState
import com.example.languageapp.languageApi.RetrofitInstance
import com.example.languageapp.languageApi.TranslationRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LanguageViewModel(
    private val preferencesHelper: SharedPreferencesHelper,
    private var retrofitInstance: RetrofitInstance,
) : ViewModel() {

    private val _state: MutableStateFlow<LanguageState> =
        MutableStateFlow(
            LanguageState(
                textSearch = "",
                filteredLanguages = preferencesHelper.getLanguages().map {
                    LanguageItem(
                        language = it,
                        code = it
                    )
                }
            )
        )
    val state: StateFlow<LanguageState> = _state.asStateFlow()

    init {
        val savedCode = preferencesHelper.getSelectedLanguage()

        _state.value = _state.value.copy(
            languageSelectedCode = savedCode
        )
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
                        it.language.contains(other = text, ignoreCase = true)
                    }
                }
                _state.value = _state.value.copy(
                    textSearch = text, filteredLanguages = filtered
                )
            }

            is LanguageAction.LanguageSelected -> {
                preferencesHelper.saveSelectedLanguage(action.item.code)
                _state.value = _state.value.copy(
                    languageSelectedCode = action.item.code
                )
            }

            is LanguageAction.ChangedTextToTranslate -> {
                val text = action.textToTranslate
                _state.value = _state.value.copy(textToTranslate = text)
                translation()
            }

            else -> {}
        }
    }

    private fun languagesGet() {
        viewModelScope.launch {
            try {
                val apiLanguages = retrofitInstance.Api.getLanguages(
                    token = LANGUAGES_API_KEY
                )
                val newLanguageList =
                    apiLanguages.map { LanguageItem(language = it.name, code = it.code) }

                preferencesHelper.saveLanguages(apiLanguages.map { it.name })
                _state.value = _state.value.copy(
                    filteredLanguages = newLanguageList,
                    allLanguages = newLanguageList
                )

            } catch (e: Exception) {

                val spLanguages =
                    preferencesHelper.getLanguages().map { LanguageItem(language = it, code = it) }
                _state.value = _state.value.copy(
                    filteredLanguages = spLanguages,
                    allLanguages = spLanguages
                )
            }
        }
    }

    private fun translation() {
        viewModelScope.launch {

            val text = _state.value.textToTranslate
            val target = _state.value.languageSelectedCode

            if (text.isBlank() || target.isBlank()) return@launch

            try {
                val result = retrofitInstance.Api.getTranslation(
                    token = TRANSLATION_API_KEY,
                    request = TranslationRequest(q = text, target = target)
                )
                val translated = result.data.translations
                    .firstOrNull()
                    ?.translatedText
                    .orEmpty()

                _state.value = _state.value.copy(
                    translatedText = translated
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    translatedText = "Ошибка перевода"
                )
            }
        }
    }
}

private const val LANGUAGES_API_KEY = "Bearer ce1e879146cfcebb5f0b5478eefc0931"
private const val TRANSLATION_API_KEY = "Bearer Lyr7N241PHbvk2sZes8qwC"
