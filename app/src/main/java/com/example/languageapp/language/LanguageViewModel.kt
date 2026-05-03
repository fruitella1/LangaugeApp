package com.example.languageapp.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.languageapp.R
import com.example.languageapp.common.SharedPreferencesHelper
import com.example.languageapp.cryptomanager.CryptoManager
import com.example.languageapp.language.arch.LanguageAction
import com.example.languageapp.language.arch.LanguageItem
import com.example.languageapp.language.arch.LanguageState
import com.example.languageapp.languageApi.RetrofitInstance
import com.example.languageapp.languageApi.TranslationRequest
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class LanguageViewModel(
    private val preferencesHelper: SharedPreferencesHelper,
    private var retrofitInstance: RetrofitInstance,
    private var cryptoManager: CryptoManager,
    private var keyEncryption: KeyEncryption
) : ViewModel() {

    private var translationDelayJob: Job? = null

    private val _state: MutableStateFlow<LanguageState> =
        MutableStateFlow(
            LanguageState(
                textSearch = "",
                languageSelectedCode = preferencesHelper.getSelectedLanguage(),
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

                translationDelayJob?.cancel()
                translationDelayJob = viewModelScope.launch {
                    delay(DELAY)
                    textTranslation()
                }
            }

            else -> {}
        }
    }

    private fun languagesGet() {
        viewModelScope.launch {
            try {
                val apiLanguages = retrofitInstance.Api.getLanguages(
                    token = cryptoManager.decryptToString(
                        preferencesHelper.getEncryptedValue(
                            LANGUAGE_KEY
                        )
                    )
                )
                val newLanguageList =
                    apiLanguages.map {
                        LanguageItem(language = it.name, code = it.code)
                    }

                preferencesHelper.saveLanguages(apiLanguages.map { it.name })
                _state.value = _state.value.copy(
                    filteredLanguages = newLanguageList,
                    allLanguages = newLanguageList
                )

            } catch (e: Exception) {

                val spLanguages = preferencesHelper.getLanguages().map { LanguageItem(language = it, code = it) }
                _state.value = _state.value.copy(
                    filteredLanguages = spLanguages,
                    allLanguages = spLanguages
                )
            }
        }
    }

    private fun textTranslation() {
        viewModelScope.launch {
            val text = _state.value.textToTranslate
            val target = _state.value.languageSelectedCode

            if (text.isBlank() || target.isBlank()) return@launch

            _state.value = _state.value.copy(errorId = null)

            try {
                val result = retrofitInstance.Api.getTranslation(
                    token = cryptoManager.decryptToString(
                        preferencesHelper.getEncryptedValue(
                            TRANSLATION_KEY
                        )
                    ),
                    request = TranslationRequest(q = text, target = target)
                )
                val translated = result.data.translations
                    .firstOrNull()
                    ?.translatedText
                    .orEmpty()

                _state.value = _state.value.copy(
                    translatedText = translated,
                    errorId = null
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    errorId = R.string.translation_error
                )
            }
        }
    }
}
private const val  DELAY = 1000.toLong()