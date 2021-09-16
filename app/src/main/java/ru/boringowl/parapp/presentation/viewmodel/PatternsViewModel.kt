package ru.boringowl.parapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.boringowl.parapp.data.repository.Repository
import ru.boringowl.parapp.domain.model.patterns.PatternInfo

class PatternsViewModel : ViewModel() {
    fun getPatternsList(): LiveData<List<PatternInfo>?>? {
        return Repository.repository!!.getAllPatternInfos()
    }

    fun deleteParty(patternInfo: PatternInfo) {
        Repository.repository?.deletePatternInfo(patternInfo)
    }
}