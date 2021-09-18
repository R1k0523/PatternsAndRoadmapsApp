package ru.boringowl.parapp.presentation.repository

import androidx.lifecycle.LiveData
import ru.boringowl.parapp.domain.model.patterns.Pattern

interface PatternsRepository {
    fun <T : Pattern> getAllPatterns(): LiveData<List<T>>
    fun <T : Pattern> addPattern(pattern: T)
    fun <T : Pattern> getPattern(patternId: Int): T
    fun <T : Pattern> deletePattern(pattern: T)
}