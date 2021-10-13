package ru.boringowl.parapp.presentation.viewmodel.patterns

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.boringowl.parapp.presentation.repository.Repository
import ru.boringowl.parapp.domain.model.patterns.Pattern

class PatternsViewModel : ViewModel() {
    fun getPatternsList(): LiveData<List<Pattern>> {
        return Repository.patternsRep.getAllPatterns()
    }

    fun deletePatternInfo(pattern: Pattern) {
        viewModelScope.launch {
            Repository.patternsRep.deletePattern(pattern)
        }
    }

    fun addPatternInfo(pattern: Pattern) {
        viewModelScope.launch {
            Repository.patternsRep.addPattern(pattern)
        }
    }
}