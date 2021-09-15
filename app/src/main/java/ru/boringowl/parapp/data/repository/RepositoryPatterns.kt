package ru.boringowl.parapp.data.repository

import androidx.lifecycle.LiveData
import ru.boringowl.parapp.domain.model.patterns.PatternInfo
import ru.boringowl.parapp.domain.model.patterns.PatternInfoDTO

interface RepositoryPatterns {
    fun getAllPatternInfo(): LiveData<List<PatternInfo>?>?
    fun addPatternInfo(party: PatternInfo)
    fun deletePatternInfo(party: PatternInfo)
}