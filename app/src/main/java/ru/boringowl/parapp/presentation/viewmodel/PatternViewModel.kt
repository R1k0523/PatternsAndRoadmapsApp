package ru.boringowl.parapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.boringowl.parapp.domain.model.patterns.PatternInfo
import ru.boringowl.parapp.presentation.repository.Repository

class PatternViewModel(patternId: Int) : ViewModel() {
    private val _patternInfo = MutableLiveData<PatternInfo>()
    val patternInfo: LiveData<PatternInfo>
        get() = _patternInfo

    init {
        _patternInfo.value = Repository.repository?.getPatternInfo(patternId)
    }
}