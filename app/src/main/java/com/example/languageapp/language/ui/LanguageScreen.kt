package com.example.languageapp.language.ui

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.languageapp.language.LanguageViewModel
import com.example.languageapp.language.arch.LanguageAction
import com.example.languageapp.language.arch.LanguageItem


@Composable
fun LanguageScreen(viewModel: LanguageViewModel) {
    val state by viewModel.state.collectAsState()
    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TextField(
                value = state.text,
                onValueChange = {
                    viewModel.onAction(LanguageAction.Language(textChanged = it))
                }
            )
            LanguageList(
                languages = state.languages
            )
        }
    }
}

@Composable
fun LanguageList(languages: List<LanguageItem>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
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


