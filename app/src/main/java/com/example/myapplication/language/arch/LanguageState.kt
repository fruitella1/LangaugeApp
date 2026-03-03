package com.example.myapplication.language.arch

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class LanguageState(
    val text: String = "",
    val languages: MutableList<String> = mutableStateListOf()
)