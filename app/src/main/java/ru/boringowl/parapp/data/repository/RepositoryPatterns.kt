package ru.boringowl.parapp.data.repository

import androidx.lifecycle.LiveData
import ru.boringowl.parapp.domain.model.patterns.PatternInfo
import ru.boringowl.parapp.domain.model.patterns.PatternInfoDTO

interface RepositoryPatterns {
    fun getAllPatternInfos(): LiveData<List<PatternInfo>?>?
    fun addPatternInfo(pattern: PatternInfo)
    fun deletePatternInfo(pattern: PatternInfo)
}