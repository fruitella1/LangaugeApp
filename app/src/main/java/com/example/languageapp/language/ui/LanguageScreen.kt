package com.example.languageapp.language.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.languageapp.language.LanguageViewModel
import com.example.languageapp.language.arch.LanguageAction
import com.example.languageapp.language.arch.LanguageItem
import com.example.languageapp.language.di.languageModule
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.module.rememberKoinModules
import org.koin.core.module.Module


@Composable
fun LanguageScreen() {
    rememberKoinModules(unloadModules = true) {
        listOf(languageModule)
    }

    val viewModel = koinViewModel<LanguageViewModel>()
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TextField(
                value = state.text,
                onValueChange = {
                    viewModel.onAction(
                        LanguageAction.LanguageValueChanged(textChanged = it)
                    )
                },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
        }
    ) { paddingValues ->

        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Spacer(
                modifier = Modifier.padding(vertical = 8.dp)
            )
            LanguageList(
                languages = state.filteredLanguages
            )
        }
    }
}

@Composable
fun LanguageList(languages: List<LanguageItem>) {
    LazyColumn(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(space = 2.dp)
    ) {
        items(
            items = languages,
            key = { it.id }
        ) { item ->
            Text(
                text = item.language
            )
        }
    }
}


