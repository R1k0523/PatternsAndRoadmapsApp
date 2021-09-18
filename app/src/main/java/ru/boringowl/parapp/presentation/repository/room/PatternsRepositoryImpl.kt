package ru.boringowl.parapp.presentation.repository.room

import android.app.Application
import androidx.lifecycle.LiveData
import ru.boringowl.parapp.presentation.repository.PatternsRepository
import ru.boringowl.parapp.presentation.repository.room.dao.PatternsDAO
import ru.boringowl.parapp.domain.model.patterns.Pattern
import ru.boringowl.parapp.presentation.repository.model.patterns.PatternDTO

class PatternsRepositoryImpl(application: Application) : PatternsRepository {
    private var patternsDAO: PatternsDAO
    private var allPatterns: LiveData<List<PatternDTO>>

    init {
        val db: MyDatabase = MyDatabase.getInstance(application)
        patternsDAO = db.patternsDAO()
        allPatterns =  patternsDAO.getAllPatterns()
    }

    override fun <T : Pattern> getAllPatterns(): LiveData<List<T>> {
        return allPatterns as LiveData<List<T>>
    }

    override fun <T : Pattern> addPattern(pattern: T) {
        MyDatabase.databaseWriteExecutor.execute { patternsDAO.addPattern(PatternDTO(pattern)) }
    }

    override fun <T : Pattern> getPattern(patternId: Int): T {
        return patternsDAO.getPattern(patternId) as T
    }

    override fun <T : Pattern> deletePattern(pattern: T) {
        MyDatabase.databaseWriteExecutor.execute { patternsDAO.deletePattern(pattern as PatternDTO) }
    }

}