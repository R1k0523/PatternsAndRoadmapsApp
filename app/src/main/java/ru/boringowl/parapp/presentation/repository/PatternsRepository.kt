package ru.boringowl.parapp.presentation.repository

import androidx.lifecycle.LiveData
import ru.boringowl.parapp.domain.model.patterns.PatternInfo

interface PatternsRepository {
    fun getAllPatternInfos(): LiveData<List<PatternInfo>?>?
    fun addPatternInfo(pattern: PatternInfo)
    fun getPatternInfo(patternId: Int): PatternInfo
    fun deletePatternInfo(pattern: PatternInfo)
}