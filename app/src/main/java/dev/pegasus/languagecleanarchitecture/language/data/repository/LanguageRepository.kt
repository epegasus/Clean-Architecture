package dev.pegasus.languagecleanarchitecture.language.data.repository

import dev.pegasus.languagecleanarchitecture.language.data.datasources.DpLanguage
import dev.pegasus.languagecleanarchitecture.language.domain.entities.Language

class LanguageRepository {

    fun getLanguages(): List<Language> {
        val dpLanguage = DpLanguage()
        return dpLanguage.languageList
    }
}