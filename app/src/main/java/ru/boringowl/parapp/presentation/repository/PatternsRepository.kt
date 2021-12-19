package ru.boringowl.parapp.presentation.repository

import androidx.lifecycle.LiveData
import ru.boringowl.parapp.domain.model.patterns.Pattern
import java.util.*

interface PatternsRepository {
    fun <T : Pattern> getAllPatterns(): LiveData<List<T>>
    suspend fun <T : Pattern> addPattern(pattern: T)
    fun <T : Pattern> getPattern(patternId: UUID): LiveData<T?>
    suspend fun <T : Pattern> deletePattern(pattern: T)
}