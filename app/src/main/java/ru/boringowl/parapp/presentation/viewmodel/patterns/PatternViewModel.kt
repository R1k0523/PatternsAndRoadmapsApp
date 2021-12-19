package ru.boringowl.parapp.presentation.viewmodel.patterns

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.boringowl.parapp.domain.model.patterns.Pattern
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.presentation.repository.Repository
import java.util.*

class PatternViewModel(patternId: UUID) : ViewModel() {
    private var _patternInfo: LiveData<Pattern?> = Repository.patternsRep.getPattern(patternId)
    val pattern: LiveData<Pattern?>
        get() = _patternInfo
    init {
    }
}