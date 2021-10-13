package ru.boringowl.parapp.presentation.repository.room

import android.app.Application
import androidx.lifecycle.LiveData
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.presentation.repository.PatternsRepository
import ru.boringowl.parapp.presentation.repository.room.dao.PatternsDAO
import ru.boringowl.parapp.domain.model.patterns.Pattern
import ru.boringowl.parapp.presentation.repository.model.patterns.PatternDTO

class PatternsRepositoryImpl : PatternsRepository {
    private var patternsDAO: PatternsDAO
    private var allPatterns: LiveData<List<PatternDTO>>

    init {
        val db: MyDatabase by inject(MyDatabase::class.java)
        patternsDAO = db.patternsDAO()
        allPatterns =  patternsDAO.getAllPatterns()
    }

    override fun <T : Pattern> getAllPatterns(): LiveData<List<T>> {
        return allPatterns as LiveData<List<T>>
    }

    override suspend fun <T : Pattern> addPattern(pattern: T) {
        patternsDAO.addPattern(PatternDTO(pattern))
    }

    override fun <T : Pattern> getPattern(patternId: Int): T {
        return patternsDAO.getPattern(patternId) as T
    }

    override suspend fun <T : Pattern> deletePattern(pattern: T) {
        patternsDAO.deletePattern(pattern as PatternDTO)
    }

}