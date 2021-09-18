package ru.boringowl.parapp.presentation.viewmodel.patterns

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.boringowl.parapp.domain.model.patterns.Pattern
import ru.boringowl.parapp.presentation.repository.Repository

class PatternViewModel(patternId: Int) : ViewModel() {
    private val _patternInfo = MutableLiveData<Pattern>()
    val pattern: LiveData<Pattern>
        get() = _patternInfo

    init {
        _patternInfo.value = Repository.patternsRep?.getPattern(patternId)
    }
}