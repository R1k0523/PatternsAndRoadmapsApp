package ru.boringowl.parapp.presentation.repository

import androidx.lifecycle.LiveData
import ru.boringowl.parapp.domain.model.patterns.PatternInfo

interface PatternsRepository {
    fun <T : PatternInfo> getAllPatternInfos(): LiveData<List<T>>
    fun <T : PatternInfo> addPatternInfo(pattern: T)
    fun <T : PatternInfo> getPatternInfo(patternId: Int): T
    fun <T : PatternInfo> deletePatternInfo(pattern: T)
}