package dev.pegasus.languagecleanarchitecture.language.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.pegasus.languagecleanarchitecture.language.domain.entities.Language
import dev.pegasus.languagecleanarchitecture.language.domain.usecases.UseCaseLanguage

class ViewModelLanguage(private val useCaseLanguage: UseCaseLanguage) : ViewModel() {

    private val _languagesLiveData = MutableLiveData<List<Language>>()
    val languageLiveData: LiveData<List<Language>> get() = _languagesLiveData

    init {
        fetchLanguages()
    }

    private fun fetchLanguages() {
        _languagesLiveData.value = useCaseLanguage.fetchLanguages()
    }

    fun updateLanguage(selectedCode: String) {
        _languagesLiveData.value = useCaseLanguage.updateLanguages(selectedCode)
    }
}