package ru.boringowl.parapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.boringowl.parapp.presentation.viewmodel.patterns.PatternViewModel
import java.util.*

class PatternViewModelFactory(private val patternId: String): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = PatternViewModel(UUID.fromString(patternId)) as T
}