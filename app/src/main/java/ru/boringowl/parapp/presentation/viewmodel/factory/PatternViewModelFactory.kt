package ru.boringowl.parapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.boringowl.parapp.presentation.viewmodel.PatternViewModel

class PatternViewModelFactory(private val patternId: Int): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = PatternViewModel(patternId) as T
}