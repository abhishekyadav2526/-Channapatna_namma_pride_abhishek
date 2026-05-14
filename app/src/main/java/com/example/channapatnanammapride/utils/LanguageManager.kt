package com.example.channapatnanammapride.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object LanguageManager {
    val currentLanguage: MutableState<Language> = mutableStateOf(Language.ENGLISH)

    enum class Language {
        ENGLISH, KANNADA
    }

    fun toggleLanguage() {
        currentLanguage.value = if (currentLanguage.value == Language.ENGLISH) {
            Language.KANNADA
        } else {
            Language.ENGLISH
        }
    }
}
