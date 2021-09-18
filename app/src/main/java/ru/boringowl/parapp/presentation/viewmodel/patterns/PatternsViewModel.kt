package ru.boringowl.parapp.presentation.viewmodel.patterns

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.boringowl.parapp.presentation.repository.Repository
import ru.boringowl.parapp.domain.model.patterns.Pattern

class PatternsViewModel : ViewModel() {
    fun getPatternsList(): LiveData<List<Pattern>> {
        return Repository.patternsRep!!.getAllPatterns()
    }

    fun deletePatternInfo(pattern: Pattern) {
        Repository.patternsRep!!.deletePattern(pattern)
    }

    fun addPatternInfo(pattern: Pattern) {
        Repository.patternsRep!!.addPattern(pattern)

    }
}