package ru.boringowl.parapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.boringowl.parapp.presentation.repository.Repository
import ru.boringowl.parapp.domain.model.patterns.PatternInfo

class PatternsViewModel : ViewModel() {
    fun getPatternsList(): LiveData<List<PatternInfo>?>? {
        return Repository.repository!!.getAllPatternInfos()
    }

    fun deletePatternInfo(patternInfo: PatternInfo) {
        Repository.repository!!.deletePatternInfo(patternInfo)
    }

    fun addPatternInfo(patternInfo: PatternInfo) {
        Repository.repository!!.addPatternInfo(patternInfo)

    }
}